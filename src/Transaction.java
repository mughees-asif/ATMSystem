// abstract superclass represents the ATM transaction
public abstract class Transaction {

    // attributes
    private int accountNumber;
    private Screen screen;
    private BankDatabase bankDatabase;

    // transaction constructor invoked by subclasses using super()
    public Transaction(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase) {
        accountNumber = userAccountNumber;
        screen = atmScreen;
        bankDatabase = atmBankDatabase;
    }

    // return account number
    public int getAccountNumber() {
        return accountNumber;
    }

    // returns reference to the screen
    public Screen getScreen() {
        return screen;
    }

    // return bank database
    public BankDatabase getBankDatabase() {
        return bankDatabase;
    }

    // perform the transaction (overridden by each subclass)
    abstract public void execute();
}
