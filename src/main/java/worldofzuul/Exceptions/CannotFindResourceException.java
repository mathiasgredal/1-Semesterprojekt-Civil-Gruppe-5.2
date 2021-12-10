package worldofzuul.Exceptions;

/**
 * Thrown when we can't find the resource, mainly used FileResourceUtils, but it could also be used for the fxml documents
 */
public class CannotFindResourceException extends Exception {
    public CannotFindResourceException(String resourceName) {
        super("Cannot find the requested resource: \"" + resourceName + "\"");
    }
}
