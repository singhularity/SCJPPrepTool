package Utility;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: saurabh.singh
 * Date: 5/19/11
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationReader {
    static Document dom;

    private static synchronized void parseXmlFile(String componentFile){
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(componentFile);
        }catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        }catch(SAXException se) {
            se.printStackTrace();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    //Parse the XML file and replace the value for date.
	public static synchronized XMLElements ReadXML(String filename){
        Map<String, String> files = new LinkedHashMap<String, String>();
        String title, path, header = "", location = "";
        parseXmlFile(System.getProperty("user.dir") + "\\Resources\\" + filename + ".xml");
        NodeList nl = dom.getElementsByTagName("Topic");
        NodeList n2;
        for (int x = 0; x< nl.getLength(); x++) {
            n2 = nl.item(x).getChildNodes();
            for(int j = 0;j < n2.getLength();j++){
                if(n2.item(j).getNodeName().equals("Header")){
                    header = n2.item(j).getTextContent();
                }
                else if(n2.item(j).getNodeName().equals("FilesLocation")){
                    location = n2.item(j).getTextContent();
                }
                else if(n2.item(j).getNodeName().equals("Titles")){
                    NodeList n3 = n2.item(j).getChildNodes();
                    for(int k = 0; k < n3.getLength(); k++){
                        if(n3.item(k).getNodeName().equals("Title")){
                            title = n3.item(k).getTextContent();
                            path = ((Element)n3.item(k)).getAttribute("path");
                            files.put(title,path);
                        }
                    }
                }
            }
        }
        return new XMLElements(header, location, files);
    }
}
