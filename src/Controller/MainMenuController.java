/*
 * Name    : Chan Si Jie
 * Email ID: sijie.chan.2023
*/
package Controller;

import Entity.*;
import UI.MainMenuUI;

public class MainMenuController {
    private MainMenuUI mainMenuUI = null;
    private GameManager gameManager = null;

    public MainMenuController(GameManager gameManager){
        mainMenuUI = new MainMenuUI(this);
        this.gameManager = gameManager;
    }

    public GameController startGame(){
        GameController gameController = new GameController();
        gameManager.gameStarted(gameController);
    }

    // Save and load player files, if does not exist, create new player
    public Player createPlayer(String name, Hand pHand, Role pRole){
        return new Player(name, pHand, pRole);
    }
}
