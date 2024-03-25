package Entity.Game;
import Entity.Utility.*;
import Entity.Data.*;
import GameManager.GameManager;

import javax.swing.ImageIcon;

public class Player {
    private String name;
    private int money;
    private boolean active;
    private ImageIcon image;
    private boolean playedTurn;
    private boolean folded;
    private Hand pHand;
    private Role pRole;
    
    
    public Player(String name, Hand pHand, Role pRole) {
        this.name = name;
        this.active = true;
        this.playedTurn = false; //player fold, call or check or all-in = true, raise = all non-folded, non-all-in players false
        this.folded = false; //player fold or not, reset at start of round
        this.pHand = pHand;
        this.pRole = pRole;
        this.money = 10000;
    }

    public Hand getpHand(){
        return this.pHand;
    }

    public String getName(){
        return this.name;
    }

    public boolean getPlayed(){
        return this.playedTurn;
    }

    public Role getPRole(){
        return pRole;
    }

    public int getAmount(){
        return this.money;
    }

    public void setAmount(int money){
        this.money = money;
    }

    public void deductAmount(int bet){
        this.money -= bet;
    }

    public void addAmount(int pot){
        this.money += pot;
    }

    public void setPlayed(boolean active){
        this.playedTurn = active;
    }

    public boolean getFolded() {
        return this.folded;
    }

    public void setFolded(boolean fold) {
        this.folded = fold;
    }

    public boolean getActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }




}