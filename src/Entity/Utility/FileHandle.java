package Entity.Utility;
//kamiyachang.2023

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.Exception.*;
import java.util.Arrays;

public class FileHandle {

    /*
    Making a class that makes and handles any exceptions from writing and making a new player svae fle
    */

public void newSaveFile() {

    fileDelete("player_names.txt");
    File myObj = new File("src\\Entity\\Utility\\player_names.txt");
    
}

    
  public void fileDelete (String fileName) { 


    File myObj = new File(fileName); 
    if (myObj.delete()) { 
      System.out.println("Deleted the file: " + myObj.getName());
    } else {
      System.out.println("Failed to delete the file.");
    } 
  } 

}