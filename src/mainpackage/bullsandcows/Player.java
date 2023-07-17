package mainpackage.bullsandcows;
import mainpackage.Keyboard;

public class Player {

    public String name;
    public ResultCalculator resultCalculator;

    public Player() {
        resultCalculator = new ResultCalculator();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String makeGuess() {
        return Keyboard.readInput();
    }

    /**
     *  Prints the player's guess and result (bulls and cows) to the console
     *
     * @param playerGuess - the player's guess
     * @param compSecretCode - the computer's secret code
     */
    public void printPlayerResult(String playerGuess, String compSecretCode) {
        System.out.println("Your guess: " + playerGuess);
        int bulls = resultCalculator.calculateBulls(playerGuess, compSecretCode);
        int cows = resultCalculator.calculateCows(playerGuess, compSecretCode);
        System.out.println("Result: " + bulls + " Bulls and " + cows + " Cows");
    }

    /**
     * Method to create a player secret code
     *
     * @return player's input
     */
    public String makeCode() {
        //Ask player to make player secret code
        System.out.print("Make your secret code: ");
        return Keyboard.readInput();
    }
}
