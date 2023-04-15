public class Points implements Winnings {
    protected int correctGuess; // Holds the point value for a correct guess
    protected int solvePuzzle; // Holds the point value for solving the puzzle
    protected int winTotal; // Holds a player's total winnings

    public Points() {
        correctGuess = 10;
        solvePuzzle = 100;
        winTotal = 0;
    }

    public void setCorrectGuess(int inputCorrectGuess) {
        correctGuess = inputCorrectGuess;    
    }

    public int getCorrectGuess() {
        return correctGuess;
    }

    public void setSolvePuzzle(int inputSolvePuzzle) {
        solvePuzzle = inputSolvePuzzle;
    }

    public int getSolvePuzzle() {
        return solvePuzzle;
    }

    public void setWinTotal(int inputWinTotal) {
        winTotal = inputWinTotal;
    }

    public int getWinTotal() {
        return winTotal;
    }
    
    public String getWinnings() {
        return winTotal + " Points";
    }
}
