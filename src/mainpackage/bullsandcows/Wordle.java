package mainpackage.bullsandcows;
import mainpackage.Keyboard;
import java.io.*;
import java.util.*;

/**
 * This class consists of methods for running the Game Wordle.
 */
public class Wordle extends Game {
    public String gameType;
    private static final int MAX_GUESSES = 6;
    public static final int CODE_LENGTH = 5;
    private static final String WORDLIST_FILENAME = "dictionary.txt";

    public Wordle() {
        super("Wordle");
    }

    public String getGameName() {
        return "Wordle";
    }

    @Override
    protected void playTurn() {
        String secretWord = (String) getRandomWord();
        List<GameData> gameDataList = new ArrayList<>();
        String winner = "";
        System.out.println("The secret word has been created. You have " + MAX_GUESSES + " guesses to guess it.");

        // Testing sentence. Keep it commented
        // System.out.println("The Secret Word For TEST: " + secretWord);

        for (int i = 1; i <= MAX_GUESSES; i++) {

            //Player make a guess
            System.out.print("Make a guess: ");
            String playerGuess = player.makeGuess();

            // keep asking the user for a guess until it is valid
            while (!validator.validateWord(playerGuess)) {
                playerGuess = player.makeGuess();
            }

            System.out.println("-----------------------------------");
            System.out.println("Turn: " + i);

            //Player guess printed, calculate Bulls, calculate Cows and print result
            player.printPlayerResult(playerGuess, secretWord);
            System.out.println("-----------------------------------");

            //If player wins
            if (resultCalculator.checkWin(playerGuess, secretWord, gameType)) {
                System.out.println("You win!!!");
                winner = "You win!!!";
                gameDataList.add(new GameData(playerGuess, null,
                        resultCalculator.calculateBulls(playerGuess, secretWord), resultCalculator.calculateCows(playerGuess, secretWord),
                        0, 0,
                        winner, i, secretWord));
                break;
            }

            //If no winner
            if (i == MAX_GUESSES) {
                System.out.println("You lost!\nThe secret word was: " + secretWord);
                winner = "You lost";
                gameDataList.add(new GameData(playerGuess, null,
                        resultCalculator.calculateBulls(playerGuess, secretWord), resultCalculator.calculateCows(playerGuess, secretWord),
                        0, 0,
                        winner, i, secretWord));
                break;
            }

            gameDataList.add(new GameData(playerGuess, null,
                    resultCalculator.calculateBulls(playerGuess, secretWord), resultCalculator.calculateCows(playerGuess, secretWord),
                    0, 0,
                    winner, i, secretWord));
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
            gameSaveWordleResult(fileName, secretWord, gameDataList, winner);
        }
    }

    /**
     * Get a random word with 5 character length from the specified file
     */
    private static Object getRandomWord() {
        List<String> wordList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(WORDLIST_FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(",");
                for (String word : words) {
                    if (word.length() == CODE_LENGTH && word.matches("[a-zA-Z]+")) {
                        wordList.add(word);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            BullsAndCows bc = new BullsAndCows();
            return bc;
        }

        // Check if the word list is empty
        if (wordList.isEmpty()) {
            System.out.println("Error: no valid words found in dictionary file.");
            System.exit(1);
        }

        // Get a random word from the worList
        Random random = new Random();
        return wordList.get(random.nextInt(wordList.size()));
    }

    /**
     * This method saves the result of the game in a file.
     */
    public static void gameSaveWordleResult(String fileName, String secretWord, List<GameData> gameDataList, String winner) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("Wordle version of Bulls & Cows game result\n");
            bw.write("Secret word: " + secretWord + "\n");

            int turn = 1;
            for (GameData gameData : gameDataList) {
                bw.write("------");
                bw.newLine();
                bw.write("Turn " + turn + ":");
                bw.newLine();
                bw.write("You guessed " + gameData.playerGuess + ", scoring " + gameData.playerBulls + " bull and " + gameData.playerCows + " cow.");
                bw.newLine();
                turn++;
            }

            bw.write(winner);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the result.");
            e.printStackTrace();
        }
    }
