public class GamePlay {
    private Player[] playerList = new Player[3];
    
    public GamePlay() {
        for(int i = 0; i < playerList.length; i++) {
            playerList[i] = new Player();
        }
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

    public void setPlayer(int playerNumber, String inputFirstName) {
        playerList[playerNumber].setName(inputFirstName);
    }

    public void setPlayer(int playerNumber, String inputFirstName, String inputLastName) {
        playerList[playerNumber].setName(inputFirstName,inputLastName);
    }
                                               
}
