public class Player {
    private String firstName;
    private String lastName;

    public Player() {
        firstName = null;
        lastName = null;
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
