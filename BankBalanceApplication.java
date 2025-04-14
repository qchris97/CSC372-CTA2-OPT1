import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class BankBalanceApplication extends JFrame {

    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton depositButton;
    private JButton withdrawButton;
    private JLabel balanceLabel;
    private double accountBalance;
    private NumberFormat currencyFormat;

    // Constructor for the BankBalanceApplication
    public BankBalanceApplication() {
        // Set the application title
        setTitle("Bank Account Application");

        // Set default close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the currency format
        currencyFormat = NumberFormat.getCurrencyInstance();

        // Initialize the bank balance to 0
        accountBalance = 0.0;

        // Create the main JPanel
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set text size and font
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // Initialize the balance label and set text
        balanceLabel = new JLabel("Account Balance: " + currencyFormat.format(accountBalance));
        balanceLabel.setFont(labelFont);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        balanceLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize the deposit button
        depositButton = new JButton("Deposit");
        depositButton.setFont(buttonFont);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter the deposit amount
                String amountString = JOptionPane.showInputDialog(BankBalanceApplication.this, "Enter amount to deposit:");
                if (amountString != null && !amountString.isEmpty()) {
                    try {
                        double amount = Double.parseDouble(amountString);
                        if (amount > 0) {
                            // Add the amount to the account balance
                            accountBalance += amount;
                            // Update the balance label
                            balanceLabel.setText("Account Balance: " + currencyFormat.format(accountBalance));
                            JOptionPane.showMessageDialog(BankBalanceApplication.this, "Deposit of " + currencyFormat.format(amount) + " was successful.");
                        // Error message if the amount is not positive
                        } else {
                            JOptionPane.showMessageDialog(BankBalanceApplication.this, "Error. Please enter a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    // Error message if the input is not a valid number
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(BankBalanceApplication.this, "Error. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Initialize the withdraw button
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(buttonFont);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter the withdrawal amount
                String amountString = JOptionPane.showInputDialog(BankBalanceApplication.this, "Enter amount to withdraw:");
                if (amountString != null && !amountString.isEmpty()) {
                    try {
                        double amount = Double.parseDouble(amountString);
                        if (amount > 0) {
                            // Check if there are sufficient funds
                            if (amount <= accountBalance) {
                                // Subtract the amount from the account balance
                                accountBalance -= amount;
                                // Update the balance label
                                balanceLabel.setText("Account Balance: " + currencyFormat.format(accountBalance));
                                JOptionPane.showMessageDialog(BankBalanceApplication.this, "Withdrawal of " + currencyFormat.format(amount) + " was successful.");
                            // Error message if there are not sufficient funds
                            } else {
                                JOptionPane.showMessageDialog(BankBalanceApplication.this, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        // Error message if the amount is not positive
                        } else {
                            JOptionPane.showMessageDialog(BankBalanceApplication.this, "Error. Please enter a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    // Error message if the input is not a valid number
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(BankBalanceApplication.this, "Error. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Create a panel for the deposit and withdraw buttons
        buttonPanel = new JPanel();
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);

        // Add the components to the main panel
        mainPanel.add(balanceLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the main panel to the JFrame
        add(mainPanel);

        // Set size
        setSize(350, 150);

        // Make the JFrame visible
        setVisible(true);
    }

    // Main method to create and run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BankBalanceApplication();
            }
        });
    }
}
