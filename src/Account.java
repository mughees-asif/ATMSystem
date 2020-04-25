// represents the bank account
public class Account {

    // attributes
    private int accountNumber;
    private int pin;
    private double availableBalance;
    private double totalBalance;

    // account constructor initialises attributes
    public Account (int theAccountNumber, int thePin, double theAvailableBalance, double theTotalBalance) {
        accountNumber = theAccountNumber;
        pin = thePin;
        availableBalance = theAvailableBalance;
        totalBalance = theTotalBalance;
    }

    // determines whether a user-specified PIN matches PIN on account details
    public boolean validatePIN (int userPin) {
        // validate the PIN
        if (userPin == pin) {
            return true;
        } else {
            return false;
        }
    }

    // returns available balance
    public double getAvailableBalance() {
        return availableBalance;
    }

    // returns the total balance
    public double getTotalBalance() {
        return totalBalance;
    }

    // credits an amount to the account
    public void credit(double amount) {
        totalBalance += amount;
    }

    // debits an amount from the account
    public void debit(double amount) {
        availableBalance -= amount;
        totalBalance -= amount;
    }

    // returns account number
    public int getAccountNumber() {
        return accountNumber;
    }
}
