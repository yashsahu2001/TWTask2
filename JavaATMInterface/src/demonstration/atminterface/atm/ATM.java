package demonstration.atminterface.atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import demonstration.atminterface.transaction.Transaction;
import demonstration.atminterface.user.User;

public class ATM {
	private User currentUser;
    private JFrame frame;
    private JPanel panel;
    private JTextField accountNumberField;
    private JPasswordField pinField;
    private Transaction transaction;

    public ATM() {
        frame = new JFrame("ATM");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberField = new JTextField();
        JLabel pinLabel = new JLabel("PIN:");
        pinField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        panel.add(accountNumberLabel);
        panel.add(accountNumberField);
        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int accountNumber = Integer.parseInt(accountNumberField.getText());
                int pin = Integer.parseInt(new String(pinField.getPassword()));
                authenticateUser(accountNumber, pin);
            }
        });
    }

    public void start() {
        // GUI login is handled in the constructor
    }

    private void authenticateUser(int accountNumber, int pin) {
        // Hardcoded user for demonstration
        currentUser = new User(123456, 1234, 1000.00);

        if (currentUser.getAccountNumber() != accountNumber || currentUser.getPin() != pin) {
            JOptionPane.showMessageDialog(frame, "Invalid account number or PIN.");
        } else {
            transaction = new Transaction(currentUser);
            launchMainMenu();
        }
    }

    private void launchMainMenu() {
        panel.removeAll();
        panel.setLayout(new GridLayout(7, 1));

        JLabel label1 = new JLabel("Welcome to the ATM");
        JLabel label2 = new JLabel("Choose an option:");
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton balanceButton = new JButton("Check Balance");
        JButton transferButton = new JButton("Transfer Funds");
        JButton exitButton = new JButton("Exit");

        panel.add(label1);
        panel.add(label2);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(balanceButton);
        panel.add(transferButton);
        panel.add(exitButton);

        frame.revalidate();
        frame.repaint();

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
                double withdrawAmount = Double.parseDouble(amount);
                withdraw(withdrawAmount);
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
                double depositAmount = Double.parseDouble(amount);
                deposit(depositAmount);
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Current balance: ₹" + currentUser.getBalance());
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipientAccount = JOptionPane.showInputDialog(frame, "Enter recipient's account number:");
                int recipientAccountNumber = Integer.parseInt(recipientAccount);
                String amount = JOptionPane.showInputDialog(frame, "Enter amount to transfer:");
                double transferAmount = Double.parseDouble(amount);
                User recipient = findUser(recipientAccountNumber);
                if (recipient != null) {
                    transfer(recipient, transferAmount);
                } else {
                    JOptionPane.showMessageDialog(frame, "Recipient account not found.");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void withdraw(double amount) {
        if (amount > currentUser.getBalance()) {
            JOptionPane.showMessageDialog(frame, "Insufficient balance.");
        } else {
            transaction.withdraw(amount);
        }
    }

    private void deposit(double amount) {
        transaction.deposit(amount);
    }

    private void transfer(User recipient, double amount) {
        transaction.transfer(recipient, amount);
    }

    private User findUser(int accountNumber) {
        // In a real application, this method would search a database for the user
        // Here, we're just simulating it by checking against a hardcoded user
        if (currentUser.getAccountNumber() == accountNumber) {
            return currentUser;
        }
        return null;
    }
}