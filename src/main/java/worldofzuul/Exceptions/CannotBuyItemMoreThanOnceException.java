package worldofzuul.Exceptions;

/**
 * Thrown when buying things in a shop, that can only be bought once
 */
public class CannotBuyItemMoreThanOnceException extends Exception {
    public CannotBuyItemMoreThanOnceException() {
        super("Cannot buy item more than once");
    }
}
