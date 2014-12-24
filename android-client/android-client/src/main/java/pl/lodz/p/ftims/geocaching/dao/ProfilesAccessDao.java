package pl.lodz.p.ftims.geocaching.dao;

import pl.lodz.p.ftims.geocaching.model.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

public class ProfilesAccessDao implements IProfilesAccess{

    public boolean changePassword(Credentials credentials, String newPassword){
        ChangePasswordRequest request = new ChangePasswordRequest(credentials, newPassword);
        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("ChangePasswordRequest", ChangePasswordRequest.class);
        xstreamIn.aliasField("Credentials", ChangePasswordRequest.class, "credentials");
        String inputXML;
        inputXML = xstreamIn.toXML(request);
        StringEntity entity = null;
        try {
            entity = new StringEntity(inputXML, ContentType.create("text/xml", Consts.UTF_8)); //nie mam pojęcia czemu nie znajduje tego ontruktora
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost("http://localhost:8080/server/rest/login");
        httppost.setEntity(entity);
        HttpClient client = HttpClients.createDefault();

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
    }

    public boolean createNewUser(Profile profile, Credentials credentials){
        CreateUserRequest request = new CreateUserRequest(profile, credentials);
        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("CreateUserRequest", CreateUserRequest.class);
        String inputXML;
        inputXML = xstreamIn.toXML(request);
        StringEntity entity = null;
        try {
            entity = new StringEntity(inputXML, ContentType.create("text/xml", Consts.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost("http://localhost:8080/server/rest/login");
        httppost.setEntity(entity);
        HttpClient client = HttpClients.createDefault();

        InputStream in;
        try {
            HttpResponse response = client.execute(httppost);
            in=response.getEntity().getContent();
            String outputXML = IOUtils.toString(in);
            return outputXML.contains("true"); //lamerko ale nie wiem jak będzie boolean zwracany
        } catch (ClientProtocolException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean login(Credentials credentials){
        LoginRequest request = new LoginRequest(credentials);
        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("LoginRequest", LoginRequest.class);
        xstreamIn.aliasField("Credentials", LoginRequest.class, "credentials");
        String inputXML;
        inputXML = xstreamIn.toXML(request);
        StringEntity entity = null;
        try {
            entity = new StringEntity(inputXML, ContentType.create("text/xml", Consts.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost("http://localhost:8080/server/rest/login");
        httppost.setEntity(entity);
        HttpClient client = HttpClients.createDefault();

        InputStream in ;
        try {
            HttpResponse response = client.execute(httppost);
            in=response.getEntity().getContent();
            String outputXML = IOUtils.toString(in);
            return outputXML.contains("true"); //lamerko ale nie wiem jak będzie boolean zwracany
        } catch (ClientProtocolException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public Profile getUserProfile(Credentials currentCredentials){
        LoginRequest request = new LoginRequest(currentCredentials);
        XStream xstreamIn = new XStream(new DomDriver());
        xstreamIn.alias("ProfileRequest", LoginRequest.class);
        xstreamIn.aliasField("Credentials", LoginRequest.class, "credentials");
        String inputXML;
        inputXML = xstreamIn.toXML(request);
        StringEntity entity  = null;
        try{
            entity = new StringEntity(inputXML, ContentType.create("text/xml", Consts.UTF_8));

        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        entity.setChunked(true);
        HttpPost httppost = new HttpPost("http://localhost:8080/server/rest/login");
        httppost.setEntity(entity);
        HttpClient client = HttpClients.createDefault();
        InputStream in;
        try {
            HttpResponse response = client.execute(httppost);
            in=response.getEntity().getContent();
            String outputXML = IOUtils.toString(in);
            XStream xstream = new XStream(new DomDriver());
            xstream.alias("Profile", LoginResponse.class);
            LoginResponse loginResponse = (LoginResponse)xstream.fromXML(outputXML);
            return loginResponse.getProfile();
        } catch (ClientProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

   /* boolean saveUserProfile(Credentials currentCredentials, Profile profile){

    }*/ //brak odpowiednich klas w datamodelu

    private class LoginRequest{
        private Credentials credentials;
        public LoginRequest(Credentials c){
            this.credentials = c;
        }
    }

    private class LoginResponse{
        private int id;
        private String login;
        private String password;
        private String nick;
        private String email;
        //private Ranking ranking;

        public Profile getProfile(){
            Profile profile = new Profile(nick, email);
            return profile;
        }
    }

    private class ChangePasswordRequest{
        private Credentials credentials;
        private String newPasswd;

        public ChangePasswordRequest(Credentials credentials, String newPasswd){
            this.credentials=credentials;
            this.newPasswd=newPasswd;
        }
    }

    private class CreateUserRequest{
        private String login = "";
        private String password = "";
        private String nick = "";
        private String email = "";

        public CreateUserRequest(Profile profile, Credentials credentials){
            this.login = credentials.getLogin();
            this.password = credentials.getPassword();
            this.nick = profile.getNick();
            this.email = profile.getEmail();
        }
    }

}