/*
 * Name    : Chan Si Jie
 * Email ID: sijie.chan.2023
*/
package UI;

import java.awt.*;        // Using AWT layouts
import javax.swing.*;     // Using Swing components and containers

public class MainMenuUI {
    // Contains Yeow Leong's face
    JPanel leftPanel = new JPanel();
    // Contains Lay Foo's face
    JPanel rightPanel = new JPanel();
    // Organises Centre Panel
    GridLayout gridLayout = new GridLayout(4, 1);
    // Contains Manu and all the buttons
    JPanel centrePanel = new JPanel();
    // Start button starts the game
    JButton start = new JButton();
    // Profile creates user profile
    JButton profile = new JButton();
    // Exit quits the game
    JButton exit = new JButton();
    // The frame holding the game
    JFrame frame = new JFrame();
    // Contains title screen
    JLabel titleLabel = new JLabel();

    // Pass in player class so that UI can process player information
    public MainMenuUI(){
        // Setup left panel (Yeow Leong)
        leftPanel.setBounds(0, 0, 250, 500);

        // resize image to fill the entire panel
        ImageIcon icon = new ImageIcon("images/yllee.jpg");
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(250, 500,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
        icon = new ImageIcon(newimg);  // assign to a new ImageIcon instance

        // Pass image to label
        JLabel label = new JLabel(icon);
        leftPanel.add(label);
        
        // Setup right panel (Lay Foo)
        rightPanel.setBounds(750, 0, 250, 500);

        // resize image to fill the entire panel
        icon = new ImageIcon("images/lfthiang.png");
        image = icon.getImage(); // transform it 
        newimg = image.getScaledInstance(250, 500,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
        icon = new ImageIcon(newimg);  // assign to a new ImageIcon instance

        // Pass image to label
        label = new JLabel(icon);
        rightPanel.add(label);
        
        //setup Centre Panel (Title and Buttons)
        centrePanel.setBackground(Color.white);
        centrePanel.setBounds(250, 0, 500, 500);
        centrePanel.setLayout(gridLayout);

        // Set up Li Yuchen and centre screen
        icon = new ImageIcon("images/yuchenli.png");
        titleLabel.setIcon(icon);
        titleLabel.setText("Texas Holdem: Revenge of the Yeow Leong");
        centrePanel.add(titleLabel);

        // Start game
        start.setPreferredSize(new Dimension(150, 50));
        start.setText("START");
        start.addActionListener(e -> {
            // kill the screen and go to the main game
            // need to pass it something that stores game information
            frame.dispose();
            new DisplayUI();
        });
        centrePanel.add(start);

        // Player Account
        profile.setPreferredSize(new Dimension(150, 50));
        profile.setText("PROFILE");
        profile.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("What is your name?");
            titleLabel.setText("Hello " + name + " It's me, your best friend Dr Li Yuchen :)");
        });
        centrePanel.add(profile);

        // Quit game
        exit.setPreferredSize(new Dimension(150, 50));
        exit.setText("EXIT");
        exit.addActionListener(e -> {
            frame.dispose();
        });
        centrePanel.add(exit);

        // set up frame containing all UI elements
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1000, 500);
        frame.setVisible(true);
        frame.add(centrePanel);
        frame.add(leftPanel);
        frame.add(rightPanel);
    }
}
