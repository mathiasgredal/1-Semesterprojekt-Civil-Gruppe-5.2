package worldofzuul.Exceptions;

public class CannotFindResourceException extends Exception {
    public CannotFindResourceException(String resourceName) {
        super("Cannot find the requested resource: \"" + resourceName + "\"");
    }
}
