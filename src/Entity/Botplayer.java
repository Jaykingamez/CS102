package Entity.Utility;

public class BotPlayer extends Player {
    
    public BotPlayer(String name, Hand pHand, Role pRole){
        super(name, pHand, pRole);
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

