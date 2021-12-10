package worldofzuul.Input;

import java.util.HashMap;
import java.util.Objects;


public class CommandWords {
    private final HashMap<String, CommandWord> validCommands;

    /**
     * initializing HashMap "validCommands", when the "CommandsWords" constructor is called.
     * forEach-loop iterating over the values in Enum "CommandWord".
     * if the current value is not equal to ""UNKNOWN", then it will be added to the validCommands hashmap with the command.toString as the key, and the command as the value.
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
     *
     * @param commandWord Command word, needs a string, and will check if it isn't null.
     * @return a CommandWord
     * @author Sebastian J.
     */
    public CommandWord getCommandWord(String commandWord) {
        CommandWord command = validCommands.get(commandWord);
        return Objects.requireNonNullElse(command, CommandWord.UNKNOWN);
    }

    /**
     * Checks for key a specific key. Returns true if it is a command or false if it isn't
     *
     * @param aString A string, this is the string that you want to check if it is a command
     * @author Sebastian J.
     */
    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
    }

    /**
     * ForEach-loop iterating over keys in hashmap "validCommands" and printing every key (CommandWord)
     * @author Sebastian J.
     */
    public void showAll() {
        for (String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
