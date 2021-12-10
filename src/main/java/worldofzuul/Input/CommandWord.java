package worldofzuul.Input;

/**
 * CommandWord is an Enum class that holds the different command words for the game, used in processCommand in the Game class.
 * @see worldofzuul.Game#processCommand(Command)
 * 
 * @author Sebastian J.
 */
public enum CommandWord {
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    UNKNOWN("?"),
    BUY("buy"),
    LOOK("look"),
    NEXT("next");

    private final String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    /**
     * toString is used when CommandWord is called. Turning the command words into Strings.
     *
     * @author Sebastian J.
     */
    public String toString() {
        return commandString;
    }
}
