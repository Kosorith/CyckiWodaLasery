package pl.lodz.p.ftims.geocaching.dao;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.StrictMode;

import pl.lodz.p.ftims.geocaching.model.*;
import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.Solution;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.StreamException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Klasa implementujaca interfejs IChallengeAccess.
 *
 * Zapewnia usluge DAO dla aplikacji.
 *
 * @author Tobiasz_Kowalski, Jonatan_Gostynski
 */
public class ChallengeAccessDao implements IChallengeAccess {
    private String challengeAddress;
    private String challengesAddress;
    private String solutionAddress;

    /**
     * Konstruktor obiektu klasy. Niezbedny parametr to obiekt klasy Context z pakietu android.content.
     *
     */
    public ChallengeAccessDao(Context context){
        PropertyReader reader = new PropertyReader(context);
        challengeAddress = reader.getProperties("httpClientProperties.properties").getProperty("ChallengeAddress");
        challengesAddress = reader.getProperties("httpClientProperties.properties").getProperty("ChallengesAddress");
        solutionAddress = reader.getProperties("httpClientProperties.properties").getProperty("SolutionAddress");
    }

    /**
     * Metoda zwracajaca liste wyzwan w postaci listy obiektow klasy ChallengeStub pochodzacej z logiki klienta.
     * Jako parametr pobierany jest obiekt klasy GeoCoords z logiki klienta. Metoda tlumaczy go na obiekt klasy ChallengeListRequest i wysyła do webservicu.
     * Jako odpowiedz otrzymuje obiekt ChallengeListReply na podstawie ktoego kreowany jest zwracany obiekt ChallengeStub.
     *
     */
    @Override
    public ArrayList<ChallengeStub> pickChallengeList(GeoCoords coords) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        dataModel.ChallengeListRequest request = new dataModel.ChallengeListRequest(new dataModel.Coordinates(coords.getLatitude(), coords.getLongitude()));

        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("ChallengeListRequest", dataModel.ChallengeListRequest.class);
        xstreamIn.aliasField("Location", dataModel.ChallengeListRequest.class, "location");

