package GameDesing.Graphics;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private JPanel panelMenu;
    private JPanel panelSeleccion;
    private JPanel panelBatalla;

    public GameWindow() {
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        panelMenu = new StartPanel();
        panelSeleccion = new pkSelectorPanel();
        panelBatalla = new BattlePanel();

        add(panelMenu, "menu");
        add(panelSeleccion, "seleccion");
        add(panelBatalla, "batalla");

        //cambiarPanel(GraphicPart.getEstado());
        setVisible(true);
    }

    public void cambiarPanel(int estado) {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        switch (estado) {
            case 0 -> cl.show(getContentPane(), "menu");
            case 1 -> cl.show(getContentPane(), "seleccion");
            case 2 -> cl.show(getContentPane(), "batalla");
        }
    }
}
