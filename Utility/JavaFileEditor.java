package Utility;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: singhulariti
 * Date: 5/16/11
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class JavaFileEditor {
    static String JAVA_PATH;
    static String EDITOR;

    public static void setJavaPath(File javaFile){
        try{
            BufferedReader bfr = new BufferedReader(new FileReader(javaFile));
            JAVA_PATH = "\"" + bfr.readLine();
            ScreenPrinter.println("\nJava path set to :: " + JAVA_PATH + "\"");
            bfr.close();
        }catch(IOException ie){
            ScreenPrinter.println("Cannot set java path!");
        }
    }

    public static void setEditorPath(File editorFile){
        try{
            BufferedReader bfr = new BufferedReader(new FileReader(editorFile));
            EDITOR = "\"" + bfr.readLine();
            ScreenPrinter.println("Editor path set to :: " + EDITOR + "\"");
            bfr.close();
        }catch(IOException ie){
            ScreenPrinter.println("Cannot set editor path!");
        }
    }

    public static boolean  fileEditor(String file){
        if(validateFile(file)){
            try{
                Process p = Runtime.getRuntime().exec("cmd /c " + EDITOR + " " +file);
                p.waitFor();
                return true;
            }catch(IOException ie){
                ScreenPrinter.println("Cannot open java file specified - " + file);
            }
            catch(InterruptedException ine){
                ine.printStackTrace();
            }
        }
        else{
            try{
                Process p = Runtime.getRuntime().exec("cmd /c " + EDITOR + " " +file);
                p.waitFor();
                return false;
            }catch(IOException ie){
                ScreenPrinter.println("Cannot open java file specified - " + file);
            }
            catch(InterruptedException ine){
                ine.printStackTrace();
            }
        }
        return false;
    }

    public static boolean compileFile(String file){
        if(validateFile(file)){
            File cmddfile = new File(System.getProperty("user.dir") + "\\Resources\\out1.bat");
            String cmdToExec = JAVA_PATH + "\\javac\" \"" + file + "\"";
            ScreenPrinter.println(cmdToExec);
            return writeCommandFile(cmdToExec, cmddfile);
        }
         return false;
    }

    public static void runFile(String file){
        if(validateFile(file)){
            File cmdd = new File(System.getProperty("user.dir") + "\\Resources\\out.bat");
            String cmdToExecute = JAVA_PATH + "\\java\" -classpath \"" + System.getProperty("user.dir") + "\" Material/"  + getPureName(file);
            writeCommandFile(cmdToExecute, cmdd);
        }
    }

    private static boolean writeCommandFile(String cmd, File cmdfile){
        boolean errored = true;
        try{
            BufferedWriter bfrwrite = new BufferedWriter(new FileWriter(cmdfile));
            bfrwrite.write("@echo off\n");
            bfrwrite.write(cmd);
            bfrwrite.flush();
            bfrwrite.close();
            Process p = Runtime.getRuntime().exec("cmd /c "+cmdfile);
            BufferedReader strBuild = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            if(strBuild.readLine() == null){
                strBuild = new BufferedReader(new InputStreamReader(p.getInputStream()));
                errored = false;
            }
            String line;
            ScreenPrinter.println("\n*****************************OUTPUT***************************\n");
            while((line = strBuild.readLine()) != null){
                ScreenPrinter.print(line + "\n");
            }
            ScreenPrinter.println("\n***************************************************************\n");
            strBuild.close();
        }catch(IOException ie){
            ScreenPrinter.println("Cannot open java file specified :: " + cmdfile.getAbsolutePath() + "\\" + cmdfile.getName());
            ie.printStackTrace();
        }
        return errored;
    }

    private static boolean validateFile(String file){
        File javaFile = new File(file);
        if(javaFile.exists() && javaFile.isFile() && (getExtension((javaFile.getName())).equalsIgnoreCase("java"))){
            return true;
        }
        return false;
    }

    public static String getExtension(String filename){
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    public static String getPureName(String file){
        File javaFile = new File(file);
        String filename = javaFile.getName();
        int mid = filename.lastIndexOf(".");
        int filenamelength = filename.length();
        return filename.substring(0,filenamelength - (filenamelength - mid));
    }
}
