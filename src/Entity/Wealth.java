package Entity;
import java.util.ArrayList;
import java.io.*;

//READ: only for reference. Denxi's file to be used for actual implementation. 
//kamiyachang.2023
public class Wealth {
    
    private Chip[] myChips;
    private int totalValue;

    public Wealth() {

        this(false);

    }

    public Wealth(boolean equalStart) {

        if (equalStart) {
            myChips = new Chip[6];
            try(PrintStream out = new PrintStream(new FileOutputStream("")))
        } else {
            
        }



    }


}
