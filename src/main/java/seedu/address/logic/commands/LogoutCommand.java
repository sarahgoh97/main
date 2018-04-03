package seedu.address.logic.commands;

/**
 * Logs the user out of the current session
 */
public class LogoutCommand extends Command {

    public static final String COMMAND_WORD = "logout";

    public static final String MESSAGE_SUCCESS = "Successfully logged out";


    @Override
    public CommandResult execute() {
        model.logout();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
