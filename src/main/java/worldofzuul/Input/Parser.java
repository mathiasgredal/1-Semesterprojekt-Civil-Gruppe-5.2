package worldofzuul.Input;

import java.util.Scanner;

public class Parser {
    private final CommandWords commands;
    private final Scanner reader;

    /**
     * No-args constructor, gets initialized with a new Object of CommandWords, and a new Scanner object.
     */
    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * Checks for the text that the player has typed into the console.
     * An if statement that checks for the words the player has written.
     * If no words is written, then Command object will have null attributes, and therefore not do anything.
     * @return A new object of Command, with the word1 and word2 as attributes
     * @author Sebastian J.
     */
    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        return new Command(commands.getCommandWord(word1), word2);
    }

    /**
     * Shows all CommandWords
     * @see CommandWords#showAll()
     */
    public void showCommands() {
        commands.showAll();
    }
}
