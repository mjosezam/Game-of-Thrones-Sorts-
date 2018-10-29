package Servlet;

import Trees.LinkedlistIS;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Serializer {

    /**
     * cambio de linked list a xml
     * @param dragon
     * @return xml
     */
    public static String serializerString(LinkedlistIS dragon){
        try{
            JAXBContext jaxbContext= JAXBContext.newInstance(Trees.LinkedlistIS.class);
            Marshaller jaxbMarshaller= jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw= new StringWriter();
            jaxbMarshaller.marshal(dragon,sw);
            String xmlContent= sw.toString();
            return xmlContent;

        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * cambio de xml a linkedlistIs
     * @param xml
     * @return LinkedlistIS
     * @throws IOException
     */
    public static LinkedlistIS deserializerString(String xml) throws IOException {
        JAXBContext jaxbContext= null;
        try {
            jaxbContext = JAXBContext.newInstance(LinkedlistIS.class);
            Unmarshaller unmarshaller= jaxbContext.createUnmarshaller();
            StringReader reader= new StringReader(xml);
            Trees.LinkedlistIS dragon=(LinkedlistIS)unmarshaller.unmarshal(reader);
            return dragon;
        } catch (JAXBException e) {
            return null;
        }
    }
    //https://www.baeldung.com/jackson-xml-serialization-and-deserialization

}
