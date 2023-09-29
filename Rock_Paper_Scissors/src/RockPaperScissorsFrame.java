import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {

    private JTextArea resultsTextArea;
    private JLabel playerWinsLabel, computerWinsLabel, tiesLabel;
    private int playerWins, computerWins, ties;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Choose One"));
        addButton(buttonsPanel, "Rock");
        addButton(buttonsPanel, "Paper");
        addButton(buttonsPanel, "Scissors");
        addButton(buttonsPanel, "Quit");

        // Create stats panel
        JPanel statsPanel = new JPanel();
        playerWinsLabel = new JLabel("Player Wins: 0");
        computerWinsLabel = new JLabel("Computer Wins: 0");
        tiesLabel = new JLabel("Ties: 0");
        statsPanel.add(playerWinsLabel);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(tiesLabel);

        // Create results text area
        resultsTextArea = new JTextArea(10, 30);
        resultsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);

        // Add components to the frame
        add(buttonsPanel, BorderLayout.WEST);
        add(statsPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void addButton(Container container, String choice) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(choice + ".png"));
        // Scale the image to your desired size (e.g., 50x50)
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (choice.equals("Quit")) {
                    System.exit(0);
                } else {
                    playGame(choice);
                }
            }
        });
        container.add(button);
    }

    private void playGame(String playerChoice) {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        int computerChoiceIndex = random.nextInt(3);
        String computerChoice = choices[computerChoiceIndex];

        String result = determineWinner(playerChoice, computerChoice);
        resultsTextArea.append(result + "\n");

        if (result.contains("Player")) {
            playerWins++;
        } else if (result.contains("Computer")) {
            computerWins++;
        } else {
            ties++;
        }

        updateStats();
    }

    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            return "Player wins! (" + playerChoice + " beats " + computerChoice + ")";
        } else {
            return "Computer wins! (" + computerChoice + " beats " + playerChoice + ")";
        }
    }

    private void updateStats() {
        playerWinsLabel.setText("Player Wins: " + playerWins);
        computerWinsLabel.setText("Computer Wins: " + computerWins);
        tiesLabel.setText("Ties: " + ties);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissorsFrame frame = new RockPaperScissorsFrame();
            frame.setVisible(true);
        });
    }
}

