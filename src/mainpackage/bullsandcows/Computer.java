package mainpackage.bullsandcows;

public abstract class Computer {
    public Generator generate;
    public ResultCalculator resultCalculator;

    public Computer() {
        resultCalculator = new ResultCalculator();
        generate = new Generator();
    }

    /**
     *  Prints the computer's guess and result (bulls and cows) to the console
     *
     * @param compGuess - the computer's guess
     * @param playerSecretCode - the player's secret code
     */
    public void printCompResult(String compGuess, String playerSecretCode) {
        System.out.println("\nComputer guess: " + compGuess);
        int compBulls = resultCalculator.calculateBulls(compGuess, playerSecretCode);
        int compCows = resultCalculator.calculateCows(compGuess, playerSecretCode);
        System.out.println("Result: " + compBulls + " Bulls and " + compCows + " Cows"
                + "\n-----------------------------------");
    }

    /**
     * Generates the computer's secret code and give instructions to the player.
     *
     * @return - the computer's secret code
     */
    public String compSecretCode() {
        // Computer generate secret code. Tell player the rules
        String compSecretCode = generate.generateCode();
        System.out.println("-----------------------------------");
        System.out.println("The Computer secret code has also been created. "
                + "\nMake sure your guess has 4 unique numbers and each number from 0 to 9"
                + "\nYou have " + BullsAndCows.MAX_ATTEMPTS + " attempts to figure out the code. Good luck!"
                + "\n-----------------------------------");

        //Sentence for testing. Comment the line below:
        //System.out.println("Computer secret number: " + compSecretCode);

        return compSecretCode;
    }

    /**
     * Abstract method for generating the computer's guess
     * Each Child will have a different implementation of this method
     *
     * @return The computer's guess
     */
    public abstract String compGuess();

}