        String inputXML = xstreamIn.toXML(request);
        StringEntity entity = null;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ArrayList<ChallengeStub>();
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost(challengesAddress);
        httppost.setEntity(entity);
        httppost.setHeader("Content-Type", "application/xml");
        HttpClient client = new DefaultHttpClient();
        InputStream in;
        try {
            HttpResponse response = client.execute(httppost);
            in = response.getEntity().getContent();
            String outputXML = IOUtils.toString(in);
            XStream xstreamOut = new XStream(new DomDriver());
            xstreamOut.alias("ChallengeListReply", dataModel.ChallengeListReply.class);
            xstreamOut.aliasField("Challenges", dataModel.ChallengeListReply.class, "challenges");
            xstreamOut.alias("Challenge", dataModel.Challenge.class);
            xstreamOut.aliasField("Coordinates", dataModel.Challenge.class, "location");
            dataModel.ChallengeListReply challenges = (dataModel.ChallengeListReply) xstreamOut.fromXML(outputXML);

            ArrayList<ChallengeStub> returnList = new ArrayList<ChallengeStub>();
            for (dataModel.ChallengeEntry challengeEntry : challenges.getChallenges()){
                ChallengeStub challengeStub = new ChallengeStub();
                challengeStub.setId(challengeEntry.getId());
                challengeStub.setName(challengeEntry.getName());
                challengeStub.setLocation(new GeoCoords(challengeEntry.getLocation().getLatitude(), challengeEntry.getLocation().getLongitude()));
                challengeStub.setDescription(challengeEntry.getDescription());
                challengeStub.setPublicAccess(challengeEntry.isPublicAccess());
                returnList.add(challengeStub);
            }
            return returnList;
        } catch (StreamException e) {
            e.printStackTrace();
            return new ArrayList<ChallengeStub>();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return new ArrayList<ChallengeStub>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<ChallengeStub>();
        }
    }

    /**
     * Metoda zwracajaca obiekt klasy Challenge z logiki klienta. Parametrem jest obiekt klasy ChallengeStub.
     * Metoda tworzy obiekt dataModel.ChallengeRequest i wysyła do odpowiedniego webservicu
     * Odpowiedzia jest obiekt dataModel.ChallengeReply ktory przed zwroceniem musi byc zamieniony na obiekt logiki aplikacji.
     *
     */
    @Override
    public Challenge pickChallengeHints(ChallengeStub challengestub) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        dataModel.ChallengeRequest request = new dataModel.ChallengeRequest(challengestub.getId(),"");
        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("ChallengeRequest", dataModel.ChallengeRequest.class);
        String inputXML = xstreamIn.toXML(request);

        StringEntity entity;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost(challengeAddress);
        httppost.setEntity(entity);
        httppost.setHeader("Content-Type", "application/xml");
        HttpClient client = new DefaultHttpClient();
        InputStream in;

        try {
            HttpResponse response = client.execute(httppost);
            in = response.getEntity().getContent();
            String outputXML = IOUtils.toString(in);
            XStream xstreamOut = new XStream(new DomDriver());
            xstreamOut.alias("ChallengeReply", dataModel.ChallengeReply.class);
            xstreamOut.aliasField("Challenge", dataModel.ChallengeReply.class, "challenge");
            dataModel.ChallengeReply challengeReply = (dataModel.ChallengeReply) xstreamOut.fromXML(outputXML);

            Challenge challenge = new Challenge();
            challenge.setPoints(challengeReply.getChallenge().getPoints());
            challenge.setPhoto(BitmapFactory.decodeByteArray(challengeReply.getChallenge().getPhoto() , 0, challengeReply.getChallenge().getPhoto().length));

            ArrayList<Hint> hints = new ArrayList<Hint>();
            for (dataModel.KHint kHint: challengeReply.getChallenge().getHints()){
                Hint hint = new Hint();
                hint.setDescription(kHint.getText());
                hint.setPhoto(BitmapFactory.decodeByteArray(kHint.getPhoto(), 0, kHint.getPhoto().length));
                hint.setDistance(kHint.getDistance());
                hints.add(hint);
            }
            challenge.setHints(hints);

            ChallengeStub challengeStub = new ChallengeStub();
            challengeStub.setId(challengeReply.getChallenge().getId());
            challengeStub.setName(challengeReply.getChallenge().getName());
            challengeStub.setLocation(new GeoCoords(challengeReply.getChallenge().getLocation().getLatitude(), challengeReply.getChallenge().getLocation().getLongitude()));
            challengeStub.setDescription(challengeReply.getChallenge().getDescription());
            challengeStub.setPublicAccess(challengeReply.getChallenge().getStatus());
            challenge.setStub(challengeStub);

            return challenge;
        } catch (StreamException e) {
            e.printStackTrace();
            return null;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Metoda zwracajaca obiekt klasy Challenge z logiki klienta. Parametrem jest obiekt klasy ChallengeStub oraz haslo.
     * Metoda tworzy obiekt dataModelu.ChallengeRequest i wysyla do odpowiedniego webservicu.
     * Odpowiedzia jest obiekt dataModel.ChallengeReply ktory przed zwroceniem musi byc zamieniony na obiekt logiki aplikacji.
     *
     */
    @Override
    public Challenge pickChallengeHints(ChallengeStub challengestub, String password) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        dataModel.ChallengeRequest request = new dataModel.ChallengeRequest(challengestub.getId(), password);
        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("ChallengeRequest", dataModel.ChallengeRequest.class);
        String inputXML = xstreamIn.toXML(request);

        StringEntity entity;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        entity.setChunked(true);
        HttpPost httppost = new HttpPost(challengeAddress);
        httppost.setEntity(entity);
        httppost.setHeader("Content-Type", "application/xml");
        HttpClient client = new DefaultHttpClient();
        InputStream in;

        try {
            HttpResponse response = client.execute(httppost);
            in = response.getEntity().getContent();
            String outputXML = IOUtils.toString(in);
            XStream xstreamOut = new XStream(new DomDriver());
            xstreamOut.alias("ChallengeReply", dataModel.ChallengeReply.class);
            xstreamOut.aliasField("Challenge", dataModel.ChallengeReply.class, "challenge");
            dataModel.ChallengeReply challengeReply = (dataModel.ChallengeReply) xstreamOut.fromXML(outputXML);

            Challenge challenge = new Challenge();
            challenge.setPoints(challengeReply.getChallenge().getPoints());
            challenge.setPhoto(BitmapFactory.decodeByteArray(challengeReply.getChallenge().getPhoto() , 0, challengeReply.getChallenge().getPhoto().length));

            ArrayList<Hint> hints = new ArrayList<Hint>();
            for (dataModel.KHint kHint: challengeReply.getChallenge().getHints()){
                Hint hint = new Hint();
                hint.setDescription(kHint.getText());
                hint.setPhoto(BitmapFactory.decodeByteArray(kHint.getPhoto(), 0, kHint.getPhoto().length));
                hint.setDistance(kHint.getDistance());
                hints.add(hint);
            }
            challenge.setHints(hints);

            ChallengeStub challengeStub = new ChallengeStub();
            challengeStub.setId(challengeReply.getChallenge().getId());
            challengeStub.setName(challengeReply.getChallenge().getName());
            challengeStub.setLocation(new GeoCoords(challengeReply.getChallenge().getLocation().getLatitude(), challengeReply.getChallenge().getLocation().getLongitude()));
            challengeStub.setDescription(challengeReply.getChallenge().getDescription());
            challengeStub.setPublicAccess(challengeReply.getChallenge().getStatus());
            challenge.setStub(challengeStub);

            return challenge;
        } catch (StreamException e) {
            e.printStackTrace();
            return null;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metoda sprawdzajaca rozwiazanie. Na podstawie parametrow w postaci obiektow klasy Solution i Credentials z modelu klienta tworzony jest obiekt SolutionSubmission z logiki serwera.
     * Zwracana wartosc logiczna odpowiada poprawnosci rozwiazania.
     *
     */
    @Override
    public boolean checkChallengeAnswer(Solution solution, Credentials credentials) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        dataModel.Solution dmSolution = new dataModel.Solution(solution.getSolved().getStub().getId(), solution.getSecret());
        dataModel.Credentials dmCredentials = new dataModel.Credentials();
        dmCredentials.setLogin(credentials.getLogin());
        dmCredentials.setPassword(credentials.getPassword());

        dataModel.SolutionSubmission submission = new dataModel.SolutionSubmission(dmCredentials, dmSolution);
        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("SolutionSubmission", dataModel.SolutionSubmission.class);
        xstreamIn.aliasField("Credentials", dataModel.SolutionSubmission.class, "credentials");
        xstreamIn.aliasField("Solution", dataModel.SolutionSubmission.class, "solution");
        String inputXML = xstreamIn.toXML(submission);

        StringEntity entity;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }

        entity.setChunked(true);
        HttpPost httppost = new HttpPost(solutionAddress);
        httppost.setEntity(entity);
        httppost.setHeader("Content-Type", "application/xml");
        HttpClient client = new DefaultHttpClient();

        try {
            HttpResponse response = client.execute(httppost);
            return response.getStatusLine().getStatusCode() == 200;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Metoda odpowiadajaca za wyslanie nowego wyzwania.
     * Implementacja metody zaplanowana na kolejna wersje aplikacji
     *
     */
    @Override
    public boolean sendNewChallenge(Challenge challenge) {
        throw new UnsupportedOperationException("Not implemented!");
    }
    /*
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        NewChallengeRequest request = new NewChallengeRequest(challenge);
        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("NewChallengeRequest", NewChallengeRequest.class);
        xstreamIn.aliasField("Challenge", NewChallengeRequest.class, "challenge");
        String inputXML = xstreamIn.toXML(request);

        StringEntity entity = null;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost(challenge);
        httppost.setEntity(entity);
        HttpClient client = new DefaultHttpClient();

        try {
            HttpResponse response = client.execute(httppost);
            if (response.getStatusLine().getStatusCode() == 200) return true;
            else return false;
        } catch (ClientProtocolException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }*/
}
