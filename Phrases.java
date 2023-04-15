import java.nio.file.*;
import java.io.*;
import java.util.LinkedList;

public class Phrases {
    private LinkedList<String> gamePhrases = new LinkedList<String>();

    // When loading this we should read the file that contains our game phrases
    // these phrases are then loaded to the gamePhrases Linked List
    public Phrases() {
        readPhraseFile();
    }

    public void readPhraseFile() {
        Path filePath = Paths.get("Phrases/phrase.txt");
        InputStream fileInput = null;

        try {
            // Check File Exists
            if (Files.exists(filePath)) {
                fileInput = new FileInputStream(filePath.toString());
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInput));
                Boolean loadingPhrases = true;
                String inputPhrase = "";

                while ( loadingPhrases ) {
                    inputPhrase = reader.readLine();
                    if ( inputPhrase != null ) {
                        gamePhrases.add(inputPhrase);                 
                    } else {
                        loadingPhrases = false;
                    }
                }
                fileInput.close();
            }
        } catch (IOException e) {
            System.out.println("Error processing phrase file");
        }
    }

    public void writePhraseFile() {
        Path filePath = Paths.get("Phrases/phrase.txt");
        byte[] outputData;
        OutputStream fileOutput = null;

        try {
            if ( Files.exists(filePath) ) {                
                String outputPhrase = "";
                fileOutput = new FileOutputStream(filePath.toString());
                
                for ( int i = 0; i < gamePhrases.size(); i++ ) {
                    outputPhrase = gamePhrases.get(i).concat("\n");                    
                    outputData = outputPhrase.getBytes();
                    fileOutput.write(outputData);
                }

                fileOutput.flush();
                fileOutput.close();
            }
        } catch (IOException e) {
            System.out.println("Error writing phrase file");
        }
    }

    public int getPhraseCount() {
        return gamePhrases.size();
    }

    public String getPhrase(int phraseNumber) {
        return gamePhrases.get(phraseNumber);
    }

    public void addPhrase(String inputPhrase) {
        gamePhrases.add(inputPhrase);
    }

    public void clearPhraseList() {
        gamePhrases.clear();
    }
    
}
