package Entity;

import javax.management.relation.Role;

public class Player {
    private String name;
    private int money;
    private boolean active;
    private Hand pHand;
    private Role pRole;
    
    
    public Player(String name, Hand pHand, Role pRole) {
        this.name = name;
        this.active = true;
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

    public boolean getActive(){
        return this.active;
    }

    public Role getPRole(){
        return pRole;
    }

    public int getAmount(){
        return this.money;
    }

    public boolean equals(Player another){
        
    }

    public void detuctAmount(int bet){
        this.money -= bet;
    }

    public void addAmount(int pot){
        this.money += pot;
    }

    // public Player(boolean newPlayer) {
    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("Enter the username of the new player");

    // }



}
