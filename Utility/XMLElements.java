package Utility;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: saurabh.singh
 * Date: 5/18/11
 * Time: 5:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class XMLElements {
    private String header;
    private String path;

    private Map<String,String> topics = new LinkedHashMap<String,String>();

    public XMLElements(String header, String path, Map<String,String> topics){
        this.header = header;
        this.path = path;
        this.topics = topics;
    }

    public String getHeader(){
        return header;
    }

    public String getPath(){
        return path;
    }

    public Map<String, String> getTopics(){
        return topics;
    }
}
