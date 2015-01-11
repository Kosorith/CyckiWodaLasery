package pl.lodz.p.ftims.geocaching.dao;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.StrictMode;

import pl.lodz.p.ftims.geocaching.model.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.DefaultHttpClient;

// import dataModel.*;  TODO: Korzystaj z tego!

public class ChallengeAccessDao implements IChallengeAccess {
    private String webServiceAddress;

    public ChallengeAccessDao(Context context){
        PropertyReader reader = new PropertyReader(context);
        webServiceAddress = reader.getProperties("httpClientProperties.properties").getProperty("ChallengeAccess");
    }

    @Override
    public ArrayList<ChallengeStub> pickChallengeList(GeoCoords coords) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ChallengeListRequest request = new ChallengeListRequest(coords);
        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("ChallengeListRequest", ChallengeListRequest.class);
        xstreamIn.aliasField("Location", ChallengeListRequest.class, "location");
        String inputXML = xstreamIn.toXML(request);
        StringEntity entity = null;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost(webServiceAddress);
        httppost.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        InputStream in;
        try {
            HttpResponse response = client.execute(httppost);
            in = response.getEntity().getContent();
            String outputXML = IOUtils.toString(in);
            XStream xstreamOut = new XStream(new DomDriver());
            xstreamOut.alias("ChallengeListReply", ChallengeListReply.class);
            xstreamOut.aliasField("Challenges", ChallengeListReply.class, "challenges");
            xstreamOut.alias("Challenge", ChallengeEntry.class);
            ChallengeListReply challenges = (ChallengeListReply) xstreamOut.fromXML(outputXML);
            ArrayList<ChallengeEntry> list = challenges.getList();
            ArrayList<ChallengeStub> challengeStubList = new ArrayList<ChallengeStub>();
            for (ChallengeEntry entry : list) {
                ChallengeStub stub = new ChallengeStub();
                stub.setId(entry.getId());
                stub.setDescription(entry.getDescription());
                stub.setName(entry.getName());
                stub.setPublicAccess(entry.isPublicAccess());
                stub.setLocation(new GeoCoords(entry.getLocation().getLatitude(), entry.getLocation().getLongitude()));
                challengeStubList.add(stub);
            }
            return challengeStubList;
        } catch (ClientProtocolException e) {
            System.out.println(e);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Challenge pickChallengeHints(ChallengeStub challengestub) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HintRequest reply = new HintRequest(challengestub);
        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("HintRequest", HintRequest.class);
        xstreamIn.aliasField("ChallengeEntry", HintRequest.class, "entry");
        String inputXML = xstreamIn.toXML(reply);

        StringEntity entity = null;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost(webServiceAddress);
        httppost.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        InputStream in;

        try {
            HttpResponse response = client.execute(httppost);
            in = response.getEntity().getContent();
            String outputXML = IOUtils.toString(in);
            XStream xstreamOut = new XStream(new DomDriver());
            xstreamOut.alias("ChallengeReply", ChallengeReply.class);
            xstreamOut.aliasField("Challenge", ChallengeReply.class, "challenge");
            ChallengeReply challengeReply = (ChallengeReply) xstreamOut.fromXML(outputXML);
            return challengeReply.getChallenge();
        }catch (ClientProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Challenge pickChallengeHints(ChallengeStub challengestub, String password) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        PrivateHintRequest reply = new PrivateHintRequest(challengestub, password);
        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("HintRequest", PrivateHintRequest.class);
        xstreamIn.aliasField("ChallengeEntry", PrivateHintRequest.class, "entry");
        xstreamIn.aliasField("Password", PrivateHintRequest.class, "password");
        String inputXML = xstreamIn.toXML(reply);

        StringEntity entity = null;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost(webServiceAddress);
        httppost.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        InputStream in;

        try {
            HttpResponse response = client.execute(httppost);
            in = response.getEntity().getContent();
            String outputXML = IOUtils.toString(in);
            XStream xstreamOut = new XStream(new DomDriver());
            xstreamOut.alias("ChallengeReply", ChallengeReply.class);
            xstreamOut.aliasField("Challenge", ChallengeReply.class, "challenge");
            ChallengeReply challengeReply = (ChallengeReply) xstreamOut.fromXML(outputXML);
            return challengeReply.getChallenge();
        }catch (ClientProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean checkChallengeAnswer(Solution solution, Credentials credentials) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SolutionSubmission submission = new SolutionSubmission(credentials, solution);
        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("SolutionSubmission", SolutionSubmission.class);
        xstreamIn.aliasField("Credentials", SolutionSubmission.class, "credentials");
        xstreamIn.aliasField("Solution", SolutionSubmission.class, "solution");
        String inputXML = xstreamIn.toXML(submission);

        StringEntity entity = null;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost(webServiceAddress);
        httppost.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        InputStream in;

        try {
            HttpResponse response = client.execute(httppost);
            in = response.getEntity().getContent();
            String outputXML = IOUtils.toString(in);
            return outputXML.contains("true"); //lamersko ale nie wiem jak będzie boolean zwracany
        } catch (ClientProtocolException e) {
            System.out.println(e);
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean sendNewChallenge(Challenge challenge) {
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
        HttpPost httppost = new HttpPost(webServiceAddress);
        httppost.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        InputStream in;

        try {
            HttpResponse response = client.execute(httppost);
            in = response.getEntity().getContent();
            String outputXML = IOUtils.toString(in);
            return outputXML.contains("true"); //lamersko ale nie wiem jak będzie boolean zwracany
        } catch (ClientProtocolException e) {
            System.out.println(e);
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    private class ChallengeListRequest {
        private Coordinates location;

        public ChallengeListRequest(GeoCoords location) {
            this.location = new Coordinates(location);
        }
    }

    private class ChallengeListReply {
        private ArrayList<ChallengeEntry> challenges;

        public ChallengeListReply() {
            challenges = new ArrayList<ChallengeEntry>();
        }

        public ArrayList<ChallengeEntry> getList() {
            return challenges;
        }
    }

    private class ChallengeEntry {
        private int id;
        private String name;
        private boolean publicAccess;
        private Coordinates location;
        private String description;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPublicAccess() {
            return publicAccess;
        }

        public void setPublicAccess(boolean publicAccess) {
            this.publicAccess = publicAccess;
        }

        public Coordinates getLocation() {
            return location;
        }

        public void setLocation(Coordinates location) {
            this.location = location;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    private class Coordinates {
        public static final int EARTH_RADIUS = 6371000;
        private double latitude;
        private double longitude;

        public Coordinates(GeoCoords g) {
            this.latitude = g.getLatitude();
            this.longitude = g.getLongitude();
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    private class SolutionSubmission {
        private Credentials credentials;
        private Solution solution;

        public SolutionSubmission(Credentials credentials, Solution solution) {
            this.credentials = credentials;
            this.solution = solution;
        }

        public SolutionSubmission() {
            super();
        }

        public Credentials getCredentials() {
            return credentials;
        }

        public void setCredentials(Credentials credentials) {
            this.credentials = credentials;
        }

        public Solution getSolution() {
            return solution;
        }

        public void setSolution(Solution solution) {
            this.solution = solution;
        }
    }

    private class KHint{
        private String text;
        private byte[] photo;
        private int distance;

        public KHint(Hint hint){
            this.text=hint.getDescription();
            this.distance=hint.getDistance();
            int iBytes = hint.getPhoto().getWidth() * hint.getPhoto().getHeight() * 4;
            ByteBuffer buffer = ByteBuffer.allocate(iBytes);
            hint.getPhoto().copyPixelsToBuffer(buffer);
            this.photo = buffer.array();
        }

        public Hint getHint(){
            Hint hint = new Hint();
            hint.setDistance(distance);
            hint.setPhoto(BitmapFactory.decodeByteArray(photo, 0, photo.length));
            hint.setDescription(text);
            return hint;
        }
    }

    private class DataModelChallenge{
        private int id;
        private String description;
        private byte[] photo;
        private ArrayList<KHint> hints;
        private int points;
        private String name;
        private Coordinates location;
        private String password;
        private String secretPassword;
        private boolean status;

        public DataModelChallenge(Challenge challenge){
            this.description=challenge.getStub().getDescription();
            ArrayList<KHint> khints = new ArrayList<KHint>();
            for (Hint hint : challenge.getHints()){
                khints.add(new KHint(hint));
            }
            this.hints=khints;
            this.id=challenge.getStub().getId();
            int iBytes = challenge.getPhoto().getWidth() * challenge.getPhoto().getHeight() * 4;
            ByteBuffer buffer = ByteBuffer.allocate(iBytes);
            challenge.getPhoto().copyPixelsToBuffer(buffer);
            this.photo = buffer.array();
            this.points = challenge.getPoints();
            this.name = challenge.getStub().getName();
            this.location = new Coordinates(challenge.getStub().getLocation());
            this.password = null;
            this.secretPassword = null;
            this.status = challenge.getStub().isPublicAccess();
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public byte[] getPhoto() {
            return photo;
        }

        public ArrayList<KHint> getHints() {
            return hints;
        }

        public String getSecretPassword() {
            return secretPassword;
        }

        public String getPassword() {
            return password;
        }

        public Coordinates getLocation() {
            return location;
        }

        public int getPoints() {
            return points;
        }

        public boolean isStatus() {
            return status;
        }
    }

    private class NewChallengeRequest{
        private DataModelChallenge challenge;

        public NewChallengeRequest(Challenge challenge){
            this.challenge = new DataModelChallenge(challenge);
        }
    }

    private class HintRequest{
        private ChallengeEntry entry;

        public HintRequest(ChallengeStub stub){
            entry = new ChallengeEntry();
            entry.setDescription(stub.getDescription());
            entry.setPublicAccess(stub.isPublicAccess());
            entry.setName(stub.getName());
            entry.setId(stub.getId());
            entry.setLocation(new Coordinates(stub.getLocation()));
        }
    }

    private class PrivateHintRequest{
        private ChallengeEntry entry;
        private String password;

        public PrivateHintRequest(ChallengeStub stub, String password){
            entry = new ChallengeEntry();
            entry.setDescription(stub.getDescription());
            entry.setPublicAccess(stub.isPublicAccess());
            entry.setName(stub.getName());
            entry.setId(stub.getId());
            entry.setLocation(new Coordinates(stub.getLocation()));
            this.password = password;
        }
    }

    private class ChallengeReply{
        private DataModelChallenge challenge;

        public Challenge getChallenge(){
            ChallengeStub stub = new ChallengeStub();
            stub.setLocation(new GeoCoords(challenge.getLocation().getLatitude(), challenge.getLocation().getLongitude()));
            stub.setPublicAccess(challenge.isStatus());
            stub.setName(challenge.getName());
            stub.setDescription(challenge.getDescription());
            stub.setId(challenge.getId());
            Challenge ch = new Challenge();
            ch.setStub(stub);
            ArrayList<Hint> list = new ArrayList<Hint>();
            ArrayList<KHint> klist = challenge.getHints();
            for (KHint h : klist){
                list.add(h.getHint());
            }
            ch.setHints(list);
            ch.setPhoto(BitmapFactory.decodeByteArray(challenge.getPhoto(), 0, challenge.getPhoto().length));
            ch.setPoints(challenge.getPoints());
            return ch;
        }
    }
}
