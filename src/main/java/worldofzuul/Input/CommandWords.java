package worldofzuul.Input;

import java.util.HashMap;
import java.util.Objects;


public class CommandWords {
    private final HashMap<String, CommandWord> validCommands;

    /**
     * initializing HashMap "validCommands", when the "CommandsWords" constructor is called.
     * forEach-loop iterating over the values in Enum "CommandWord".
     * if the current value is not equal to ""UNKNOWN", then it will be put into the "validcommands(command.toString(), command)".
     * @author Sebastian J.
     */
    public CommandWords() {
        validCommands = new HashMap<>();
        for (CommandWord command : CommandWord.values()) {
            if (command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * "Objects.requireNonNullElse()" checks for a String, and if it isn't NULL, it will return value "CommandWord.UNKNOWN"
     * @author Sebastian J.
     */
    public CommandWord getCommandWord(String commandWord) {
        CommandWord command = validCommands.get(commandWord);
        return Objects.requireNonNullElse(command, CommandWord.UNKNOWN);
    }

    /**
     * Checks for key a specific key. Returns true if it is a command or false if it isn't
     * @author Sebastian J.
     */
    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
    }

    /**
     * ForEach-loop iterating over keys in hashmap "validCommands" and printing every key
     * @author Sebastian J.
     */
    public void showAll() {
        for (String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
