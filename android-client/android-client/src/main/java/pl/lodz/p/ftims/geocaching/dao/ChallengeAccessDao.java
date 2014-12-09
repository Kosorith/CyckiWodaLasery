package pl.lodz.p.ftims.geocaching.dao;

import pl.lodz.p.ftims.geocaching.model.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class ChallengeAccessDao implements IChallengeAccess{

	@Override
	public List<ChallengeStub> pickChallengeList(GeoCoords coords) {
		StringWriter stringWriter = new StringWriter();
		/* Importy do tego nie działają - trzeba dodać te jary w Gradle'u,
		   Jest czwarta w nocy, a jutro prezentacja, więc tylko zakomentowałem ten kod,
		   bo nie dam rady teraz tego dodać

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
		*/
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
	public Challenge pickChallengeHints(ChallengeStub challengestub) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Challenge pickChallengeHints(ChallengeStub challengestub,
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
			//Ustawienie po��czenia
			String hostname = "localhost";
			int port = 8080;
			InetAddress addr = InetAddress.getByName(hostname);
			socket = new Socket(addr, port);
			
			//wysy�anie XML
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

	@Override
	public boolean sendNewChallenge(Challenge challenge) {
		return false;
	}

}
