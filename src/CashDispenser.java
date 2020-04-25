// represents the cash dispenser of the ATM
public class CashDispenser {

    // default number of bills in the cash dispenser
    private final static int INITIAL_COUNT = 500;
    // number of bill remaining
    private int count;

    // no-argument constructor initialises count to default
    public CashDispenser() {
        count = INITIAL_COUNT;
    }

    // simulates dispensing of specified amount of cash
    public void dispenseCash (int amount) {
        // number of £20 bills required
        int billsRequired = amount / 20;
        // update count of bills
        count -= billsRequired;
    }

    // indicates whether the cash dispenser can dispense desired amount
    public boolean isSufficientCashAvailable(int amount) {
        // number of £20 bills required
        int billsRequired = amount / 20;
        // check to see if enough bills available
        if (count >= billsRequired) {
            return true;
        } else {
            return false;
        }
    }
}
