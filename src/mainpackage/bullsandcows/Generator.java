package mainpackage.bullsandcows;

/**
 * Class to generate a random secret code or a random guess.
 */
public class Generator {

    public String generateCode() {
        String secretCode = "";
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int maxIndex = 9;

        for (int i = 0; i < 4; i++) {
            int randomIndex = (int) (Math.random() * (maxIndex +1));
            int digit = digits[randomIndex];
            secretCode += digit;
            digits[randomIndex] = digits[maxIndex];
            maxIndex--;
        }

        return secretCode;
    }

}
