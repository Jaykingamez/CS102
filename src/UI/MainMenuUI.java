package UI;

import java.awt.*;        // Using AWT layouts
import javax.swing.*;     // Using Swing components and containers

public class MainMenuUI {
    public static void main(String[] args) {
        
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 250, 500);
        ImageIcon icon = new ImageIcon("images/yllee.jpg");
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(250, 500,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
        icon = new ImageIcon(newimg);  // assign to a new ImageIcon instance
        JLabel label = new JLabel(icon);
        leftPanel.add(label);

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(750, 0, 250, 500);
        icon = new ImageIcon("images/lfthiang.png");
        image = icon.getImage(); // transform it 
        newimg = image.getScaledInstance(250, 500,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
        icon = new ImageIcon(newimg);  // assign to a new ImageIcon instance
        label = new JLabel(icon);
        rightPanel.add(label);



        GridLayout gridLayout = new GridLayout(4, 1);
        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.white);
        bluePanel.setBounds(250, 0, 500, 500);
        bluePanel.setLayout(gridLayout);

        icon = new ImageIcon("images/yuchenli.png");
        label = new JLabel(icon);
        label.setText("Texas Holdem: Revenge of the Yeow Leong");
        bluePanel.add(label);

        JButton start = new JButton();
        start.setPreferredSize(new Dimension(150, 50));
        start.setText("START");
        start.addActionListener(e -> {
                new DisplayUI();  // Let the constructor do the job
        });
        bluePanel.add(start);

        JButton profile = new JButton();
        profile.setPreferredSize(new Dimension(150, 50));
        profile.setText("PROFILE");
        bluePanel.add(profile);

        JButton exit = new JButton();
        exit.setPreferredSize(new Dimension(150, 50));
        exit.setText("EXIT");
        bluePanel.add(exit);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1000, 500);
        frame.setVisible(true);
        frame.add(bluePanel);
        frame.add(leftPanel);
        frame.add(rightPanel);

    }
    
    
}
