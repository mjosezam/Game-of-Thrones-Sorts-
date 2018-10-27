package Servlet;


import Trees.LinkedlistIS;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import Estructuras.ListaSimple;

public class Serializer {



    public static String serializerString(LinkedlistIS dragon) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(dragon);
        return xml;
    }

    public static LinkedlistIS deserializerString(String xml) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        LinkedlistIS dragon = xmlMapper.readValue(xml, LinkedlistIS.class);
        return dragon;
    }
    //https://www.baeldung.com/jackson-xml-serialization-and-deserialization

}