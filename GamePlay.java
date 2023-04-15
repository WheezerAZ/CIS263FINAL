import java.util.Random;

public class GamePlay {
    private Player[] playerList = new Player[3];
    private Phrases phrases = new Phrases();
    private String activePhrase;   
    private String activePlayingPhrase; 
    private int currentPlayer;
    private int currentRound;
    private int totalRounds;
    private boolean activeGame;
    private String winType;

    
    public GamePlay() {
        for(int i = 0; i < playerList.length; i++) {
            playerList[i] = new Player();
        }

        totalRounds = 3;
        activeGame = false;
        winType = "Points";
    }

    public Phrases getPhrases() {
        return phrases;
    }    

    public Player[] getPlayerList() {
        return playerList;
    }

    public void setPlayerList(Player[] inputPlayerList) {
        playerList = inputPlayerList;
    }

    public String getPlayer(int playerNumber) {
        String playerName = null;
        if ( playerList[playerNumber] != null ) {
            playerName = playerList[playerNumber].getCurrentUserName();
        }
        return playerName;
    }

    public String getPlayerFirstname(int playerNumber) {
        return playerList[playerNumber].getFirstName();
    }

    public void setPlayer(int playerNumber, String inputFirstName) {
        playerList[playerNumber].setName(inputFirstName);
    }

    public void setPlayer(int playerNumber, String inputFirstName, String inputLastName) {
        playerList[playerNumber].setName(inputFirstName,inputLastName);
    }

    public void setRound(int roundNumber) {
        currentRound = roundNumber;
    }

    public int getRound() {
        return currentRound;
    }

    public void setWinType(String inputWinType) {
        winType = inputWinType;
    }

    public String getWinType() {
        return winType;
    }

    public void setTotalRounds(int roundNumber) {
        totalRounds = roundNumber;
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public boolean getActiveGame() {
        return activeGame;
    }

    public void setActiveGame(boolean status) {
        activeGame = status;
    }

    public void setRandomActivePhrase() {
        int phraseCount = phrases.getPhraseCount();
        Random generateRandom = new Random();

        if ( activePhrase == null ) {
            activePhrase = "temporary";
        }

        String lastActivePhrase = activePhrase;
        while ( lastActivePhrase.equals(activePhrase) ) {
            int randomPhraseNumber = generateRandom.nextInt(phraseCount);

            System.out.println("Randomphrasenumber: " + randomPhraseNumber);
            activePhrase = phrases.getPhrase(randomPhraseNumber);
            activePlayingPhrase = activePhrase.replaceAll("\\S","_");
            System.out.println(activePhrase);
        }
    }

    public String getActivePhrase() {
        return activePhrase;
    }

    public String getActivePlayingPhrase() {
        return activePlayingPhrase;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int playerNumber) {
        currentPlayer = playerNumber;
    }

    // This function is to take a players guess. Checks it is a single letter
    // and nothing else and if found in puzzle when not previously guessed
    // returns the number guessed
    public int checkGuess(String playerGuess) throws MultipleLettersException, Exception {
        int lettersGuessed = 0;
        
        // First verify we have a single letter
        if ( playerGuess.length() > 1 ) {
            throw(new MultipleLettersException());
        } else if ( ! playerGuess.matches("[a-zA-Z]")) {
            throw(new Exception("Only letters A through Z accepted!"));
        } else if ( activePlayingPhrase.indexOf(playerGuess) == -1 ) {    
            System.out.println("Checking Here");
            char[] activePlayingPhraseLetters = activePlayingPhrase.toCharArray();

            for(int i = 0; i < activePhrase.length(); i++) {
                if ( activePhrase.toUpperCase().charAt(i) == playerGuess.toUpperCase().charAt(0)) {
                    activePlayingPhraseLetters[i] = activePhrase.charAt(i);
                    lettersGuessed++;
                }
            }
            
            if ( lettersGuessed > 0 ) {
                activePlayingPhrase = new String(activePlayingPhraseLetters); 
            }
        }

        return lettersGuessed;
    }
                                               
}
