package cardgame.stuff;
import java.io.*;
import java.io.File;

public class DataArchive {



    public int gamesPlayed;
    public static String playerName;
    


    public DataArchive() {
        this(true);

    }
    //invoke dataarchive without any input to continue with an old save file. 

    public DataArchive(boolean loadGame) {

        String str1 = "new";

        if (loadGame) {
            str1 = "existing";
            //load existing data
        } else {
            
            startNewGame();
        }

        System.out.printf("Loading %s game...%n", str1);
        

    }

    
    public void resetData() {


        System.out.println("""
                Are you sure you want to erase all existing data?

                Yes (y/Y)           No (n/N)
                """);

        Scanner sc = new Scanner(System.in);
        char confirm = sc.nextChar();
        boolean deleteRecords = (confirm == 'y' | confirm == 'Y') ? true : false ;
        if (deleteRecords) {
            delAll();
            
        }

        return;

        sc.close();
        
    }

}