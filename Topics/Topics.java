package Topics;

import Utility.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: singhulariti
 * Date: 5/17/11
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Topics {

    public abstract void callMe();

    public void callMe(String className){
        XMLElements menu = ConfigurationReader.ReadXML(className);
        Map<String, String> files = menu.getTopics();
        String[] menu1 = new String[menu.getTopics().size()];
        int i = 0;
        for(String key : menu.getTopics().keySet()){
            menu1[i++] = key;
        }
        String header = menu.getHeader();
        int option = menuReader(header,menu1);
        while(option != menu1.length){
            if(option - 1 >= 0 && option < menu1.length){
                if(files.containsKey(menu1[option - 1])){
                    String file = System.getProperty("user.dir") + menu.getPath() + files.get(menu1[option - 1]);
                    ScreenPrinter.println("Launching file :: " + file + "\n");
                    boolean compile = JavaFileEditor.fileEditor(file);
                    if(compile){
                        ScreenPrinter.println("Do you want to compile the file Y/N ?");
                        if(yesNo()){
                            ScreenPrinter.println("Compiling file :: " + file + "\n");
                            boolean compiled = JavaFileEditor.compileFile(file);
                            if(!compiled){
                                ScreenPrinter.println("Do you want to run the file Y/N ?");
                                if(yesNo()){
                                    ScreenPrinter.println("Running file :: " + file + "\n");
                                    JavaFileEditor.runFile(file);
                                }
                            }
                        }
                    }
                }
                option = menuReader(header, menu1);
            }
            else{
                option = menuReader(header, menu1);
            }
        }
    }

    private static boolean yesNo(){
        try{
            BufferedReader optionReader = new BufferedReader(new InputStreamReader(System.in));
            String ip = optionReader.readLine();
            if(ip.equals("Y"))
                return true;
            else if(ip.equals("N"))
                return false;
            else{
                ScreenPrinter.println("That's not Y/N ! Try again you imbecile.");
                return yesNo();
            }
        }catch(IOException ie){
            ScreenPrinter.println("Cannot read option");
            return false;
        }
    }

    public static int menuReader(String header,String [] menu1){
        MenuBuilder.buildMainMenu(header, menu1);
        int option = 0;
        BufferedReader optionReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            String ip = optionReader.readLine();
            if(isInt(ip)){
                option = Integer.parseInt(ip);
            }
        }catch(IOException ie){
            ScreenPrinter.println("Cannot read option");
        }
        return option;
    }

    private static boolean isInt(String input){
        return (!((input.length() > 1) || (input.replaceAll("\\d","").length() > 1) || input.equals("") || input == null));
    }
}
