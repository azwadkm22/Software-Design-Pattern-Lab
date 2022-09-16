import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class XMLParser003_036 {
    XMLParser003_036(){
        String basePath = new File("").getAbsolutePath();
        completePath =  basePath + "\\config.xml";
    }

    private static String completePath = "D:/SDPLab3/src/SDPLab3/config.xml";

    public List<String> parseXML(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        List<String> commands = new ArrayList<String>();

        try {

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(completePath));

            doc.getDocumentElement().normalize();

            NodeList buttonList = doc.getElementsByTagName("button");
            NodeList textboxList = doc.getElementsByTagName("textbox");
            NodeList editboxList = doc.getElementsByTagName("editbox");

            for(int i=0; i<buttonList.getLength(); i++)
            {
                Node node = buttonList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element el = (Element) node;
                    String command = "Button" + "," +el.getElementsByTagName("value").item(0).getTextContent() +
                            ","+ el.getElementsByTagName("X").item(0).getTextContent() +
                            ","+ el.getElementsByTagName("Y").item(0).getTextContent();

                    commands.add(command);
                }

            }

            for(int i=0; i<editboxList.getLength(); i++)
            {
                Node node = editboxList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element el = (Element) node;
                    String command = "EditBox" + "," +el.getElementsByTagName("value").item(0).getTextContent() +
                            ","+ el.getElementsByTagName("X").item(0).getTextContent() +
                            ","+ el.getElementsByTagName("Y").item(0).getTextContent();

                    commands.add(command);
                }

            }

            for(int i=0; i<textboxList.getLength(); i++)
            {
                Node node = textboxList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element el = (Element) node;
                    String command = "TextBox" + "," +el.getElementsByTagName("value").item(0).getTextContent() +
                            ","+ el.getElementsByTagName("X").item(0).getTextContent() +
                            ","+ el.getElementsByTagName("Y").item(0).getTextContent();
                    commands.add(command);
                }

            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return commands;

    }

}