/*
Jialin
Last edit: 25/3/2024
*/

package Entity.Game;

public class BotPlayer extends Player {
    
    public BotPlayer(String name, Hand pHand){
        super(name, pHand, null);
    }

    public Hand getpHand(){
        return super.getpHand();
    }

    public String getName(){
        return super.getName();
    }

    public boolean getActive(){
        return super.getActive();
    }

    public Role getPRole(){
        return super.getPRole();
    }
}

