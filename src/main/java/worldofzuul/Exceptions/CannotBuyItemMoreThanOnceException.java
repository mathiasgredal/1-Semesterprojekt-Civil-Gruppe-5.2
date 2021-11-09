package worldofzuul.Exceptions;

public class CannotBuyItemMoreThanOnceException extends Exception {
    public CannotBuyItemMoreThanOnceException() {
        super("Cannot buy item more than once");
    }
}
