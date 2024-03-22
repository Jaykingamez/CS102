/*
 * Name    : Chan Si Jie
 * Email ID: sijie.chan.2023
*/
package Controller;

import java.util.List;

import UI.GameUI;
import UI.MainMenuUI;
import GameManager.GameManager;

public class GameController {
    private GameUI gameUI = null;
    private GameManager gameManager = null;

    public GameController(GameManager gameManager){
        this.gameManager = gameManager;
        gameUI = new GameUI(this);
    }

    public void updatePlayerInfo(){
        gameUI.updatePlayerInfo(gameManager.getPlayer());
    }

    public void updateTableCards(){
        gameUI.updateTableCards(gameManager.getTableCards());
    }

    public void updateHandCards(){
        gameUI.updateHandCards(gameManager.getPlayer().getpHand());
    }

    public void updateRole(){
        gameUI.updateRole(gameManager.getPlayer());
    }

    public void updatePassiveAction(){
        gameUI.updatePassiveAction(gameManager.showFold(), gameManager.showCheck());
    }

    public void updateActiveAction(){
        gameUI.updateActiveAction(gameManager.showRaise(), gameManager.showCall());
    }



    

}
