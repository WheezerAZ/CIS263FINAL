import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

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
    private JPanel playerWinPanel = new JPanel();
    private JLabel[] playerWinLabel = new JLabel[3];
    private GamePlay currentGame = new GamePlay();
    private JPanel phrasePanel = new JPanel();
    private JTextArea phraseText = new JTextArea();
    private JScrollPane phraseScrollPane;
    private JButton phraseUpdateButton = new JButton("Update Phrases");
    private JButton phraseCancelButton = new JButton("Cancel");
    private JPanel roundPanel = new JPanel();
    private JLabel roundText = new JLabel();
    private JPanel playingPhrasePanel = new JPanel();
    private JLabel playingPhraseText = new JLabel();
    private JPanel buttonPanel = new JPanel();
    private JButton gamePlayButton = new JButton();
    private JPanel gameMessagePanel = new JPanel();
    private JLabel gameMessage = new JLabel();
    private JPanel prizePanel = new JPanel();
    private JLabel prizeImage = new JLabel();
    
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
        
        // Setup playerPanel as a 1 by 6 grid
        playerPanel.setLayout(new GridLayout(1,6));
        playerPanel.setBackground(Color.CYAN);
        playerPanel.setPreferredSize(new Dimension(800,35));
        playerPanel.setBounds(0,0,800,35);   
        
        // Setup playerWinPanel as a 1 by 3 grid
        playerWinPanel.setLayout(new GridLayout(1,3));        
        playerWinPanel.setPreferredSize(new Dimension(800,70));
        playerWinPanel.setBounds(0,35,800,70);

        // Setup roundPanel
        roundPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //roundPanel.setBackground(Color.BLUE);
        roundPanel.setPreferredSize(new Dimension(800,100));
        roundPanel.setBounds(0,105,800,100);
        roundText.setText("");
        roundText.setFont(new Font("Arial",Font.PLAIN, 36));
        roundPanel.add(roundText);

        // Setup playing phrase window
        playingPhrasePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //playingPhrasePanel.setBackground(Color.GRAY);
        playingPhrasePanel.setPreferredSize(new Dimension(600,150));
        playingPhrasePanel.setBounds(0,205,800,150);
        playingPhraseText.setText("");
        playingPhraseText.setFont(new Font("Arial",Font.PLAIN,36));
        playingPhrasePanel.add(playingPhraseText);

        // Setup Prize Image Window
        prizePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        prizePanel.setPreferredSize(new Dimension(600,250));
        prizePanel.setBounds(0,105,800,250);
        prizeImage.setText("");
        prizePanel.add(prizeImage);

        // Setup gamePlay Button Panel
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //buttonPanel.setBackground(Color.PINK);
        buttonPanel.setPreferredSize(new Dimension(400,50));
        buttonPanel.setBounds(0,355,800,50);
        gamePlayButton.setText("Play");
        gamePlayButton.setFont(new Font("Arial",Font.PLAIN,24));
        buttonPanel.add(gamePlayButton);

        // Setup GamePlay Message Window
        gameMessagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //gameMessagePanel.setBackground(Color.MAGENTA);
        gameMessagePanel.setPreferredSize(new Dimension(600,100));
        gameMessagePanel.setBounds(0,405,800,100);
        gameMessage.setText("Press Play to Start Game");
        gameMessage.setFont(new Font("Arial",Font.PLAIN,24));
        gameMessagePanel.add(gameMessage);

        // Set mainWindow to have no layout manager
        // Panels are arranged using setBounds functionality
        mainWindow.setLayout(null);
        mainWindow.add(playerPanel);
        mainWindow.add(playerWinPanel);
        mainWindow.add(roundPanel);
        mainWindow.add(playingPhrasePanel);
        mainWindow.add(buttonPanel);
        mainWindow.add(gameMessagePanel);
        mainWindow.add(prizePanel);

        // Set player labels        
        for(int i = 0; i < playerLabels.length; i++) {
            int playerNumber = i + 1;
            playerLabels[i] = new JLabel("Player " + playerNumber + ":");            
        }
        showPlayerList();        
        updatePhraseList();
        phrasePanel.setVisible(false);
        roundPanel.setVisible(false);
        playingPhrasePanel.setVisible(false);
        buttonPanel.setVisible(true);
        gameMessagePanel.setVisible(true);
        prizePanel.setVisible(false);
      
        mainWindow.setVisible(true);

        // Add Action Listeners
        updatePlayerList.addActionListener(this);
        updatePhraseList.addActionListener(this);
        setPrizeType.addActionListener(this);
        phraseUpdateButton.addActionListener(this);
        phraseCancelButton.addActionListener(this);
        playGame.addActionListener(this);
        gamePlayButton.addActionListener(this);
    }

    public void showPlayerList() {        
        playerPanel.removeAll();
        playerWinPanel.removeAll();

        System.out.println("SHOW PLAYER LIST");

        for(int i = 0; i < playerNames.length; i++) {
            String currentPlayer = currentGame.getPlayer(i);            
            if ( currentPlayer != null ) {
                playerNames[i].setText(currentPlayer);                
            } else {                
                int playerNumber = i + 1;
                playerNames[i] = new JLabel("Waiting for Player " + playerNumber);                
            }

            if ( currentGame.getWinType().equals("Points")) {    
                String currentPlayerPoints = currentGame.getPlayerList()[i].getPlayerPoints().getWinnings();                                            
                playerWinLabel[i] = new JLabel(currentPlayerPoints);
            } else if ( currentGame.getWinType().equals("Money")) {
                String currentPlayerMoney = currentGame.getPlayerList()[i].getPlayerMoney().getWinnings();                                            
                playerWinLabel[i] = new JLabel(currentPlayerMoney);
            } else if ( currentGame.getWinType().equals("Prizes")) {
                String currentPlayerPrizes = currentGame.getPlayerList()[i].getPlayerPrizes().getWinnings();                                            
                playerWinLabel[i] = new JLabel(currentPlayerPrizes);
            }
            
            playerWinLabel[i].setFont(new Font("Arial",Font.PLAIN,18));

            playerPanel.add(playerLabels[i]);
            playerPanel.add(playerNames[i]);

            JPanel tmpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tmpPanel.add(playerWinLabel[i]);
            tmpPanel.setBackground(Color.CYAN);
            playerWinPanel.add(tmpPanel);
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
                    for(int i = 0; i < playerOptions.length; i++) {                        
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

    public void updatePrizeType() {
        // First lets make sure we are not in an active game. Can't Update this when game is going on
        if ( currentGame.getActiveGame() ) {
            JOptionPane.showMessageDialog(null,"Can't update Prize Type during active game.");
        } else {
            Object[] prizeTypeOptions = { "Points", "Money", "Prizes" };
            String currentPrizeType = currentGame.getWinType();

            String newPrizeType = (String)JOptionPane.showInputDialog(null,
                                        "Current Prize Type is " + currentPrizeType + ". Would you like to change?",
                                        "Prize Type",
                                        JOptionPane.PLAIN_MESSAGE,
                                        null,
                                        prizeTypeOptions,
                                        "Points");
            
            currentGame.setWinType(newPrizeType);
            showPlayerList();;
            mainWindow.repaint();
        }
    }

    // Read the current phrase list and let the user edit it
    public void updatePhraseList() {
        // Setup our phrasePanel and pull in our current list of phrases
        phraseText.setEditable(true);        
        phraseText.setWrapStyleWord(false);
        phraseScrollPane = new JScrollPane(phraseText, 
                                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        phraseScrollPane.setPreferredSize(new Dimension(500,400));

        Phrases currentPhraseList = currentGame.getPhrases();
        
        for (int i = 0; i < currentPhraseList.getPhraseCount(); i++ ) {            
            if ( i > 0 ) {
                phraseText.setText(phraseText.getText() + "\n" + currentPhraseList.getPhrase(i));
            } else {
                phraseText.setText(currentPhraseList.getPhrase(i));
            }
        }
        
        phrasePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        phrasePanel.add(phraseScrollPane);
        phrasePanel.setPreferredSize(new Dimension(800,500));
        phrasePanel.setBounds(0,0,800,500);
        phrasePanel.setBackground(Color.WHITE);
        phrasePanel.add(phraseUpdateButton);
        phrasePanel.add(phraseCancelButton);
        mainWindow.add(phrasePanel);                
    }

    public Boolean playerCheck() {
        Boolean checkingPlayers = true;

        while ( checkingPlayers ) {
            Boolean playersAdded = true;
            for(int i = 0; i < playerNames.length; i++) {
                String currentPlayer = currentGame.getPlayer(i);            
                if ( currentPlayer == null ) {
                    playersAdded = false;
                    break;                
                }
            }

            if ( ! playersAdded ) {
                JOptionPane.showMessageDialog(null,"Add all 3 players to start game.");
                updatePlayerList();                                
            } else {
                checkingPlayers = false;
            }
        }

        return true;
    }

    public void clearPlayerTotals() {
        for(int i = 0; i < playerNames.length; i++) {
            Player currentPlayer = currentGame.getPlayerList()[i];              
            currentPlayer.getPlayerPoints().setWinTotal(0);
            currentPlayer.getPlayerMoney().setWinTotal(0);
            currentPlayer.getPlayerPrizes().setPrizeWinning(0,-1);
            currentPlayer.getPlayerPrizes().setPrizeWinning(1,-1);
            currentPlayer.getPlayerPrizes().setPrizeWinning(2,-1);
        }
    }

    public void updateTotalForGuess(int guessCount) {
        // Lets pull the current player for this game
        Player currentPlayer = currentGame.getPlayerList()[currentGame.getCurrentPlayer()-1];
        
        if ( currentGame.getWinType().equals("Points")) {
            // Lets get the value of each guess
            int guessTotal = currentPlayer.getPlayerPoints().getCorrectGuess() * guessCount;
            int currentPlayerTotal = currentPlayer.getPlayerPoints().getWinTotal();
            currentPlayer.getPlayerPoints().setWinTotal(currentPlayerTotal + guessTotal);

            currentPlayerTotal = currentPlayer.getPlayerPoints().getWinTotal();
        } else if ( currentGame.getWinType().equals("Money") ) {
            // Lets get the value of each guess
            int guessTotal = currentPlayer.getPlayerMoney().getCorrectGuess() * guessCount;
            int currentPlayerTotal = currentPlayer.getPlayerMoney().getWinTotal();
            currentPlayer.getPlayerMoney().setWinTotal(currentPlayerTotal + guessTotal);

            currentPlayerTotal = currentPlayer.getPlayerMoney().getWinTotal();
        }
    }

    public void updateTotalForWin() {
        // Lets pull the current player for this game
        Player currentPlayer = currentGame.getPlayerList()[currentGame.getCurrentPlayer()-1];
        
        if ( currentGame.getWinType().equals("Points")) {
            // Lets get the value of each guess
            int winTotal = currentPlayer.getPlayerPoints().getSolvePuzzle();
            int currentPlayerTotal = currentPlayer.getPlayerPoints().getWinTotal();
            currentPlayer.getPlayerPoints().setWinTotal(currentPlayerTotal + winTotal);
        } else if ( currentGame.getWinType().equals("Money")) {
            // Lets get the value of each guess
            int winTotal = currentPlayer.getPlayerMoney().getSolvePuzzle();
            int currentPlayerTotal = currentPlayer.getPlayerMoney().getWinTotal();
            currentPlayer.getPlayerMoney().setWinTotal(currentPlayerTotal + winTotal);
        } else if ( currentGame.getWinType().equals("Prizes")) {
            // First lets pull a winning prize number
            int winningPrizeNumber = currentPlayer.getPlayerPrizes().pullRandomPrize();
            int currentRound = currentGame.getRound();
            currentPlayer.getPlayerPrizes().setPrizeWinning(currentRound - 1, winningPrizeNumber);
            ImageIcon prizeImageIcon = new ImageIcon(currentPlayer.getPlayerPrizes().getPrizeImage(winningPrizeNumber));            
            prizeImage.setIcon(prizeImageIcon);
            String prizeName = currentPlayer.getPlayerPrizes().getPrizeList(winningPrizeNumber);
            gameMessage.setText("Congratulations! You won a " + prizeName);
        }
    }

    // This will look at what event source and route the request appropriately
    public void actionPerformed(ActionEvent e) {                   
        if ( e.getSource() == updatePlayerList ) {
            updatePlayerList();                              
        } else if ( e.getSource() == updatePhraseList ) {
            playerPanel.setVisible(false);
            playerWinPanel.setVisible(false);
            phrasePanel.setVisible(true);            
            roundPanel.setVisible(false);
            playingPhrasePanel.setVisible(false);
            buttonPanel.setVisible(false);
            gameMessagePanel.setVisible(false);
            mainWindow.repaint();
            mainWindow.setVisible(true);
        } else if ( e.getSource() == phraseCancelButton ) {            
            playerPanel.setVisible(true);
            playerWinPanel.setVisible(true);
            phrasePanel.setVisible(false);            
            roundPanel.setVisible(false);
            playingPhrasePanel.setVisible(false);
            buttonPanel.setVisible(true);
            gameMessagePanel.setVisible(true);            
            mainWindow.repaint();
            mainWindow.setVisible(true);
        } else if ( e.getSource() == phraseUpdateButton ) {
            // Lets validate the user's input here
            if ( phraseText.getText() == null ) {
                JOptionPane.showMessageDialog(null,"Must have some phrases");                
            } else {
                String[] updatePhraseList = phraseText.getText().split("\n");

                // Let's make sure they are a minimum length and include only letters
                // and a space
                Boolean phraseListError = false;
                for (int i = 0; i < updatePhraseList.length; i++) {
                    String testPhrase = updatePhraseList[i].replaceAll("\\s", "");                    
                    if ( testPhrase.length() < 4 ||
                         ! testPhrase.matches("[a-zA-Z]+") ) {
                        phraseListError = true;                        
                    }
                }                

                if ( phraseListError ) {
                    JOptionPane.showMessageDialog(null,"Phrase must be 4 characters or longer. Only letters and spaces accepted");
                } else {
                    Phrases currentPhraseList = currentGame.getPhrases();
                    
                    currentPhraseList.clearPhraseList();
                    for (int i = 0; i < updatePhraseList.length; i++) {
                        currentPhraseList.addPhrase(updatePhraseList[i]);
                    }
                    currentPhraseList.writePhraseFile();

                    playerPanel.setVisible(true);
                    playerWinPanel.setVisible(true);
                    phrasePanel.setVisible(false);                    
                    roundPanel.setVisible(false);
                    playingPhrasePanel.setVisible(false);
                    buttonPanel.setVisible(true);
                    gameMessagePanel.setVisible(true);
                    mainWindow.repaint();
                    mainWindow.setVisible(true);
                }
            }                         
        } else if ( e.getSource() == playGame || 
                    ( e.getSource() == gamePlayButton && ! currentGame.getActiveGame() ) ) {
            // First lets make sure we have 3 players
            if( playerCheck() ) {
                // After player check lets initialize the game
                currentGame.setRandomActivePhrase();
                String currentPhrase = currentGame.getActivePhrase();
                System.out.println(currentPhrase);                                
                currentGame.setRound(1);                
                currentGame.setCurrentPlayer(1);
                currentGame.setActiveGame(true);


                // Populate the variables panels for playing the game
                roundText.setText("Round " + currentGame.getRound() + " of " + currentGame.getTotalRounds());
                playingPhraseText.setText(currentGame.getActivePlayingPhrase());
                gameMessage.setText(currentGame.getPlayerFirstname(currentGame.getCurrentPlayer()-1) +
                                    ", what letter is your guess?");
                gamePlayButton.setText("Guess");

                // Display the panels
                roundPanel.setVisible(true);
                playingPhrasePanel.setVisible(true);                
            }                 
        } else if ( e.getSource() == gamePlayButton && gamePlayButton.getText().equals("Guess")) {
            String playerGuess = null;
            while ( playerGuess == null ) {
                playerGuess = JOptionPane.showInputDialog(null,"What letter?"); 
            }

            try {
                int lettersGuessed = currentGame.checkGuess(playerGuess);              
                if ( lettersGuessed > 0 ) {
                    updateTotalForGuess(lettersGuessed);                    
                    showPlayerList();
                    playingPhraseText.setText(currentGame.getActivePlayingPhrase());
                    if ( currentGame.getActivePlayingPhrase().indexOf("_") == -1 ) {
                        if ( currentGame.getWinType().equals("Prizes")) {                                                        
                            playingPhrasePanel.setVisible(false);                    
                            roundPanel.setVisible(false);
                            prizePanel.setVisible(true);                            
                            mainWindow.repaint();                            
                        }
                        updateTotalForWin();
                        showPlayerList();
                        JOptionPane.showMessageDialog(null,"Congratulations " + 
                                        currentGame.getPlayerFirstname(currentGame.getCurrentPlayer()-1) +
                                        ", you guessed the puzzle!");

                        if ( currentGame.getWinType().equals("Prizes")) {                                                        
                            playingPhrasePanel.setVisible(true);                    
                            roundPanel.setVisible(true);
                            prizePanel.setVisible(false);
                        }
                        
                        currentGame.setRound(currentGame.getRound()+1);
                        if ( currentGame.getRound() <= currentGame.getTotalRounds() ) {
                            currentGame.setRandomActivePhrase();
                            String currentPhrase = currentGame.getActivePhrase();
                            System.out.println(currentPhrase);
                            if ( currentGame.getCurrentPlayer() == 3 ) {
                                currentGame.setCurrentPlayer(1);
                            } else {
                                currentGame.setCurrentPlayer(currentGame.getCurrentPlayer() + 1);
                            }

                            // Populate the variables panels for playing the game
                            roundText.setText("Round " + currentGame.getRound() + " of " + currentGame.getTotalRounds());
                            playingPhraseText.setText(currentGame.getActivePlayingPhrase());
                            gameMessage.setText(currentGame.getPlayerFirstname(currentGame.getCurrentPlayer()-1) +
                                                ", what letter is your guess?");
                            gamePlayButton.setText("Guess");
                        } else {
                            int newGame = JOptionPane.showConfirmDialog(null,
                                                                "Game over! Do you want to play another game?","New Game",
                                                                        JOptionPane.YES_NO_OPTION);
                            clearPlayerTotals();
                            if ( newGame == JOptionPane.YES_OPTION ) {
                                currentGame.setRandomActivePhrase();
                                String currentPhrase = currentGame.getActivePhrase();
                                System.out.println(currentPhrase);                                
                                currentGame.setRound(1);                
                                currentGame.setCurrentPlayer(1);
                                currentGame.setActiveGame(true);


                                // Populate the variables panels for playing the game
                                roundText.setText("Round " + currentGame.getRound() + " of " + currentGame.getTotalRounds());
                                playingPhraseText.setText(currentGame.getActivePlayingPhrase());
                                gameMessage.setText(currentGame.getPlayerFirstname(currentGame.getCurrentPlayer()-1) +
                                                    ", what letter is your guess?");                                
                            } else {
                                // Display the panels
                                roundPanel.setVisible(false);
                                playingPhrasePanel.setVisible(false);  
                                currentGame.setActiveGame(false);
                                gameMessage.setText("Press Play to Start Game");
                                gamePlayButton.setText("Play");
                            }                   
                            showPlayerList();         
                            mainWindow.repaint();
                        }
                    } else {
                        gameMessage.setText("Great guess " + 
                                            currentGame.getPlayerFirstname(currentGame.getCurrentPlayer()-1) +
                                            ", you may guess again.");
                    } 
                    showPlayerList();
                    mainWindow.repaint();
                } else {
                    JOptionPane.showMessageDialog(null,"That was incorrect. Next player.");
                    if ( currentGame.getCurrentPlayer() == 3 ) {
                        currentGame.setCurrentPlayer(1);
                    } else {
                        currentGame.setCurrentPlayer(currentGame.getCurrentPlayer() + 1);
                    }

                    // Populate the variables panels for playing the game                    
                    gameMessage.setText(currentGame.getPlayerFirstname(currentGame.getCurrentPlayer()-1) +
                                        ", what letter is your guess?");                    
                    mainWindow.repaint();
                }
            } catch (MultipleLettersException mle) {
                JOptionPane.showMessageDialog(null,mle.getMessage());
            } catch (Exception eventMessage) {
                JOptionPane.showMessageDialog(null,eventMessage.getMessage());
            }
        } else if ( e.getSource() == setPrizeType ) {
            updatePrizeType();
        }
        
    }
    
}
