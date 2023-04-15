public class Player {
    private String firstName;
    private String lastName;
    private Points playerPoints;
    private Money playerMoney;
    private Prizes playerPrizes;

    public Player() {
        firstName = null;
        lastName = null;
        playerPoints = new Points();
        playerMoney = new Money();
        playerPrizes = new Prizes();
    }

    public Player(String inputFirstName) {
        firstName = inputFirstName;
        lastName = null;
    }

    public Player (String inputFirstName, String inputLastName) {
        firstName = inputFirstName;
        lastName = inputLastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String inputFirstName) {
        firstName = inputFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String inputLastName) {
        lastName = inputLastName;
    }

    public void setName(String inputFirstName) {
        firstName = inputFirstName;
        lastName = null;
    }

    public void setName (String inputFirstName, String inputLastName) {
        firstName = inputFirstName;
        lastName = inputLastName;
    }

    public void setPlayerPoints(Points inputPoints) {
        playerPoints = inputPoints;
    }

    public Points getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerMoney(Money inputMoney) {
        playerMoney = inputMoney;
    }

    public Points getPlayerMoney() {
        return playerMoney;
    }

    public Prizes getPlayerPrizes() {
        return playerPrizes;
    }

    // This function will only output firstname if the lastname is null
    public String getCurrentUserName() {
        String outputName = null;

        if ( firstName != null ) {
            outputName = firstName;
        } 

        if ( lastName != null ) {
            outputName = outputName + " " + lastName;
        }
        return outputName;
    }    
}
