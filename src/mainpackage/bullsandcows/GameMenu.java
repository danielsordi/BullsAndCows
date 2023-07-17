package mainpackage.bullsandcows;
import mainpackage.Keyboard;

/**
 * This class represents the main menu of the game where player can
 * choose between Bulls and Cows or Wordle.
 */
public class GameMenu {

    public void selectGame() {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Please chose a game to play: ");
            System.out.println("1. Bulls and Cows");
            System.out.println("2. Wordle");

            try {
                int game = Integer.parseInt(Keyboard.readInput());

                switch (game) {
                    case 1:
                        BullsAndCows bullsAndCows = new BullsAndCows();
                        bullsAndCows.start();
                        validInput = true;
                        break;
                    case 2:
                        Wordle wordle = new Wordle();
                        wordle.start();
                        validInput = true;
                        break;
                    default:
                        System.out.println("Invalid input. Please select a valid AI level (type 1 or 2):");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please select a valid AI level (type 1 or 2):");
            }
        }
    }

    public static void main(String[] args) {
        GameMenu gameMenu = new GameMenu();
        gameMenu.selectGame();
    }
}
