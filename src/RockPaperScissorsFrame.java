import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    private JTextField playerWinsField, computerWinsField, tiesField;
    private JTextArea resultsArea;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Panel for buttons (Rock, Paper, Scissors, Quit)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose your move"));
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton rockButton = new JButton(new ImageIcon("src/rock.jpeg"));
        JButton paperButton = new JButton(new ImageIcon("src/paper.png"));
        JButton scissorsButton = new JButton(new ImageIcon("src/scissors.jpeg"));
        JButton quitButton = new JButton("Quit");

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

        // Stats panel for showing results
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(3, 2));
        statsPanel.add(new JLabel("Player Wins: "));
        playerWinsField = new JTextField("0", 5);
        statsPanel.add(playerWinsField);
        statsPanel.add(new JLabel("Computer Wins: "));
        computerWinsField = new JTextField("0", 5);
        statsPanel.add(computerWinsField);
        statsPanel.add(new JLabel("Ties: "));
        tiesField = new JTextField("0", 5);
        statsPanel.add(tiesField);

        // JTextArea for displaying game results
        resultsArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        resultsArea.setEditable(false);

        // Add panels to the frame
        add(buttonPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // Action listeners for buttons
        rockButton.addActionListener(new GameActionListener("Rock"));
        paperButton.addActionListener(new GameActionListener("Paper"));
        scissorsButton.addActionListener(new GameActionListener("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    // Helper method to determine computer's move
    private String getComputerMove() {
        String[] moves = {"Rock", "Paper", "Scissors"};
        Random rand = new Random();
        return moves[rand.nextInt(3)];
    }

    // Method to determine the winner
    private String determineWinner(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            ties++;
            tiesField.setText(String.valueOf(ties));
            return "It's a tie!";
        }

        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            playerWins++;
            playerWinsField.setText(String.valueOf(playerWins));
            return playerMove + " beats " + computerMove + ". Player wins!";
        } else {
            computerWins++;
            computerWinsField.setText(String.valueOf(computerWins));
            return computerMove + " beats " + playerMove + ". Computer wins!";
        }
    }

    // Action listener for game moves
    private class GameActionListener implements ActionListener {
        private String playerMove;

        public GameActionListener(String playerMove) {
            this.playerMove = playerMove;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String computerMove = getComputerMove();
            String result = determineWinner(playerMove, computerMove);
            resultsArea.append(playerMove + " vs. " + computerMove + ": " + result + "\n");
        }
    }
}
