package mainpackage.bullsandcows;

/**
 * This class validates secret codes and secret words for the games.
 */
public class Validator {

    /**
     * Validates whether the player's guess is valid
     *
     * @param playerGuess the player guess
     * @return true if the guess is valid
     */
    public boolean validateCode(String playerGuess) {
        // Step 1: Check that the length of the input is exactly four digits.
        if (playerGuess.length() != 4) {
            System.out.println("Guess must be a four-digit number."
                    + "\nTry again");
            return false;
        }

        // Step 2: Check that each character is a digit between 0 and 9.
        for (int i = 0; i < playerGuess.length(); i++) {
            char c = playerGuess.charAt(i);
            if (c < '0' || c > '9') {
                System.out.println("Guess must be a four-digit number with digits from 0 to 9."
                        + "\nTry again");
                return false;
            }
        }

        // Step 3: Check that each digit is unique.
        for (int i = 0; i < 4; i++) {
            char digit = playerGuess.charAt(i);
            for (int j = i + 1; j < 4; j++) {
                if (digit == playerGuess.charAt(j)) {
                    System.out.println("Guess must be a four-digit number with unique digits."
                            + "\nTry again");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Validates whether the player's guess is valid
     *
     * @param playerGuess the player guess
     * @return true if the guess is valid
     */
    public boolean validateWord(String playerGuess) {

        boolean b = playerGuess.length() == Wordle.CODE_LENGTH && playerGuess.matches("[a-zA-Z]+");
        if (b  == false) {
            System.out.println("incorrect input. Word must have " + Wordle.CODE_LENGTH + " characters.");
            return false;
        }

        return true;

    }
}
