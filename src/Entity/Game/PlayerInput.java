package Entity.Game;
import java.io.*;
import java.util.*;

public class PlayerInput {

/*
[int betAmount, int callFoldRaise]

*/
Player respondingPlayer;
int[2] response;
boolean validity; 

public PlayerInput(Player p, int betAmount, int callFoldRaise) {
this.respondingPlayer = p;
this.response[0] = betAmount;
this.response[1] = callFoldRaise;

}


}
