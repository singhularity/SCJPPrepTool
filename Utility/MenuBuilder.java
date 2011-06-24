package Utility;

/**
 * Created by IntelliJ IDEA.
 * User: singhulariti
 * Date: 5/16/11
 * Time: 10:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class MenuBuilder {
    public static void buildMainMenu(String title, String[] menuItems){
        int itemNumber = 1;
        title = "       " + title + " ";
        ScreenPrinter.println("\n"+title);
        ScreenPrinter.print("       ");
        for(int i = 0; i <= title.trim().length(); ++i){
            ScreenPrinter.print("-");
        }
        ScreenPrinter.println("");
        for(String item : menuItems){
            ScreenPrinter.println("       " + itemNumber++ + ". " + item );
        }
    }
}
