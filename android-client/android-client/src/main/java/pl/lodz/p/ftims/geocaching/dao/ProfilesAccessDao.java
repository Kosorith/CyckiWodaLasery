package pl.lodz.p.ftims.geocaching.dao;

import android.content.Context;
import android.os.StrictMode;

import pl.lodz.p.ftims.geocaching.model.*;
import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.Profile;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.StreamException;
import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Klasa implementujaca interfejs IProfilesAccess.
 *
 * Zapewnia usluge DAO dla aplikacji
 *
 */
public class ProfilesAccessDao implements IProfilesAccess{
    private String showProfileAddress;
    private String createProfileAddress;
    private String rankingAddress;
    private String loginAddress;
    private String passwordAddress;
    private static final String TAG = "myApp";

    /**
     * Konstruktor obiektu klasy. Niezbedny parametr to obiekt klasy Context z pakietu android.content
     *
     */
    public ProfilesAccessDao(Context context){
        PropertyReader reader = new PropertyReader(context);
        createProfileAddress = reader.getProperties("httpClientProperties.properties").getProperty("CreateProfileAddress");
        showProfileAddress = reader.getProperties("httpClientProperties.properties").getProperty("ShowProfileAddress");
        rankingAddress = reader.getProperties("httpClientProperties.properties").getProperty("RankingAddress");
        loginAddress = reader.getProperties("httpClientProperties.properties").getProperty("LoginAddress");
        passwordAddress = reader.getProperties("httpClientProperties.properties").getProperty("PasswordAddress");
    }

