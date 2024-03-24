package Entity.Utility;
//kamiyachang.2023

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.Exception.*;
import java.util.Arrays;

public class FileHandle {



    
  public void fileDelete (String fileName) { 


    File myObj = new File(fileName); 
    if (myObj.delete()) { 
      System.out.println("Deleted the file: " + myObj.getName());
    } else {
      System.out.println("Failed to delete the file.");
    } 
  } 

}
