package mainpackage.bullsandcows;
import mainpackage.Keyboard;

public abstract class Game {
    public String gameType;
    public Player player;
    public Computer computer;
    public Validator validator;
    public ResultCalculator resultCalculator;

    /**
     * Constructor of Game class
     *
     */

    public Game(String gameType) {
        this.gameType = gameType;
        player = new Player();
        printWelcomeMessage();
        if ("BullsAndCows".equals(gameType)) {
            computer = getComputerInstanceLevel();
        }
        validator = new Validator();
        resultCalculator = new ResultCalculator();
    }

    public void start() {

        playTurn();

        //ask player if they want to play again
        String playAgain;
        while (true) {
            System.out.println("Do you want to play again? (Y/N)");
            playAgain = Keyboard.readInput().toUpperCase();
            if (playAgain.equals("Y") || playAgain.equals("N")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }
        if (playAgain.equals("Y")) {
            System.out.println("-------------------------------------------------------");
            if ("BullsAndCows".equals(gameType)) {
                BullsAndCows bc = new BullsAndCows();
                bc.start();
            } else {
                Wordle wordle = new Wordle();
                wordle.start();
            }
        }
    }

    /**
     * Abstract method representing a single turn of the game
     */
    protected abstract void playTurn();

    /**
     * Returns the name of the game
     */
    public abstract String getGameName();

    /**
     * Prints a welcome message to the console
     */
    private void printWelcomeMessage() {
        System.out.print("Welcome to " + getGameName() + ". \nType your name: ");
        player.setName(Keyboard.readInput());
        System.out.println("Welcome " + player.name);
        System.out.println("-------------------------------------------------------");
    }

    /**
     * Prompts the player to select the AI level they want to play against
     *
     * @return An instance of the selected Computer subclass
     */
    public Computer getComputerInstanceLevel() {
        while (true) {
            String[] levelNames = {"Easy", "Medium", "Hard"};
            System.out.println("Please select the AI level you want to play against: ");
            for (int i = 0; i < levelNames.length; i++) {
                System.out.println(i + 1 + ": " + levelNames[i]);
            }

            try {
                int level = Integer.parseInt(Keyboard.readInput());

                switch (level) {
                    case 1:
                        return new EasyGame();
                    case 2:
                        return new MediumGame();
                    case 3:
                        return new HardGame();
                    default:
                        System.out.println("Invalid input. Please select a valid AI level (type 1 to 3):");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please select a valid AI level (type 1 to 3):");
            }
        }
    }

}
