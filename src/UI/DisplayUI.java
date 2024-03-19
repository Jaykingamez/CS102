package UI;

import java.awt.*;        // Using AWT layouts

import javax.swing.*;     // Using Swing components and containers

public class DisplayUI{

    public DisplayUI(){
        ImageIcon icon = new ImageIcon("images/2c.gif");
        JLabel label = new JLabel();
        String playerInfo = String.format("Player info %s%nMoney: %d%nBet: %d ",
         "Yeow Leong", 500, 100);
        JTextArea playerInfoText = new JTextArea(playerInfo);
        //label.setText();
        ImageIcon yeowLeong = new ImageIcon("images/yllee.jpg");
        Image image = yeowLeong.getImage(); // transform it 
        Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
        yeowLeong = new ImageIcon(newimg);  // assign to a new ImageIcon instance
        label.setIcon(yeowLeong);

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

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        redPanel.setBounds(0, 0, 250, 250);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.blue);
        bluePanel.setBounds(750, 0, 250, 250);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.green);
        greenPanel.setBounds(250, 0, 500, 250);

        JPanel blackPanel = new JPanel();
        blackPanel.setBackground(Color.black);
        blackPanel.setBounds(250, 250, 500, 250);

        JPanel pinkPanel = new JPanel();
        pinkPanel.setBackground(Color.pink);
        pinkPanel.setBounds(0, 250, 250, 250);

        JButton fold = new JButton();
        fold.setPreferredSize(new Dimension(150, 50));
        fold.setText("FOLD");

        JButton check = new JButton();
        check.setPreferredSize(new Dimension(150, 50));
        check.setText("CHECK");

        pinkPanel.add(fold);
        pinkPanel.add(check);

        JPanel purplePanel = new JPanel();
        purplePanel.setBackground(Color.magenta);
        purplePanel.setBounds(750, 250, 250, 250);

        JButton raise = new JButton();
        raise.setPreferredSize(new Dimension(150, 50));
        raise.setText("RAISE");

        JButton call = new JButton();
        call.setPreferredSize(new Dimension(150, 50));
        call.setText("CALL");

        purplePanel.add(raise);
        purplePanel.add(call);
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1000, 500);
        frame.setVisible(true);

        redPanel.add(playerInfoText);
        redPanel.add(label);

        greenPanel.add(label2);
        greenPanel.add(label3);
        greenPanel.add(label4);
        greenPanel.add(label5);

        bluePanel.add(winConditionText);

        blackPanel.add(label6);
        blackPanel.add(label7);

        frame.add(redPanel);
        frame.add(bluePanel);
        frame.add(greenPanel);
        frame.add(blackPanel);
        frame.add(pinkPanel);
        frame.add(purplePanel);
    }

    public static void main(String[] args) {
        ImageIcon icon = new ImageIcon("images/2c.gif");
        JLabel label = new JLabel();
        String playerInfo = String.format("Player info %s%nMoney: %d%nBet: %d ",
         "Yeow Leong", 500, 100);
        JTextArea playerInfoText = new JTextArea(playerInfo);
        //label.setText();
        ImageIcon yeowLeong = new ImageIcon("images/yllee.jpg");
        Image image = yeowLeong.getImage(); // transform it 
        Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly  
        yeowLeong = new ImageIcon(newimg);  // assign to a new ImageIcon instance
        label.setIcon(yeowLeong);

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

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        redPanel.setBounds(0, 0, 250, 250);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.blue);
        bluePanel.setBounds(750, 0, 250, 250);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.green);
        greenPanel.setBounds(250, 0, 500, 250);

        JPanel blackPanel = new JPanel();
        blackPanel.setBackground(Color.black);
        blackPanel.setBounds(250, 250, 500, 250);

        JPanel pinkPanel = new JPanel();
        pinkPanel.setBackground(Color.pink);
        pinkPanel.setBounds(0, 250, 250, 250);

        JButton fold = new JButton();
        fold.setPreferredSize(new Dimension(150, 50));
        fold.setText("FOLD");

        JButton check = new JButton();
        check.setPreferredSize(new Dimension(150, 50));
        check.setText("CHECK");

        pinkPanel.add(fold);
        pinkPanel.add(check);

        JPanel purplePanel = new JPanel();
        purplePanel.setBackground(Color.magenta);
        purplePanel.setBounds(750, 250, 250, 250);

        JButton raise = new JButton();
        raise.setPreferredSize(new Dimension(150, 50));
        raise.setText("RAISE");

        JButton call = new JButton();
        call.setPreferredSize(new Dimension(150, 50));
        call.setText("CALL");

        purplePanel.add(raise);
        purplePanel.add(call);
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1000, 500);
        frame.setVisible(true);

        redPanel.add(playerInfoText);
        redPanel.add(label);

        greenPanel.add(label2);
        greenPanel.add(label3);
        greenPanel.add(label4);
        greenPanel.add(label5);

        bluePanel.add(winConditionText);

        blackPanel.add(label6);
        blackPanel.add(label7);

        frame.add(redPanel);
        frame.add(bluePanel);
        frame.add(greenPanel);
        frame.add(blackPanel);
        frame.add(pinkPanel);
        frame.add(purplePanel);


        // // Run GUI codes in Event-Dispatching thread for thread-safety
        // SwingUtilities.invokeLater(new Runnable() {
        //     @Override
        //     public void run() {
        //     new DisplayUI();  // Let the constructor do the job
        //     }
        // });
    }
}
