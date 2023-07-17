package mainpackage.bullsandcows;

import mainpackage.Keyboard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class consists of methods for running the Game Bulls and Cows.
 */

public class BullsAndCows extends Game {
    public static String gameType = "BullsAndCows";
    public static final int MAX_ATTEMPTS = 7;
    public static final int CODE_LENGTH = 4;
    public String compSecretCode;
    public static String playerSecretCode;


    public BullsAndCows() {
        super(gameType);
        compSecretCode = computer.compSecretCode();
        playerSecretCode = player.makeCode();
    }

    public String getGameName() {
        return "Bulls and Cows";
    }

    public static String getPlayerSecretCode() {
        return playerSecretCode;
    }

    @Override
    protected void playTurn() {
        // keep asking the user to create Secrete Code until it is valid
        while(!validator.validateCode(playerSecretCode)){
            playerSecretCode = player.makeCode();
        }

        // Sentence for testing. Comment
        //System.out.println("Computer Secret Code TEST: " + compSecretCode);

        List<GameData> gameDataList = new ArrayList<>();
        String winner = "";

        for (int i = 1; i <= MAX_ATTEMPTS; i++) {

            System.out.print("Make a guess: ");
            String playerGuess = player.makeGuess();

            // keep asking the user for a guess until it is valid
            while (!validator.validateCode(playerGuess)) {
                playerGuess = player.makeGuess();
            }

            System.out.println("-----------------------------------");
            System.out.println("Turn: " + i);

            //Player guess printed, calculate Bulls, calculate Cows and print result
            player.printPlayerResult(playerGuess, compSecretCode);

            //Computer guess printed, calculate Bulls, calculate Cows and print result
            String compGuess = computer.compGuess();
            computer.printCompResult(compGuess, playerSecretCode);

            //If player wins
            if (resultCalculator.checkWin(playerGuess, compSecretCode, gameType)) {
                System.out.println("You win!!!");
                winner = player.name;
                gameDataList.add(new GameData(playerGuess, compGuess,
                        resultCalculator.calculateBulls(playerGuess, compSecretCode), resultCalculator.calculateCows(playerGuess, compSecretCode),
                        resultCalculator.calculateBulls(compGuess, playerSecretCode), resultCalculator.calculateCows(compGuess, playerSecretCode),
                        winner, i,null));
                break;
            }

            //If computer wins
            if (resultCalculator.checkWin(compGuess, playerSecretCode, gameType)) {
                System.out.println("Computer win!!!");
                winner = "Computer";
                gameDataList.add(new GameData(playerGuess, compGuess,
                        resultCalculator.calculateBulls(playerGuess, compSecretCode), resultCalculator.calculateCows(playerGuess, compSecretCode),
                        resultCalculator.calculateBulls(compGuess, playerSecretCode), resultCalculator.calculateCows(compGuess, playerSecretCode),
                        winner, i, null));
                break;
            }

            //If no winner
            if (i == MAX_ATTEMPTS) {
                System.out.println("No winner!\nThe secret number was: " + compSecretCode);
                winner = "No one ";
                gameDataList.add(new GameData(playerGuess, compGuess,
                        resultCalculator.calculateBulls(playerGuess, compSecretCode), resultCalculator.calculateCows(playerGuess, compSecretCode),
                        resultCalculator.calculateBulls(compGuess, playerSecretCode), resultCalculator.calculateCows(compGuess, playerSecretCode),
                        winner, i, null));
                break;
            }

            gameDataList.add(new GameData(playerGuess, compGuess,
                    resultCalculator.calculateBulls(playerGuess, compSecretCode), resultCalculator.calculateCows(playerGuess, compSecretCode),
                    resultCalculator.calculateBulls(compGuess, playerSecretCode), resultCalculator.calculateCows(compGuess, playerSecretCode),
                    null, i, null));
        }

        //ask player if they want to save result in a File
        String saveResult;
        while (true) {
            System.out.println("Do you want to save the result in a File? (Y/N)");
            saveResult = Keyboard.readInput().toUpperCase();
            if (saveResult.equals("Y") || saveResult.equals("N")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }

        // save result in a File with specified file name by player
        if (saveResult.equals("Y")) {
            System.out.print("Enter the file name: ");
            String fileName = Keyboard.readInput();
            gameSaveBCResult(fileName, playerSecretCode, compSecretCode, gameDataList, winner);
        }
    }

    /**
     * This method saves the result of the game in a file.
     */
    public static void gameSaveBCResult(String fileName, String playerSecretCode, String compSecretCode, List<GameData> gameDataList, String winner) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("Bulls & Cows game result\n");
            bw.write("Player's secret code: " + playerSecretCode + "\n");
            bw.write("Computer's secret code: " + compSecretCode + "\n");

            int turn = 1;
            for (GameData gameData : gameDataList) {
                bw.write("------");
                bw.newLine();
                bw.write("Turn " + turn + ":");
                bw.newLine();
                bw.write("You guessed " + gameData.playerGuess + ", scoring " + gameData.playerBulls + " bull and " + gameData.playerCows + " cow.");
                bw.newLine();
                bw.write("Computer guessed " + gameData.compGuess + ", scoring " + gameData.compBulls + " bull and " + gameData.compCows + " cow.");
                bw.newLine();
                turn++;
            }

            bw.write(winner + " win!!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the result.");
            e.printStackTrace();
        }
    }
}
