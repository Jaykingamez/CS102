package cardgame.stuff;
import java.util.ArrayList;
import java.io.*;

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
