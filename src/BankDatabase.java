import java.util.Objects;

// represents the bank account information database
public class BankDatabase {

    // array of accounts
    private Account[] accounts;

    // no-argument constructor initialises accounts
    public BankDatabase() {
        // two accounts for TESTING
        accounts = new Account[2];
        accounts[0] = new Account(12345, 6789, 1000, 1300);
        accounts[1] = new Account(15675, 6387, 0, 200);
    }

    // retrieve account object containing specified account number
    private Account getAccount(int accountNumber) {
        // loop through accounts for the matching account number
        for(Account currentAccount : accounts) {
            if (currentAccount.getAccountNumber() == accountNumber) {
                return currentAccount;
            }
        }
        return null;
    }

    // determine whether user-specified account number and PIN match with the database details
    public boolean authenticateUser(int userAccountNumber, int userPIN) {
        Account userAccount = getAccount(userAccountNumber);
        if (userAccount != null) {
            return userAccount.validatePIN(userPIN);
        } else {
            return false;
        }
    }

    // return available balance of specified account balance
    public double getAvailableBalance(int userAccountNumber) {
        // makes sure the method does not return a null object
        return Objects.requireNonNull(getAccount(userAccountNumber)).getAvailableBalance();
    }

    // return total balance of account with specified account number
    public double getTotalBalance(int userAccountNumber) {
        return Objects.requireNonNull(getAccount(userAccountNumber)).getTotalBalance();
    }

    // credit an amount to account
    public void credit(int userAccountNumber, double amount) {
        Objects.requireNonNull(getAccount(userAccountNumber)).credit(amount);
    }

    // debit an amount from the account
    public void debit(int userAccountNumber, double amount) {
        Objects.requireNonNull(getAccount(userAccountNumber)).debit(amount);
    }
}
