package pl.lodz.p.ftims.geocaching.dao;

import android.content.Context;
import android.os.StrictMode;

import pl.lodz.p.ftims.geocaching.model.*;
import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.Profile;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
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


public class ProfilesAccessDao implements IProfilesAccess{
    private String profileAddress;
    private String rankingAddress;
    private String loginAddress;
    private String passwordAddress;
    private static final String TAG = "myApp";

    public ProfilesAccessDao(Context context){
        PropertyReader reader = new PropertyReader(context);
        profileAddress = reader.getProperties("httpClientProperties.properties").getProperty("ProfileAddress");
        rankingAddress = reader.getProperties("httpClientProperties.properties").getProperty("RankingAddress");
        loginAddress = reader.getProperties("httpClientProperties.properties").getProperty("LoginAddress");
        passwordAddress = reader.getProperties("httpClientProperties.properties").getProperty("PasswordAddress");
    }

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
        xstreamIn.autodetectAnnotations(true);

        String inputXML = xstreamIn.toXML(request);
        StringEntity entity = null;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setChunked(true);
        HttpPut httpPut = new HttpPut(passwordAddress);
        httpPut.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        try {
            HttpResponse response = client.execute(httpPut);
            if (response.getStatusLine().getStatusCode() == 200) return true;
            else return false;
        } catch (ClientProtocolException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

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
        }
        httpget.setURI(webservice);
        HttpClient client = new DefaultHttpClient();
        InputStream in;
        try {
            HttpResponse response = client.execute(httpget);
            in=response.getEntity().getContent();
            String outputXML = IOUtils.toString(in);
            XStream xstream = new XStream(new DomDriver());
            xstream.autodetectAnnotations(true);
            dataModel.RankingReply rankingReply = (dataModel.RankingReply)xstream.fromXML(outputXML);
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
        } catch (ClientProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public boolean createNewUser(Profile profile, Credentials credentials){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        dataModel.CreateUserRequest request = new dataModel.CreateUserRequest();
        request.setLogin(credentials.getLogin());
        request.setPassword(credentials.getPassword());
        request.setEmail(profile.getEmail());
        request.setNick(profile.getNick());

        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.autodetectAnnotations(true);

        String inputXML = xstreamIn.toXML(request);
        StringEntity entity = null;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost(profileAddress);
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
    }

    public boolean login(Credentials credentials){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        dataModel.Credentials dmCredentials = new dataModel.Credentials();
        dmCredentials.setLogin(credentials.getLogin());
        dmCredentials.setPassword(credentials.getPassword());
        dataModel.LoginRequest request = new dataModel.LoginRequest(dmCredentials);

        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.autodetectAnnotations(true);

        String inputXML;
        inputXML = xstreamIn.toXML(request);
        System.out.print(inputXML);
        StringEntity entity = null;
        try {
            entity = new StringEntity(inputXML);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost(loginAddress);
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
    }

 /*public Profile getUserProfile(Credentials currentCredentials){ //WEBSERVICE NIE MA METODY DO TEGO
     StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
     StrictMode.setThreadPolicy(policy);

     dataModel.Credentials dmCredentials = new dataModel.Credentials();
     dmCredentials.setLogin(currentCredentials.getLogin());
     dmCredentials.setPassword(currentCredentials.getPassword());
     dataModel.LoginRequest request = new dataModel.LoginRequest(dmCredentials);

     XStream xstreamIn = new XStream(new DomDriver());
     xstreamIn.autodetectAnnotations(true);

     String inputXML = xstreamIn.toXML(request);
     StringEntity entity  = null;
     try{
         entity = new StringEntity(inputXML);
     } catch (UnsupportedEncodingException e){
         e.printStackTrace();
     }
     entity.setChunked(true);
     HttpPost httppost = new HttpPost(profileAddress);
     httppost.setEntity(entity);
     HttpClient client = new DefaultHttpClient();
     InputStream in;
     try {
         HttpResponse response = client.execute(httppost);
         in=response.getEntity().getContent();
         String outputXML = IOUtils.toString(in);
         XStream xstreamOut = new XStream(new DomDriver());
         xstreamOut.autodetectAnnotations(true);
         dataModel.Profile dmProfile = (dataModel.Profile)xstreamOut.fromXML(outputXML);
         Profile profile = new Profile();
         profile.setNick(dmProfile.getNick());
         profile.setPoints(dmProfile.getRanking().getPoints());
         profile.setCompletedChallenges(dmProfile.getRanking().getCompletedChallengesNum());
         profile.setEmail(dmProfile.getEmail());
         return profile;
        } catch (ClientProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
     }*/

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