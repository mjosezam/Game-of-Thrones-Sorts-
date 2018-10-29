package Servlet;

import Trees.LinkedlistIS;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Message {
    private static final String url = "http://localhost:9080/web_war_exploded/Prueba";

    public void Message(){
    }

    /**
     * Realiza la conexion al servidor posteando lo que necesita que ayude
     * @return LinkedListIs oleada de dragones
     */
    public LinkedlistIS Generate(){
        try {
            HttpURLConnection conn = null;
            conn = get_connection(url, "POST");
            conn.setRequestProperty("accept", "text/plain");
            String Generate = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode("Generate", "UTF-8");
            send_requests(Generate, conn);
            String x= get_response(conn);
            return Serializer.deserializerString(x);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param message
     * @param dragonList
     * @return Linked list oleada ordenada
     */
    public LinkedlistIS Method(String message, LinkedlistIS dragonList){
        try {
            HttpURLConnection conn = null;
            conn = get_connection(url, "POST");
            conn.setRequestProperty("accept", "text/plain");
            String List= Serializer.serializerString(dragonList);
            String Generate = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8")+"&&"+URLEncoder.encode("dragonList", "UTF-8")+ "=" + URLEncoder.encode(List, "UTF-8");
            send_requests(Generate, conn);
            String x= get_response(conn);
            return Serializer.deserializerString(x);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Envia los datos al servidor
     * @param request
     * @param conn
     */
    private void send_requests(String request, HttpURLConnection conn) {
        try {

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(request);
            out.flush();


        }
        catch(IOException e) { System.err.println(e); }
        catch(NullPointerException e) { System.err.println(e); }
    }

    /**
     * Realiza conexion con el servidor
     * @param url_string
     * @param verb
     * @return la conexion
     */
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

    /**
     * Toma la respuesta del servidor y la envia para enviarselo al cliente
     * @param conn
     * @return respuesta el servidor
     */
    private String get_response(HttpURLConnection conn) {
        try {
            String xml = "";
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String next = null;
            while ((next = reader.readLine()) != null)
                xml += next;
            return xml;

        }
        catch(IOException e) { System.err.println(e); }
        return null;
    }
}
