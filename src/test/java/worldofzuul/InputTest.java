package worldofzuul;

import org.junit.jupiter.api.Test;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Input.CommandWords;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InputTest {
    @Test
    void isUnknownTest(){
        Command command = new Command(CommandWord.UNKNOWN, "south");

        assertEquals(true, command.isUnknown());
    }

    @Test
    void getSecondWord(){
        Command command = new Command(CommandWord.UNKNOWN, "south");

        assertEquals("south", command.getSecondWord());
    }

    @Test
    void testCommandClass(){
        CommandWord commandWord = CommandWord.GO;
        String secondWord = "east";

        Command command = new Command(commandWord, secondWord);

        if(command.hasSecondWord()){
            assertEquals(CommandWord.GO, commandWord);
            assertEquals("east", secondWord);
        }
    }

    @Test
    void hasSecondWordTest(){
        Command command = new Command(CommandWord.BUY, null);

        if(command.hasSecondWord()){
            assertEquals(false, command.hasSecondWord());
        }
    }

    @Test
    void isCommandTest(){
        Command command = new Command(CommandWord.GO, "east");
        CommandWords commandWords = new CommandWords();
        assertEquals(commandWords.isCommand(command.getCommandWord().toString()), true);
    }

    @Test
    void isNotCommandTest(){
        Command command = new Command(CommandWord.UNKNOWN, "east");
        CommandWords commandWords = new CommandWords();
        assertEquals(commandWords.isCommand(command.getCommandWord().toString()), false);
    }
}
