public class Withdrawal extends Transaction {

    // attributes
    private int accountNumber;
    private double amount;

    // references to associated objects
    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;

    // constructor
    public Withdrawal() {

    }

    // operations
    @Override
    public void execute() {

    }
}
