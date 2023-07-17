package mainpackage.bullsandcows;
import java.util.HashMap;

/**
 * This class implements methods to calculate the results of both games Bulls and Cows and Wordle.
 */

public class ResultCalculator {

    /**
     * Calculate numbers of bulls (correct numbers in the correct position)
     *
     * @param guess
     * @param code Secret code
     * @return numbers of bulls
     */
    public int calculateBulls(String guess, String code) {
        int count = 0;
        for ( int i = 0; i < code.length() ; i++) {
            if (code.charAt(i) == guess.charAt(i)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Calculate numbers of cows (correct numbers in the wrong position)
     *
     * @param guess Computer/Player guess
     * @param code Computer/Player secret code
     * @return numbers of cows
     */
    public int calculateCows(String guess, String code) {
        int count = 0;
        HashMap<Character, Integer> guessCharCount = new HashMap<>();

        // Count the number of each character in the guess
        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);
            guessCharCount.put(guessChar, guessCharCount.getOrDefault(guessChar, 0) + 1);
        }

        // Find the matching characters in the secret code
        for (int i = 0; i < code.length(); i++) {
            char secretChar = code.charAt(i);
            if (guessCharCount.containsKey(secretChar) && guessCharCount.get(secretChar) > 0) {
                count++;
                guessCharCount.put(secretChar, guessCharCount.get(secretChar) - 1);
            }
        }

        return count;
    }

    /**
     * Check if guess is correct and winner
     *
     * @param guess Computer/Player guess
     * @param code Computer/Player secret code
     * @return true if 4 bulls
     */
    public boolean checkWin(String guess, String code, String gameType) {
        int bulls = calculateBulls(guess, code);
        try {
            if (gameType.equals("BullsAndCows")) {
                return bulls == BullsAndCows.CODE_LENGTH;
            }
        } catch (NullPointerException e) {
            // handle the exception by returning the result for Wordle game
            return bulls == Wordle.CODE_LENGTH;
        }

        return false;
    }
}
