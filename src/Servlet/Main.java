package Servlet;

import Structure.LinkedList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Main {
    private static final String url = "http://localhost:9080/web_war_exploded/Prueba";

    public static void main(String[ ] args) {
        new Main().send_requests();
    }

    private void send_requests() {
        try {
            HttpURLConnection conn = null;

            // POST request to create some Fibonacci numbers.
            LinkedList nums= new LinkedList();
            nums.append(4);
            nums.append(10);
            System.out.println(nums);

            String payload = URLEncoder.encode("dragon", "UTF-8") + "=" +
                    URLEncoder.encode(nums.toString(), "UTF-8");

            // Send the request
            conn = get_connection(url, "POST");
            conn.setRequestProperty("accept", "text/xml");
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(payload);
            out.flush();
            get_response(conn);

            // GET to test whether POST worked
            conn = get_connection(url, "GET");
            conn.addRequestProperty("accept", "text/xml");
            conn.connect();
            get_response(conn);
        }
        catch(IOException e) { System.err.println(e); }
        catch(NullPointerException e) { System.err.println(e); }
    }

    private HttpURLConnection get_connection(String url_string, String verb) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(url_string);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(verb);
            conn.setDoInput(true);
            conn.setDoOutput(true);
        }
        catch(MalformedURLException e) { System.err.println(e); }
        catch(IOException e) { System.err.println(e); }
        return conn;
    }
    private void get_response(HttpURLConnection conn) {
        try {
            String xml = "";
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String next = null;
            while ((next = reader.readLine()) != null)
                xml += next;
            System.out.println("The response:\n" + xml);
        }
        catch(IOException e) { System.err.println(e); }
    }
    private static String jaxbObjectToXML(LinkedList<LinkedList> dragon){
        try{
            JAXBContext jaxbContext= JAXBContext.newInstance(LinkedList.class);
            Marshaller jaxMarshaller= jaxbContext.createMarshaller();
            jaxMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw= new StringWriter();
            jaxMarshaller.marshal(dragon, sw);
            String xmlContent= sw.toString();
            return xmlContent;

        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
