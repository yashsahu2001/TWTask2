package demonstration.atminterface.user;

public class User {
	 private int accountNumber;
	    private int pin;
	    private double balance;

	    public User(int accountNumber, int pin, double balance) {
	        this.accountNumber = accountNumber;
	        this.pin = pin;
	        this.balance = balance;
	    }

	    public int getAccountNumber() {
	        return accountNumber;
	    }

	    public int getPin() {
	        return pin;
	    }

	    public double getBalance() {
	        return balance;
	    }

	    public void setBalance(double balance) {
	        this.balance = balance;
	    }

}
