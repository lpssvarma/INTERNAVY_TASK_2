import java.util.Scanner;

public class HangmanGame {
    private String[] words = {"apple", "banana", "orange", "grape", "melon"};
    private String word;
    private char[] guessedLetters;
    private int numAttempts;
    private int maxAttempts = 6;

    public HangmanGame() {
        word = getRandomWord();
        guessedLetters = new char[word.length()];
        numAttempts = 0;
    }

    private String getRandomWord() {
        int index = (int) (Math.random() * words.length);
        return words[index];
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");
        System.out.println("Guess the word by entering one letter at a time.");

        while (true) {
            System.out.println();
            displayWordProgress();

            System.out.print("Enter your guess: ");
            char guess = scanner.next().charAt(0);

            if (!processGuess(guess)) {
                numAttempts++;
                displayHangman();
            }

            if (isGameOver()) {
                if (isWordGuessed()) {
                    System.out.println("Congratulations! You guessed the word correctly.");
                } else {
                    System.out.println("Game over! You ran out of attempts. The word was: " + word);
                }
                break;
            }
        }

        scanner.close();
    }

    private void displayWordProgress() {
        for (char letter : guessedLetters) {
            if (letter == '\u0000') {
                System.out.print("_ ");
            } else {
                System.out.print(letter + " ");
            }
        }
        System.out.println();
    }

    private boolean processGuess(char guess) {
        boolean isCorrectGuess = false;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                guessedLetters[i] = guess;
                isCorrectGuess = true;
            }
        }

        return isCorrectGuess;
    }

    private void displayHangman() {
        System.out.println("Attempts remaining: " + (maxAttempts - numAttempts));

        System.out.println(" _______ ");
        System.out.println(" |     | ");
        System.out.println(" |     " + (numAttempts >= 1 ? "O" : ""));
        System.out.println(" |    " + (numAttempts >= 3 ? "/" : "") + (numAttempts >= 2 ? "|" : "") + (numAttempts >= 4 ? "\\" : ""));
        System.out.println(" |    " + (numAttempts >= 5 ? "/" : "") + " " + (numAttempts >= 6 ? "\\" : ""));
        System.out.println("_|_");
    }

    private boolean isGameOver() {
        return numAttempts >= maxAttempts || isWordGuessed();
    }

    private boolean isWordGuessed() {
        for (char letter : guessedLetters) {
            if (letter == '\u0000') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        HangmanGame game = new HangmanGame();
        game.play();
    }
}
