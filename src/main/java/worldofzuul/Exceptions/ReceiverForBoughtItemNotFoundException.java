package worldofzuul.Exceptions;

/**
 * This is thrown when something is bought that doesn't have a receiver.
 * In this context it means the build area for energysources or the house for energy consumers.
 * This is not supposed to be thrown, unless one implements the buyable interface
 */
public class ReceiverForBoughtItemNotFoundException extends Exception {
    public ReceiverForBoughtItemNotFoundException() {
        super("Could not find reciever for the bought item");
    }
}
