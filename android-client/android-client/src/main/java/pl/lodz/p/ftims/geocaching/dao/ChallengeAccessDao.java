package pl.lodz.p.ftims.geocaching.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.ChallengeStub;
import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.GeoCoords;
import pl.lodz.p.ftims.geocaching.model.Hint;
import pl.lodz.p.ftims.geocaching.model.Solution;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class ChallengeAccessDao implements IChallengeAccess{

	@Override
	public List<ChallengeStub> pickChallengeList(GeoCoords coords) {
		StringWriter stringWriter = new StringWriter();
		try {			
			JAXBContext context = JAXBContext.newInstance(GeoCoords.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(coords, stringWriter);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		Socket socket = this.sendXmlToWebService(stringWriter.toString());
		String challengeStub = this.receiveXmlFromWebservice(socket);
		
		/*try {
            JAXBContext context = JAXBContext.newInstance(List<ChallengeStub>.class);
            Unmarshaller un = context.createUnmarshaller();
            List<ChallengeStub> list = (List<ChallengeStub>) un.unmarshal(challengeStub);
            return list;
        } catch (JAXBException e) {
            e.printStackTrace();
        }*/
        return null;
	}

	@Override
	public List<Hint> pickChallengeHints(ChallengeStub challengestub) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Hint> pickChallengeHints(ChallengeStub challengestub,
			String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean checkChallengeAnswer(Solution solution, Credentials credentials) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private Socket sendXmlToWebService(String xml){
		Socket socket = new Socket();
		try {
			//Ustawienie po³¹czenia
			String hostname = "localhost";
			int port = 8080;
			InetAddress addr = InetAddress.getByName(hostname);
			socket = new Socket(addr, port);
			
			//wysy³anie XML
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
			bw.write("POST /server/rest/ranking HTTP/1.0rn");
			bw.write("Content-Length: " + xml.length() + "rn");
			bw.write("Content-Type: text/xml; charset='utf-8'rn");
			bw.write("rn");
			bw.write(xml);
			bw.flush();
			bw.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		}	
		return socket;
	}
	
	private String receiveXmlFromWebservice(Socket socket){
		String response = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				response += line;
				System.out.println(line);
			}
			br.close();
			socket.close();	
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
		}		
		return response;
	}

}
