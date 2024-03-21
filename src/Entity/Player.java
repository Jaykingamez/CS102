package cardgame.stuff;
import java.util.io.*;
import java.util.*;

import javax.management.relation.Role;

import java.io.File;

public class Player {
    private String name;
    private boolean active;
    private Hand pHand;
    private Role pRole;
    
    
    public Player(String name, Hand pHand, Role pRole) {
        this.name = name;
        this.active = true;
        this.winCondition = winCondition;
        this.pHand = pHand;
        this.pRole = pRole;
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

    public boolean equals(Player another){
        
    }

    // public Player(boolean newPlayer) {
    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("Enter the username of the new player");

    // }



}
