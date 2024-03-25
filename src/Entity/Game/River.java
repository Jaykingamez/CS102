/*
Jia Lin
Last edit: 21/03/2024
*/

/*
Are u ok with putting this under the package Entity.Game?
*/
package Entity.Game;
import GameManager.GameManager;
import java.util.*;

public class River {
    private ArrayList<Card> river;

    // call new river class when start gamee and 3 cards are place in river
    public River(ArrayList<Card> startCards){
        river = startCards;
    }

    public ArrayList<Card> getRiver(){
        return river;
    }

    public void addCard(Card c){
        river.add(c);
    }

    
    
//WHy not change this to just river.clear() 
    
    public void endRiver(){
        river = new ArrayList<>();
    }
}