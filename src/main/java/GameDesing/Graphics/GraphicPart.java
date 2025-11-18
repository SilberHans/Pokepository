package GameDesing.Graphics;

import GameDesing.Game;
import javax.swing.JFrame;

public class GraphicPart {

    // Estados del juego
    public static final int STATE_START = 0;
    public static final int STATE_BATTLE = 1;
    public static final int STATE_TRADER = 2;
    public static final int STATE_SELECTOR = 3;
    public static final int STATE_NURSE_JOY = 4; // NUEVO ESTADO

    // Estado actual
    public static int gameState = STATE_START;
    // Ventana principal
    private static JFrame window;

    // Paneles del juego
    private static StartPanel startPanel;
    public static BattlePanel battlePanel;
    public static TraderPanel traderPanel;
    public static pkSelectorPanel selectorPanel;
    public static NurseJoyPanel nurseJoyPanel; // NUEVO PANEL

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
        nurseJoyPanel = null; // NUEVO

        // Mostrar el panel inicial
        cambiarPanel(STATE_START);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    public static void createGame(String trainer1Name, String trainer2Name) {
        System.out.println("DEBUG: [GraphicPart] 1. Creando Game(name1, name2)...");
        game = new Game(trainer1Name, trainer2Name); // <- El error podría estar aquí
        System.out.println("DEBUG: [GraphicPart] 2. Game Creado.");
        
        System.out.println("DEBUG: [GraphicPart] 3. Creando pkSelectorPanel...");
        selectorPanel = new pkSelectorPanel(game);
        System.out.println("DEBUG: [GraphicPart] 4. pkSelectorPanel Creado.");
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
            // NUEVO CASE PARA NURSE JOY
            case STATE_NURSE_JOY -> {
                if (nurseJoyPanel == null) { // Crear el panel si no existe
                    nurseJoyPanel = new NurseJoyPanel(game);
                }
                window.add(nurseJoyPanel);
                nurseJoyPanel.startGameThread();
                nurseJoyPanel.requestFocusInWindow();
            }
        }

        window.pack();
        window.revalidate();
        window.repaint();
    }
}