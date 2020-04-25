// represents a withdrawal ATM transaction
public class Withdrawal extends Transaction {

    // attributes
    private int amount;
    private Keypad keypad;
    private CashDispenser cashDispenser;

    // constant corresponding to menu option to cancel
    private final static int CANCELLED = 6;

    // constructor
    public Withdrawal(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase,
                      Keypad atmKeypad, CashDispenser atmCashDispenser) {
        // initialise super class variables
        super(userAccountNumber, atmScreen, atmBankDatabase);

        // initialise references to keypad and cash dispenser
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    }

    // perform transactions
    @Override
    public void execute() {
        boolean cashDispensed = false;
        double availableBalance;

        // get references to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        // loop until cash is dispensed or the user cancels
        do {
            amount = displayMenuOfAmounts();
            // check if amount chosen or cancelled
            if (amount != CANCELLED) {
                availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
                // check whether user has enough cash in the account
                if (amount <= availableBalance) {
                    // check dispenser has enough bills
                    if (cashDispenser.isSufficientCashAvailable(amount)) {
                        bankDatabase.debit(getAccountNumber(), amount);
                        cashDispenser.dispenseCash(amount);
                        cashDispensed = true;
                        screen.displayMessageLine("\nYour cash has been" +
                                " dispensed. Please take your cash now.");
                    } else {
                        screen.displayMessageLine("\nInsufficient cash available in the ATM." +
                                "\n\nPlease choose a smaller amount.");
                    }
                } else {
                    screen.displayMessageLine("\nInsufficient cash available in your account." +
                            "\n\nPlease choose a smaller amount.");
                }
                // user chose cancel option
                } else {
                screen.displayMessageLine("\nCancelling transaction ...");
                return;
            }
        } while (!cashDispensed);
    }

    // display a menu of withdrawal amounts and the option to cancel;
    // return the chosen amount or 0 if the user chooses to cancel
    private int displayMenuOfAmounts() {
        int userChoice = 0;
        Screen screen = getScreen();

        // array elements amount to corresponding menu numbers
        int[] amounts = { 0, 10, 20, 40, 60, 100, 200 };

        // loop while no valid choice has been made
        while(userChoice == 0) {
            screen.displayMessageLine("\nWithdrawal menu:");
            screen.displayMessageLine("1 - £20");
            screen.displayMessageLine("2 - £40");
            screen.displayMessageLine("3 - £60");
            screen.displayMessageLine("4 - £100");
            screen.displayMessageLine("5 - £200");
            screen.displayMessageLine("6 - Cancel transaction");
            screen.displayMessageLine("\nChoose a withdrawal amount: ");

            // input from user
            int input = keypad.getInput();

            // determine how to proceed based on the input value
            switch (input) {
                case 1: // if the user chose a withdrawal amount
                case 2: // (i.e. option 1, 2, 3, 4 or 5), return the
                case 3: // corresponding amount from amounts array
                case 4:
                case 5:
                    userChoice = amounts[input];
                    break;
                case CANCELLED:
                    userChoice = CANCELLED;
                    break;
                default:
                    screen.displayMessageLine("\nInvalid selection. Try again.");
            }
        }
        return userChoice;
    }
}
