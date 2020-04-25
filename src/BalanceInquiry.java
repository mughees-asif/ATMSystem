// represents a balance inquiry
public class BalanceInquiry extends Transaction{

    // constructor
    public BalanceInquiry(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
    }

    @Override
    public void execute() {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());

        double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());

        // display balance information on the screen
        screen.displayMessage("\nBalance Information:");
        screen.displayMessage(" - Available Balance: ");
        screen.displayCurrencyAmount(availableBalance);
        screen.displayMessage("\n - Total Balance:      ");
        screen.displayCurrencyAmount(totalBalance);
        screen.displayMessage("");
    }
}
