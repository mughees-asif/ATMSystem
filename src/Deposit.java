// represents a deposit transaction
public class Deposit extends Transaction {

    // attributes
    private double amount;
    private Keypad keypad;
    private DepositSlot depositSlot;
    private final static int CANCELLED = 0;

    // constructor
    public Deposit(int userAccountNumber, Screen atmScreen,
                   BankDatabase atmBankDatabase, Keypad atmKeypad, DepositSlot atmDepositSlot) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        depositSlot = atmDepositSlot;
    }

    @Override
    public void execute() {

        // get references
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        // get deposit from user
        amount = promptForDepositAmount();

        // check whether user entered a deposit or cancelled
        if (amount != CANCELLED) {
            screen.displayMessage("\nPlease insert a deposit envelope containing ");
            screen.displayCurrencyAmount(amount);
            screen.displayMessageLine(".");

            // check whether deposit envelope was received
            boolean envelopeReceived = depositSlot.isEnvelopeReceived();
            if (envelopeReceived) {
                screen.displayMessageLine("\nYour envelope was received.\nNOTE: the contents will be " +
                        "verified before the " + amount + " is deposited in your bank account.");
                bankDatabase.credit(getAccountNumber(), amount);
            } else {
                screen.displayMessageLine("\nNo enveloped detected. Cancelling transaction...");
            }
        } else {
            screen.displayMessageLine("\nCancelling transaction.");
        }
    }

    private double promptForDepositAmount() {
        Screen screen = getScreen();
        screen.displayMessage("\nPlease enter the amount you would like to deposit in PENNIES (or press " +
                "0 to cancel): ");
        int input = keypad.getInput();

        // check if user cancelled or entered a valid amount
        if (input == CANCELLED) {
            return CANCELLED;
        } else {
            // return amount in GBP
            return (double) input / 100;
        }
    }
}
