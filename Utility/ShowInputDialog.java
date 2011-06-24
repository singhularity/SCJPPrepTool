package Utility;

import javax.swing.*;
import java.io.*;

public class ShowInputDialog{
   private static String editor = "";
  public void callme(File fileToWrite,String param){
    while(!getIp(fileToWrite,param)){
        getIp(fileToWrite,param);
    }
  }

    private static boolean getIp(File fileToWrite, String param){
        try{
            BufferedWriter bfrwrite = new BufferedWriter(new FileWriter(fileToWrite));
            String str =  JOptionPane.showInputDialog(null, "Enter fully qualified " + param + " location!","Set " + param + " location!", 1);
            bfrwrite.write(str);
            bfrwrite.flush();
            bfrwrite.close();
            return true;
        }catch(IOException ie){
            ScreenPrinter.println("Cannot Set Editor!");
            return false;
        }
    }
}