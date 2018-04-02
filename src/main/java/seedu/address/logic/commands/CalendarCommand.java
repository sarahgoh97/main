package seedu.address.logic.commands;

import java.io.IOException;

import seedu.address.Quickstart;

/**
 * Lists all the commands entered by user from the start of app launch.
 */
public class CalendarCommand extends Command {

    public static final String COMMAND_WORD = "calendar";
    public static final String COMMAND_ALIAS = "cal";
    public static final String ERROR_MESSAGE = "calendar execution failed";

    @Override
    public CommandResult execute() {
        try {
            String messagesuccess = new Quickstart().quick();
            return new CommandResult(messagesuccess);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommandResult(ERROR_MESSAGE);
    }

}