    /**
     * Metoda zmieniajaca haslo uzytkownika. Na podstawie parametrow w postaci obiektu klasy Credentials i String (nowe haslo) tworzony jest obiekt dataModel.ChangePasswordRequest.
     *
     * Zwrocona watosc logiczna jest stanem logicznym powodzenia operacji.
     *
     */
    public boolean changePassword(Credentials credentials, String newPassword){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        dataModel.Credentials dmCredentials = new dataModel.Credentials();
        dmCredentials.setLogin(credentials.getLogin());
        dmCredentials.setPassword(credentials.getPassword());

        dataModel.ChangePasswordRequest request = new dataModel.ChangePasswordRequest();
        request.setCredentials(dmCredentials);
        request.setNewPasswd(newPassword);

        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("ChangePasswordRequest", dataModel.ChangePasswordRequest.class);
        xstreamIn.aliasField("Credentials", dataModel.ChangePasswordRequest.class, "credentials");
        xstreamIn.aliasField("newPasswd", dataModel.ChangePasswordRequest.class, "newPasswd");

        String inputXML = xstreamIn.toXML(request);
        StringEntity entity;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        entity.setChunked(true);
        HttpPut httpPut = new HttpPut(passwordAddress);
        httpPut.setEntity(entity);
        httpPut.setHeader("Content-Type", "application/xml");
        HttpClient client = new DefaultHttpClient();
        try {
            HttpResponse response = client.execute(httpPut);
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
     * Metoda zmieniajaca ranking uzytkownikow w postaci listy. Do odpowiedniego webservicu jest wysylany pakiet HTTPGET
     *
     * Odpowiedzia jest obiekt klasy dataModel.Reply ktory nastepnie jest konwertowany na obiekt listy obiektow klasy RankingEntry
     *
     */
    @Override
    public List<RankingEntry> pickRanking() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HttpGet httpget = new HttpGet();
        URI webservice = null;
        try {
            webservice = new URI(rankingAddress);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ArrayList<RankingEntry>();
        }
        httpget.setURI(webservice);
        HttpClient client = new DefaultHttpClient();
        InputStream in;
        try {
            HttpResponse response = client.execute(httpget);
            in=response.getEntity().getContent();
            String outputXML = IOUtils.toString(in);
            XStream xstreamOut = new XStream(new DomDriver());
            xstreamOut.alias("RankingReply", dataModel.RankingReply.class);
            xstreamOut.aliasField("RankingList", dataModel.RankingReply.class, "ranking");
            xstreamOut.alias("Ranking", dataModel.Ranking.class);

            dataModel.RankingReply rankingReply = (dataModel.RankingReply)xstreamOut.fromXML(outputXML);
            ArrayList<RankingEntry> returnList = new ArrayList<RankingEntry>();
            for(dataModel.Ranking rep : rankingReply.getRanking()){
                RankingEntry entry = new RankingEntry();
                entry.setNick(rep.getProfile().getNick());
                entry.setCompletedChallenges(rep.getCompletedChallengesNum());
                entry.setPoints(rep.getPoints());
                entry.setPosition(rep.getId()); //no niby skąd mam ją wziąć wpisane na sztywno żeby nie wywalało
                returnList.add(entry);
            }
            return returnList;
        } catch (StreamException e) {
            e.printStackTrace();
            return new ArrayList<RankingEntry>();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return new ArrayList<RankingEntry>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<RankingEntry>();
        }
    }

    /**
     * Metoda tworzaca nowego uzytkownika. Na podstawie parametrow w postaci obiektu klasy Credentials i Profile tworzony jest obiekt dataModel.CreateUserRequest.
     *
     * Zwrocona watosc logiczna jest stanem logicznym powodzenia operacji.
     *
     */
    public boolean createNewUser(Profile profile, Credentials credentials){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        dataModel.CreateUserRequest request = new dataModel.CreateUserRequest();
        request.setLogin(credentials.getLogin());
        request.setPassword(credentials.getPassword());
        request.setEmail(profile.getEmail());
        request.setNick(profile.getNick());

        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("CreateUserRequest", dataModel.CreateUserRequest.class);
        xstreamIn.aliasField("login", dataModel.CreateUserRequest.class, "login");
        xstreamIn.aliasField("password", dataModel.CreateUserRequest.class, "password");
        xstreamIn.aliasField("nick", dataModel.CreateUserRequest.class, "nick");
        xstreamIn.aliasField("email", dataModel.CreateUserRequest.class, "email");

        String inputXML = xstreamIn.toXML(request);
        StringEntity entity;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost(createProfileAddress);
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
     * Metoda logujaca uzytkownika. Na podstawie parametrow w postaci obiektu klasy Credentials tworzony jest obiekt dataModel.LoginRequest.
     *
     * Zwrocona watosc logiczna jest stanem logicznym powodzenia operacji.
     *
     */
    public boolean login(Credentials credentials){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        dataModel.Credentials dmCredentials = new dataModel.Credentials();
        dmCredentials.setLogin(credentials.getLogin());
        dmCredentials.setPassword(credentials.getPassword());
        dataModel.LoginRequest request = new dataModel.LoginRequest(dmCredentials);

        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("LoginRequest", dataModel.LoginRequest.class);
        xstreamIn.aliasField("Credentials", dataModel.LoginRequest.class, "credentials");

        String inputXML;
        inputXML = xstreamIn.toXML(request);
        StringEntity entity;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost(loginAddress);
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
     * Metoda zwracajaca pelen profil uzytkownika. Do odpowiedniego webservicu wysylany jest obiekt dataModel.ProfileRequest stworzony na podstawie obiektu klasy Credentials.
     *
     * Otrzymany obiket dataModel.Profile jest zamieniany na obiekt klasy Profile z logiki klienta i zwracany
     *
     */
 public Profile getUserProfile(Credentials currentCredentials){
     StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
     StrictMode.setThreadPolicy(policy);

     dataModel.Credentials dmCredentials = new dataModel.Credentials();
     dmCredentials.setLogin(currentCredentials.getLogin());
     dmCredentials.setPassword(currentCredentials.getPassword());
     dataModel.ProfileRequest request = new dataModel.ProfileRequest(dmCredentials);

     XStream xstreamIn = new XStream(new DomDriver());
     xstreamIn.alias("ProfileRequest", dataModel.ProfileRequest.class);
     xstreamIn.aliasField("Credentials", dataModel.ProfileRequest.class, "credentials");

     String inputXML = xstreamIn.toXML(request);
     StringEntity entity  = null;
     try{
         entity = new StringEntity(inputXML);
     } catch (UnsupportedEncodingException e){
         e.printStackTrace();
         return null;
     }
     entity.setChunked(true);
     HttpPost httppost = new HttpPost(showProfileAddress);
     httppost.setEntity(entity);
     httppost.setHeader("Content-Type", "application/xml");
     HttpClient client = new DefaultHttpClient();
     InputStream in;
     try {
         HttpResponse response = client.execute(httppost);
         in = response.getEntity().getContent();
         String outputXML = IOUtils.toString(in);

         XStream xstreamOut = new XStream(new DomDriver());
         xstreamOut.alias("Profile", dataModel.Profile.class);

         dataModel.Profile dmProfile = (dataModel.Profile)xstreamOut.fromXML(outputXML);
         Profile profile = new Profile();
         profile.setNick(dmProfile.getNick());
         profile.setPoints(dmProfile.getRanking().getPoints());
         profile.setCompletedChallenges(dmProfile.getRanking().getCompletedChallengesNum());
         profile.setEmail(dmProfile.getEmail());
         return profile;
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

    /*boolean saveUserProfile(Credentials currentCredentials, Profile profile){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SaveProfileRequest request = new SaveProfileRequest(currentCredentials, profile);
        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("SaveProfileRequest", SaveProfileRequest.class);
        xstreamIn.aliasField("Profile", SaveProfileRequest.class, "profile");
        String inputXML;
        inputXML = xstreamIn.toXML(request);
        StringEntity entity  = null;
        try{
            entity = new StringEntity(inputXML);

        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost(webServiceAddress);
        httppost.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        InputStream in;
        try {
            HttpResponse response = client.execute(httppost);
            in=response.getEntity().getContent();
            String outputXML = IOUtils.toString(in);
            return outputXML.contains("true");
        } catch (ClientProtocolException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }*/
}