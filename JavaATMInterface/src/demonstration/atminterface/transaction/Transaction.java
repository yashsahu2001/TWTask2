package demonstration.atminterface.transaction;

import demonstration.atminterface.user.User;
import java.util.Date;

public class Transaction {
	private User user;

    public Transaction(User user) {
        this.user = user;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= user.getBalance()) {
            user.setBalance(user.getBalance() - amount);
            generateReceipt("Withdrawal", amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            user.setBalance(user.getBalance() + amount);
            generateReceipt("Deposit", amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void transfer(User recipient, double amount) {
        if (amount > 0 && amount <= user.getBalance()) {
            user.setBalance(user.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            generateReceipt("Transfer", amount);
        } else {
            System.out.println("Invalid transfer amount or insufficient balance.");
        }
    }

    private void generateReceipt(String transactionType, double amount) {
        System.out.println("Transaction Receipt:");
        System.out.println("Date: " + new Date());
        System.out.println("User Account Number: " + user.getAccountNumber());
        System.out.println("Transaction Type: " + transactionType);
        System.out.println("Amount: Rs" + amount);
        System.out.println("Updated Balance: Rs" + user.getBalance());
        System.out.println("-------------------------------------------");
    }
}