import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean player1Turn = true;
    private JLabel statusLabel;

    public Main() {
        // Set up the frame
        setTitle("Tic-Tac-Toe");
        setSize(300, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Status label at the top
        statusLabel = new JLabel("Player 1's Turn (X)", SwingConstants.CENTER);
        add(statusLabel, BorderLayout.NORTH);

        // Panel to hold the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        // Initialize buttons and add to panel
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // Check if the button is already clicked
        if (!clickedButton.getText().equals("")) {
            return;
        }

        // Set the text to "X" or "O" depending on the player's turn
        if (player1Turn) {
            clickedButton.setText("X");
            statusLabel.setText("Player 2's Turn (O)");
        } else {
            clickedButton.setText("O");
            statusLabel.setText("Player 1's Turn (X)");
        }

        // Check for a win or draw
        if (checkForWin()) {
            statusLabel.setText("Player " + (player1Turn ? "1" : "2") + " Wins!");
            disableButtons();
        } else if (checkForDraw()) {
            statusLabel.setText("It's a Draw!");
        } else {
            // Switch turn
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin() {
        // Winning combinations (indexes in the buttons array)
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
                {0, 4, 8}, {2, 4, 6}              // diagonals
        };

        for (int[] combo : winningCombinations) {
            if (buttons[combo[0]].getText().equals(buttons[combo[1]].getText()) &&
                    buttons[combo[1]].getText().equals(buttons[combo[2]].getText()) &&
                    !buttons[combo[0]].getText().equals("")) {
                return true;
            }
        }
        return false;
    }

    private boolean checkForDraw() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    private void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.setVisible(true);
    }
}
