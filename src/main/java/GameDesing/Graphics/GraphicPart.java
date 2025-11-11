package GameDesing.Graphics;

import javax.swing.JFrame;


public class GraphicPart {
    public static void main(String[] args) {
       
        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("pokeBattle");
        
        BattlePanel panel2=new BattlePanel();
        TraderPanel panel3=new TraderPanel();
        StartPanel panel=new StartPanel();
        pkSelectorPanel panel4=new pkSelectorPanel();
        
        panel2.startGameThread();
        panel3.startGameThread();
        panel.startGameThread();
        panel4.startGameThread();
        
        window.add(panel3);
        window.pack();
        
        
       
        
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        
        panel.requestFocusInWindow();
        
    }
}
