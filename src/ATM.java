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

        // start ATM
        public void run() {
            while (true) {
                while (!userAuthenticated) {
                    screen.displayMessage();
                }
            }
        }
    }

}
