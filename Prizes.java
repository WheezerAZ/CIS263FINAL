import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Random;

public class Prizes extends Points {
    private int[] prizeWinnings = {-1,-1,-1};
    private String[] prizeList = new String[5];
    private Image[] prizeImages = new Image[5];

    public Prizes() {
        prizeList[0] = "a Potted Plant";
        prizeList[1] = "Half a Chocolate Bar";
        prizeList[2] = "a cat that quacks";
        prizeList[3] = "a CD Player that runs backwards";
        prizeList[4] = "a Parrot that mimes";   
        prizeImages[0]=new ImageIcon("images\\potted_plant.png").getImage();
        prizeImages[1]=new ImageIcon("images\\chocolate_bar.png").getImage();
        prizeImages[2]=new ImageIcon("images\\cat_duck.png").getImage();
        prizeImages[3]=new ImageIcon("images\\cdplayer.png").getImage();
        prizeImages[4]=new ImageIcon("images\\parrot_mime.png").getImage();         
    }

    public int getPrizeWinning(int prizeWinningNumber) {
        return prizeWinnings[prizeWinningNumber];
    }

    public void setPrizeWinning(int prizeWinningNumber, int prizeWinningValue) {
        prizeWinnings[prizeWinningNumber] = prizeWinningValue;
    }

    public Image getPrizeImage(int prizeNumber) {
        return prizeImages[prizeNumber];
    }

    public String getPrizeList(int prizeNumber) {
        return prizeList[prizeNumber];
    }

    public int pullRandomPrize() {
        int currentPrize = -1;
        Random generateRandom = new Random();
        boolean checkingRandomPrize = true;

        while ( checkingRandomPrize ) {
            currentPrize = generateRandom.nextInt(prizeList.length);

            boolean prizeUsed = false;
            for(int i = 0; i < prizeWinnings.length; i++) {
                if ( prizeWinnings[i] == currentPrize ) {
                    prizeUsed = true;
                    break;
                }
            }
            if ( ! prizeUsed ) {                
                checkingRandomPrize = false;
            }
        }

        return currentPrize;
    }

    public String getWinnings() {
        int totalPrizes = 0;
        for(int i = 0; i < prizeWinnings.length; i++) {
            if (prizeWinnings[i] >= 0) {
                totalPrizes++;
            }
        }

        return totalPrizes + " Prizes";
    }
    
}
