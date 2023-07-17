package mainpackage.bullsandcows;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the computer Hard AI level in the Bulls and Cows game.
 */
public class HardGame extends Computer{

    private List<String> possibleCodes;

    /**
     * Constructor for HardGame create an ArrayList and add all possible codes into the list
     */
    public HardGame() {
        super();
        possibleCodes = new ArrayList<>();
        possibleCodes.addAll(possibleCodes());
    }

    /**
     * The computer guess is picked randomly from the list of possible codes
     * Every turn, the list of possible codes is updated removing the code that doesn't have chance to be a correct one.
     *
     * @return a computer guess
     */
    @Override
    public String compGuess() {
        String compGuess;

        // Get a guess randomly from the list of possible codes
        compGuess = getRandomCodeFromPossibleCodes();

        // Create a list of codes to be discarded
        List<String> discardedCodes = notMatchCodes(compGuess, possibleCodes,
                resultCalculator.calculateBulls(compGuess, BullsAndCows.getPlayerSecretCode()),
                resultCalculator.calculateCows(compGuess, BullsAndCows.getPlayerSecretCode()));

        // Remove the discarded codes from the list of possible codes
        possibleCodes.removeAll(discardedCodes);

        // Line below for testing. Make sure its commented
        // System.out.println(possibleCodes);

        return compGuess;
    }

    /**
     * The method gets a random code from the possible code list
     */
    private String getRandomCodeFromPossibleCodes() {
        int randomIndex = (int) (Math.random() * possibleCodes.size());
        return possibleCodes.get(randomIndex);
    }

    /**
     * This method take as a parameter the current guess and its bulls and cows and compare with the list of possible code.
     * Its then create a list of codes that doesn't have the same amount of bulls and cows as the current guess.
     *
     * @param guess computer current guess
     * @param possibleCodes list
     * @param bulls from the current guess compared with the player secret code
     * @param cows from the current guess compared with the player secret code
     */
    public static List<String> notMatchCodes(String guess, List<String> possibleCodes, int bulls, int cows) {
        List<String> notMatchingCodes = new ArrayList<>();

        for (String code : possibleCodes) {
            int codeBulls = 0;
            int codeCows = 0;

            for (int i = 0; i < code.length(); i++) {
                char c = code.charAt(i);

                if (guess.charAt(i) == c) {
                    codeBulls++;
                } else if (code.contains(Character.toString(guess.charAt(i)))) {
                    codeCows++;
                }
            }

            if (codeBulls != bulls && codeCows != cows) {
                notMatchingCodes.add(code);
            }
        }
        return notMatchingCodes;
    }

    /**
     * This method add the all possible codes of the game to start the possible code list
     *
     * @return the first possible code list
     */
    public List<String> possibleCodes() {

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if(j == i) {
                    continue;
                }
                for (int k = 0; k <= 9; k++) {
                    if(k == i || k == j) {
                        continue;
                    }
                    for (int l = 0; l <= 9; l++) {
                        if (l == i || l == j || l == k) {
                            continue;
                        }
                        String code = "" + i + j + k + l;
                        possibleCodes.add(code);
                    }
                }
            }
        }

        return possibleCodes;
    }
}
