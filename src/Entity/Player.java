package Entity;

import javax.swing.ImageIcon;

public class Player {
    private String name;
    private ImageIcon image;
    private boolean active;
    private Hand pHand;
    private Role pRole;
    private int money;
    
    
    public Player(String name, Hand pHand, Role pRole, int money, ImageIcon image) {
        this.name = name;
        this.active = true;
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

    public boolean getActive(){
        return this.active;
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

    public void setActive(boolean active){
        this.active = active;
    }


    // public Player(boolean newPlayer) {
    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("Enter the username of the new player");

    // }



}
