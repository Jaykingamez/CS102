/*
 * Name    : Chan Si Jie
 * Email ID: sijie.chan.2023
*/
package UI;

import java.awt.*;        // Using AWT layouts
import javax.swing.*;     // Using Swing components and containers

public class MainMenuUI {
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    GridLayout gridLayout = new GridLayout(4, 1);
    JPanel bluePanel = new JPanel();
    JButton start = new JButton();
    JButton profile = new JButton();
    JButton exit = new JButton();
    JFrame frame = new JFrame();

    // Pass in player class so that UI can process player information
    public MainMenuUI(){
        // Setup left panel (Yeow Leong)
        leftPanel.setBounds(0, 0, 250, 500);
        ImageIcon icon = new ImageIcon("images/yllee.jpg");
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(250, 500,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
        icon = new ImageIcon(newimg);  // assign to a new ImageIcon instance
        JLabel label = new JLabel(icon);
        leftPanel.add(label);
        
        // Setup right panel (Lay Foo)
        rightPanel.setBounds(750, 0, 250, 500);
        icon = new ImageIcon("images/lfthiang.png");
        image = icon.getImage(); // transform it 
        newimg = image.getScaledInstance(250, 500,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
        icon = new ImageIcon(newimg);  // assign to a new ImageIcon instance
        label = new JLabel(icon);
        rightPanel.add(label);
        
        //setup Centre Panel (Title and Buttons)
        bluePanel.setBackground(Color.white);
        bluePanel.setBounds(250, 0, 500, 500);
        bluePanel.setLayout(gridLayout);

        icon = new ImageIcon("images/yuchenli.png");
        label = new JLabel(icon);
        label.setText("Texas Holdem: Revenge of the Yeow Leong");
        bluePanel.add(label);

        // Start game
        start.setPreferredSize(new Dimension(150, 50));
        start.setText("START");
        start.addActionListener(e -> {
            frame.dispose();
            new DisplayUI();
        });
        bluePanel.add(start);

        // Player Account
        profile.setPreferredSize(new Dimension(150, 50));
        profile.setText("PROFILE");
        bluePanel.add(profile);

        // Quit game
        exit.setPreferredSize(new Dimension(150, 50));
        exit.setText("EXIT");
        exit.addActionListener(e -> {
            frame.dispose();
        });
        bluePanel.add(exit);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1000, 500);
        frame.setVisible(true);
        frame.add(bluePanel);
        frame.add(leftPanel);
        frame.add(rightPanel);
    }
    public static void main(String[] args) {
        
        
       

    }
    
    
}
