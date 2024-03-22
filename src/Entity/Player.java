package Entity;
import java.util.io.*;

import javax.swing.ImageIcon;

import java.util.*;

import java.io.File;

public class Player {
    private String name;
    private ImageIcon image;
    private boolean active;
    private Hand pHand;
    private Role pRole;
    private Wealth money;
    
    
    public Player(String name, Hand pHand, Role pRole, 
        Wealth money, ImageIcon image) {
        this.name = name;
        this.active = true;
        //this.winCondition = winCondition;
        this.pHand = pHand;
        this.pRole = pRole;
        this.money = money;
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

    public void setActive(boolean active){
        this.active = active;
    }


    // public Player(boolean newPlayer) {
    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("Enter the username of the new player");

    // }



}
