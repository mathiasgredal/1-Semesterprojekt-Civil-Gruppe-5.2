package worldofzuul.Exceptions;

/**
 * We subclass exception, even though it is only used once,
 * since it allows us to specify which exception is catched in the try-catch.
 * Otherwise, we would have to catch all exceptions: try {...} catch(Exception ex) {...}
 * which is considered bad practise
 */
public class RecieverForBoughtItemNotFound extends Exception {
    public RecieverForBoughtItemNotFound() {
        super("Could not find reciever for the bought item");
    }
}
