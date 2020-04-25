// represents an automated teller machine
public class ATM {

    private boolean userAuthenticated;
    private int currentAccountNumber;
    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private BankDatabase bankDatabase;

    // constants for the main menu options
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;

    //no-argument ATM constructor initialises instance variables
    public ATM() {
        // user is not authenticated at the start
        userAuthenticated = false;
        // no current account number to start
        currentAccountNumber = 0;
        // create screen
        screen = new Screen();
        // create keypad
        keypad = new Keypad();
        // create cash dispenser
        cashDispenser = new CashDispenser();
        // create deposit slot
        depositSlot = new DepositSlot();
        // create account information databse
        bankDatabase = new BankDatabase();
    }

    // start ATM
    public void run() {
        while (true) {
            while (!userAuthenticated) {
                screen.displayMessageLine("\nWelcome!");
                authenticateUser();
            }
            performTransactions();
            userAuthenticated = false;
            currentAccountNumber = 0;
            screen.displayMessage("\nThank you! Goodbye!");
        }
    }

    // authenticates user against the database
    private void authenticateUser() {
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput();
        screen.displayMessage("\nEnter you PIN: ");
        int pin = keypad.getInput();
        // set userAuthenticated to boolean value returned by the database
        userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);

        // check whether authentication was successful
        if(userAuthenticated) {
            currentAccountNumber = accountNumber;
        } else {
            screen.displayMessageLine("Invalid account number or PIN. Please try again.");
        }
    }

    // display the main menu and perform transactions
    private void performTransactions() {
        Transaction currentTransaction = null;
        boolean userExited = false;

        // loop till user chooses a valid option
        while(!userExited) {
            int mainMenuSelection = displayMainMenu();

            // decide how to proceed based on the user's input
            switch (mainMenuSelection) {
                case BALANCE_INQUIRY:
                case WITHDRAWAL:
                case DEPOSIT:
                //initialise new object of chosen type
                    currentTransaction = createTransaction(mainMenuSelection);
                    currentTransaction.execute();
                    break;
                case EXIT:
                    // user chose to terminate the session
                    screen.displayMessageLine("\nExiting the system...");
                    userExited = true;
                    break;
                default:
                    // user did not enter a integer b/w 1 - 4
                    screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
                    break;
            }
        }
    }

    // display main menu and return an input selection
    private int displayMainMenu() {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Exit\n");
        screen.displayMessageLine("Enter a choice: ");
        return keypad.getInput();
    }

    // return object of specified Transaction class
    private Transaction createTransaction(int type) {
        Transaction temp = null;

        // determine which type of transaction to create
        switch (type) {
            case BALANCE_INQUIRY:
                temp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
                break;
            case WITHDRAWAL:
                temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
                break;
            case DEPOSIT:
                temp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
                break;
        }
        return temp;
    }
}


