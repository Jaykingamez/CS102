/*
Kong Khai
Last updated: 22/03/2024
*/
package GameManager;

import Controller.GameController;

public class GameManager {
    private GameController ctrl;
    public GameManager() {
        this.ctrl = null;
    }

    public void gameStarted(GameController ctrl) {
        this.ctrl = ctrl;
    }

    //fill in game inputs
}
