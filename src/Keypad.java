import java.util.Scanner;

// represents the keypad of the ATM
public class Keypad {

    private Scanner input;
    // no-argument constructor initialises the Scanner
    public Keypad() {
        input = new Scanner(System.in);
    }

    // return an integer value entered by the user
    public int getInput() {

        // ASSUMPTION: User enters an Integer
        return input.nextInt();
    }
}
