import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGame extends JFrame {
    private int randomNumber;
    private int attempts;
    private int maxAttempts = 10;
    private int score;
    private int round = 1;
    
    private JTextField inputField;
    private JButton guessButton;
    private JLabel messageLabel, attemptLabel, scoreLabel, roundLabel;

    public GuessTheNumberGame() {
        // GUI Setup
        setTitle("Guess the Number Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // Labels and TextField
        messageLabel = new JLabel("Guess a number between 1 and 100:", SwingConstants.CENTER);
        attemptLabel = new JLabel("Attempts left: " + maxAttempts, SwingConstants.CENTER);
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        roundLabel = new JLabel("Round: 1", SwingConstants.CENTER);
        inputField = new JTextField();
        
        // Guess Button
        guessButton = new JButton("Guess");

        // Adding components to JFrame
        add(messageLabel);
        add(inputField);
        add(guessButton);
        add(attemptLabel);
        add(scoreLabel);
        add(roundLabel);

        // Button ActionListener
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeGuess();
            }
        });

        resetGame();

        // Center the frame
        setLocationRelativeTo(null);
    }

    private void makeGuess() {
        try {
            int guess = Integer.parseInt(inputField.getText());

            // Check if the guess is valid
            if (guess < 1 || guess > 100) {
                messageLabel.setText("Please enter a number between 1 and 100.");
                return;
            }

            // Check the guess
            attempts--;
            attemptLabel.setText("Attempts left: " + attempts);

            if (guess == randomNumber) {
                score += 10 * attempts;
                scoreLabel.setText("Score: " + score);
                JOptionPane.showMessageDialog(this, "Congratulations! You guessed the number.");
                nextRound();
            } else if (guess > randomNumber) {
                messageLabel.setText("Too high! Try again.");
            } else {
                messageLabel.setText("Too low! Try again.");
            }

            // Check if attempts are exhausted
            if (attempts == 0) {
                JOptionPane.showMessageDialog(this, "No attempts left! The number was " + randomNumber);
                nextRound();
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
    }

    private void nextRound() {
        round++;
        if (round <= 3) {  // Limit rounds to 3
            roundLabel.setText("Round: " + round);
            resetGame();
        } else {
            JOptionPane.showMessageDialog(this, "Game Over! Your final score is: " + score);
            System.exit(0);  // End game
        }
    }

    private void resetGame() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attempts = maxAttempts;
        inputField.setText("");
        attemptLabel.setText("Attempts left: " + maxAttempts);
        messageLabel.setText("Guess a number between 1 and 100:");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuessTheNumberGame game = new GuessTheNumberGame();
            game.setVisible(true);
        });
    }
}
