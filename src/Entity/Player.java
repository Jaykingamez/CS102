package Entity;

import javax.swing.ImageIcon;

public class Player {
    private String name;
    private ImageIcon image;
    private boolean playedTurn;
    private boolean folded;
    private Hand pHand;
    private Role pRole;
    private int money;
    
    
    public Player(String name, Hand pHand, Role pRole, int money, ImageIcon image) {
        this.name = name;
        this.playedTurn = false; //player fold, call or check or all-in = true, raise = all non-folded, non-all-in players false
        this.folded = false; //player fold or not, reset at start of round
        //this.winCondition = winCondition;
        this.pHand = pHand;
        this.pRole = pRole; 
        this.money = 500; // set a constant?
        this.image = image;
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

    public ImageIcon getImage(){
        return image;
    }

    public boolean equals(Player another){
        return true;
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int money){
        this.money = money;
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


    // public Player(boolean newPlayer) {
    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("Enter the username of the new player");

    // }



}
