package mainpackage.bullsandcows;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the computer Medium AI level in the Bulls and Cows game.
 */
public class MediumGame extends Computer{

    private Set<String> guesses;
    public MediumGame() {
        super();
        guesses = new HashSet<>();}

    /**
     * This method generate randomly guesses keeping track of the guesses in a list, so it doesn't repeat them.
     *
     * @return computer guess
     */
    @Override
    public String compGuess() {
        String guess = null;

        //loop until the guess is unique
        while (guess == null || guesses.contains(guess)) {
            guess = generate.generateCode();
        }
        guesses.add(guess);

        return guess;
    }
}
