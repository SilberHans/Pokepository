package GameDesing.Graphics;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private JPanel currentPanel;

    public GameWindow() {
        setTitle("PokeArcade");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // Iniciar con el menú principal
        showPanel(new BattlePanel());
    }

    public void showPanel(JPanel panel) {
        if (currentPanel != null) remove(currentPanel);
        currentPanel = panel;
        add(currentPanel);
        revalidate();
        repaint();
    } 
}
