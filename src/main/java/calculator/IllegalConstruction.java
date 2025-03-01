package calculator;

/**
 * Exception that will be used when an incorrectly constructed arithmetic expression is encountered.
 */
public class IllegalConstruction extends Exception {
    public IllegalConstruction() {
        super("Illegal construction of an arithmetic expression");
    }
}
