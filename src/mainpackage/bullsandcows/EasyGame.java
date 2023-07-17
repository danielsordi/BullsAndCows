package mainpackage.bullsandcows;

/**
 * This class represents the computer Easy AI level in the Bulls and Cows game.
 */
public class EasyGame extends Computer{

    public EasyGame() { super(); }

    /**
     * Generates a random 4 digit code
     *
     * @return computer guess.
     */
    @Override
    public String compGuess() {
        String compGuess = generate.generateCode();

        return compGuess;
    }
}
