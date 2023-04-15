public class Money extends Points {

    public Money() {
        correctGuess = 5;
        solvePuzzle = 25;
        winTotal = 0;
    }

    public String getWinnings() {
        return "$" + this.winTotal;
    }
    
}
