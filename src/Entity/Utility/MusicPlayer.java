/*
Si Jie
Last edit: 26/3/2024
*/
package Entity.Utility;
import java.io.*;
import javazoom.jl.player.*;

public class MusicPlayer extends Thread {

    private String fileLocation;
    private boolean loop;
    private Player player;

    public MusicPlayer (String fileLocation, boolean loop) {
        this.fileLocation = fileLocation;
        this.loop = loop;
    }

    public void run() {

        try {
            do {
                FileInputStream buff = new FileInputStream(fileLocation);
                player = new Player(buff);
                player.play();
            } while (loop);
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }

    public void close(){
        loop = false;
        player.close();
        this.interrupt();
    }
}