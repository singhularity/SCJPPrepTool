package Certification;

import Topics.CollectionsTopics;
import Topics.ThreadsTopics;
import Topics.Topics;
import Utility.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: singhulariti
 * Date: 5/16/11
 * Time: 10:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class SCJPMain {
    private static String[] menu1;
    private static String header;
    private static Map<Integer,Topics> topics = new HashMap<Integer,Topics>();

    public static void main(String args[]){
        int option = initiate();
        while(option != menu1.length){
            if(topics.containsKey(option)){
                topics.get(option).callMe();
            }
            else{
                if((option < menu1.length) && (option != 0)){
                    ScreenPrinter.println("Topic not yet available! Apologies :-(");
                }
                else{
                    ScreenPrinter.println("Incorrect Choice! Try again.");
                }
            }
            option = Topics.menuReader(header,menu1);
        }
    }

    private static int initiate(){
        setHandlers();
        XMLElements menu = ConfigurationReader.ReadXML("SCJPMain");
        Map<String, String> files = menu.getTopics();
        menu1 = new String[files.size()];
        int i = 0;
        for(String key : files.keySet()){
            menu1[i++] = key;
        }
        header = menu.getHeader();
        constructTopicArray(topics);
        return Topics.menuReader(header, menu1);
    }

    private static void constructTopicArray(Map<Integer, Topics> topics){
        topics.put(1,new CollectionsTopics());
        topics.put(2,new ThreadsTopics());
    }

    private static void setHandlers(){
        File javafile = new File(System.getProperty("user.dir") + "\\Resources\\javapath.txt");
        File editorfile = new File(System.getProperty("user.dir") + "\\Resources\\editorpath.txt");
        if(!javafile.exists()){
            ShowInputDialog sh = new ShowInputDialog();
            sh.callme(javafile, "Java");
        }
        if(!editorfile.exists()){
            ShowInputDialog sh = new ShowInputDialog();
            sh.callme(editorfile, "editor");
        }
        JavaFileEditor.setJavaPath(javafile);
        JavaFileEditor.setEditorPath(editorfile);
    }

}
