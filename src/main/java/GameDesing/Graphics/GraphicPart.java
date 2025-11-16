package GameDesing.Graphics;

import GameDesing.Game;
import javax.swing.JFrame;

public class GraphicPart {

    // Estados del juego
    public static final int STATE_START = 0;
    public static final int STATE_BATTLE = 1;
    public static final int STATE_TRADER = 2;
    public static final int STATE_SELECTOR = 3;

    // Estado actual
    public static int gameState = STATE_START;
    // Ventana principal
    private static JFrame window;

    // Paneles del juego
    private static StartPanel startPanel;
    public static BattlePanel battlePanel;
    public static TraderPanel traderPanel;
    public static pkSelectorPanel selectorPanel;

    //Conectando GAME
    private static Game game;
    
    
    public static void main(String[] args) {
        // Crear ventana
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("PokeBattle");

        //
        
        // Inicializar paneles
        startPanel = new StartPanel();
        battlePanel = null;
        traderPanel = null;
        selectorPanel = null;

        // Mostrar el panel inicial
        cambiarPanel(STATE_START);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    public static void createGame() {
        game = new Game();
        System.out.println("¡New Game creado con Pokémon y Trainers!");

        selectorPanel = new pkSelectorPanel(game);
    }
        
    public static void cambiarPanel(int nuevoEstado) {
        if (window.getContentPane().getComponentCount() > 0) {
            window.getContentPane().removeAll();
        }

        gameState = nuevoEstado;

        switch (gameState) {
            case STATE_START -> {
                window.add(startPanel);
                startPanel.startGameThread();
                startPanel.requestFocusInWindow();
            }
            case STATE_BATTLE -> {
                window.add(battlePanel);
                battlePanel.startGameThread();
                battlePanel.requestFocusInWindow();
            }
            case STATE_TRADER -> {
                window.add(traderPanel);
                traderPanel.startGameThread();
                traderPanel.requestFocusInWindow();
            }
            case STATE_SELECTOR -> {
                window.add(selectorPanel);
                selectorPanel.startGameThread();
                selectorPanel.requestFocusInWindow();
            }
        }

        window.pack();
        window.revalidate();
        window.repaint();
    }
}
