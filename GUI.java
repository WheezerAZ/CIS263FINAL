import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
    private String windowTitle = "Fortunate Disc";
    private JFrame mainWindow;
    private int windowHeight = 550;
    private int windowWidth = 800;
    private JMenuBar mainMenu;
    private JMenu playMenu;
    private JMenu settingsMenu;
    private JMenu aboutMenu;
    private JMenuItem playGame;
    private JMenuItem setPrizeType;
    private JMenuItem updatePhraseList;
    private JMenuItem updatePlayerList;
    private JPanel playerPanel = new JPanel();
    private JLabel[] playerNames = new JLabel[3];
    private JLabel[] playerLabels = new JLabel[3];
    private GamePlay currentGame = new GamePlay();
    
    // Setting up the page and game elements
    public GUI() {
        mainWindow = new JFrame(windowTitle);
        mainWindow.setSize(windowWidth, windowHeight);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setting up our menu bar
        mainMenu = new JMenuBar();
        playMenu = new JMenu("Game");
        settingsMenu = new JMenu("Settings");
        aboutMenu = new JMenu("About");

        // Adding Menu Items
        playGame = new JMenuItem("Play");
        setPrizeType = new JMenuItem("Set Prize");
        updatePlayerList = new JMenuItem("Update Players");
        updatePhraseList = new JMenuItem("Update Phrases");

        // Add Menu to main window
        mainWindow.setJMenuBar(mainMenu);

        // Play Menu Setup
        mainMenu.add(playMenu);
        playMenu.add(playGame);
        playMenu.setMnemonic('G');

        // Settings Menu Setup
        mainMenu.add(settingsMenu);
        settingsMenu.add(updatePlayerList);
        settingsMenu.add(setPrizeType);
        settingsMenu.add(updatePhraseList);
        settingsMenu.setMnemonic('S');

        // About Menu Setup
        mainMenu.add(aboutMenu);
        aboutMenu.setMnemonic('A');
        
        // Setup playerPanel as a 3 by 2 grid
        playerPanel.setLayout(new GridLayout(3,2));
        playerPanel.setBackground(Color.GREEN);
        playerPanel.setPreferredSize(new Dimension(250,100 ));
        playerPanel.setBounds(0,0,250,100);        

        // Set mainWindow to have no layout manager
        // Panels are arranged using setBounds functionality
        mainWindow.setLayout(null);
        mainWindow.add(playerPanel);

        // Set player labels        
        for(int i = 0; i < playerLabels.length; i++) {
            int playerNumber = i + 1;
            playerLabels[i] = new JLabel("Player " + playerNumber + ":");            
        }
        showPlayerList();        

        mainWindow.setVisible(true);

        // Add Action Listeners
        updatePlayerList.addActionListener(this);
    }

    public void showPlayerList() {
        playerPanel.removeAll();

        for(int i = 0; i < playerNames.length; i++) {
            String currentPlayer = currentGame.getPlayer(i);
            System.out.println(i + " " +currentPlayer);
            if ( currentPlayer != null ) {
                playerNames[i].setText(currentPlayer);
            } else {
                System.out.println("Should be adding this here");
                int playerNumber = i + 1;
                playerNames[i] = new JLabel("Waiting for Player " + playerNumber);
            }

            playerPanel.add(playerLabels[i]);
            playerPanel.add(playerNames[i]);
        }       

        mainWindow.repaint();
        mainWindow.setVisible(true);
    }

    public void updatePlayerList() {
        Boolean updatePlayerList = true;        
        Object[] playerOptions = {"Player 1", "Player 2", "Player 3"};

        while ( updatePlayerList ) {
            String playerChoice = (String)JOptionPane.showInputDialog(null,
                                    "Choose player to update",
                                    "Player Choice",
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    playerOptions,
                                    "Player 1");
            if ( playerChoice != null ) {
                String playerName = JOptionPane.showInputDialog(null,
                                        "What is the name of " + playerChoice + "(First Last)?");
                if ( playerName != null ) {
                    System.out.println(playerName);
                    for(int i = 0; i < playerOptions.length; i++) {
                        System.out.println(playerChoice);
                        if (playerOptions[i].equals(playerChoice)) {
                            if (playerName.length() > 3 && playerName.indexOf(" ") > 0) {
                                String[] nameParts = playerName.split(" ");
                                currentGame.setPlayer(i, nameParts[0], nameParts[1]);
                            } else {
                                currentGame.setPlayer(i,playerName);
                            }
                            break;
                        }
                    }                    
                } 
                showPlayerList();                

                int updateAnotherPlayer = JOptionPane.showConfirmDialog(null, "Update another player?");
                if ( updateAnotherPlayer == JOptionPane.NO_OPTION || 
                     updateAnotherPlayer == JOptionPane.CANCEL_OPTION) {
                    updatePlayerList = false;
                }
            } else {
                updatePlayerList = false;
            }                                                         
        }
    }

    // This will look at what event source and route the request appropriately
    public void actionPerformed(ActionEvent e) {                
        if ( e.getSource() == updatePlayerList ) {
            updatePlayerList();                              
        }
        
    }

    



    
    
}
