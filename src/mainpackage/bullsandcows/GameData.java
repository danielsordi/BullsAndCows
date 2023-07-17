package mainpackage.bullsandcows;

/**
 * Class to hold game data to be saved in a file.
 */
public class GameData {
    public String playerGuess;
    public String compGuess;
    public int playerBulls;
    public int playerCows;
    public int compBulls;
    public int compCows;
    public String winner;
    public int turn;
    public String secretWord;

    public GameData(String playerGuess, String compGuess, int playerBulls, int playerCows, int compBulls, int compCows, String winner, int turn, String secretWord) {
        this.playerGuess = playerGuess;
        this.compGuess = compGuess;
        this.playerBulls = playerBulls;
        this.playerCows = playerCows;
        this.compBulls = compBulls;
        this.compCows = compCows;
        this.winner = winner;
        this.turn = turn;
        this.secretWord = secretWord;
    }



}
