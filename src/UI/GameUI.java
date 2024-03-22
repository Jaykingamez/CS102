/*
 * Name    : Chan Si Jie
 * Email ID: sijie.chan.2023
*/
package UI;

import java.awt.*;        // Using AWT layouts

import javax.swing.*;     // Using Swing components and containers

import Controller.GameController;
import Entity.Card;
import Entity.Player;

public class GameUI{
    // Shows player info
    JTextArea playerInfoText = new JTextArea();
    

    // Pass in player and game information
    public GameUI(GameController gameController){
        ImageIcon icon = new ImageIcon("images/2c.gif");

        // Contains player name, picture, money and betted money
        JPanel playerInfo = new JPanel();
        // Lists player role and their win condition
        JPanel winConditionPanel = new JPanel();
        // Shows all community cards
        JPanel tablePanel = new JPanel();
        // Shows all cards in player's hand
        JPanel handPanel = new JPanel();
        // Shows all passive action player can take
        // FOLD, CHECK
        JPanel passiveActionPanel = new JPanel();
        // Shows all active action player can take 
        // RAISE, CALL
        JPanel activeActionPanel = new JPanel();

        // Label contains player's image and info
        JLabel label = new JLabel();
        String playerString = String.format("Player info %s%nMoney: %d%nBet: %d ",
         "Yeow Leong", 500, 100);
        JTextArea playerInfoText = new JTextArea(playerString);

        updatePlayerInfo(gameController.getPlayer());
        
        // Transform player's image
        ImageIcon yeowLeong = new ImageIcon("images/yllee.jpg");
        Image image = yeowLeong.getImage(); // transform it 
        Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
        yeowLeong = new ImageIcon(newimg);  // assign to a new ImageIcon instance
        label.setIcon(yeowLeong);

        // Contain's player's role and win condition
        String winCondition = String.format("Win Conditon:%n Role: %s%n Description: %s",
        "jester", "Just die lmao");
        JTextArea winConditionText = new JTextArea(winCondition);

        // Create multiple JLabels with the same image
        // table cards
        JLabel label2 = new JLabel(icon);
        JLabel label3 = new JLabel(icon);
        JLabel label4 = new JLabel(icon);
        JLabel label5 = new JLabel(icon);

        // hand cards
        JLabel label6 = new JLabel(icon);
        JLabel label7 = new JLabel(icon);

        // Contains player name, picture, money and betted money
        playerInfo.setBackground(Color.red);
        playerInfo.setBounds(0, 0, 250, 250);

        // Lists player role and their win condition
        winConditionPanel.setBackground(Color.blue);
        winConditionPanel.setBounds(750, 0, 250, 250);

        // Shows all community cards
        tablePanel.setBackground(Color.green);
        tablePanel.setBounds(250, 0, 500, 250);

        // Shows all cards in player's hand
        handPanel.setBackground(Color.black);
        handPanel.setBounds(250, 250, 500, 250);

        // Shows all passive action player can take
        // FOLD, CHECK
        passiveActionPanel.setBackground(Color.pink);
        passiveActionPanel.setBounds(0, 250, 250, 250);

        // FOLD button 
        JButton fold = new JButton();
        fold.setPreferredSize(new Dimension(150, 50));
        fold.setText("FOLD");

        // CHECK button
        JButton check = new JButton();
        check.setPreferredSize(new Dimension(150, 50));
        check.setText("CHECK");

        // Add FOLD and CHECK to panel
        passiveActionPanel.add(fold);
        passiveActionPanel.add(check);

        // Shows all active action player can take 
        // RAISE, CALL
        activeActionPanel.setBackground(Color.magenta);
        activeActionPanel.setBounds(750, 250, 250, 250);

        // RAISE button
        JButton raise = new JButton();
        raise.setPreferredSize(new Dimension(150, 50));
        raise.setText("RAISE");

        // CALL button
        JButton call = new JButton();
        call.setPreferredSize(new Dimension(150, 50));
        call.setText("CALL");

        activeActionPanel.add(raise);
        activeActionPanel.add(call);
        
        // Frame that contains all UI elements
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1000, 500);
        frame.setVisible(true);
         
        playerInfo.add(playerInfoText);
        playerInfo.add(label);

        tablePanel.add(label2);
        tablePanel.add(label3);
        tablePanel.add(label4);
        tablePanel.add(label5);

        winConditionPanel.add(winConditionText);

        handPanel.add(label6);
        handPanel.add(label7);

        frame.add(playerInfo);
        frame.add(winConditionPanel);
        frame.add(tablePanel);
        frame.add(handPanel);
        frame.add(passiveActionPanel);
        frame.add(activeActionPanel);
    }

    public void updatePlayerInfo(Player player){
        String playerString = String.format("Player info %s%nMoney: %d%nBet: %d ",
        player.getName(), player.getCurrentChips(), player.getCurrentBet());
        playerInfoText.setText(playerString);
    }

    public void updateTableCards(List<Card> cards){
        tablePanel.removeAll();
        for (Card card: cards){
            JLabel label = new JLabel(card.getCardImage());
            tablePanel.add(label);
        }
    }

    public void updateRole(Player player){
        String winCondition = String.format("Win Conditon:%n Role: %s%n Description: %s",
        player.getPRole().getRoleName(), player.getPRole().getRoleDescription());
        JTextArea winConditionText = new JTextArea(winCondition);

    }

    public void updatePassiveAction(boolean showFold, boolean showCheck){
        JButton check = new JButton();
        check.setVisible(show);
    }
}
