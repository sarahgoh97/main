//@@author zacci
package seedu.address.logic.commands;

/**
 * Logs the user out of the current session
 */
public class LogoutCommand extends Command {

    public static final String COMMAND_WORD = "logout";

    public static final String MESSAGE_SUCCESS = "Successfully logged out";
    public static final String MESSAGE_USER_NOT_LOGGED_IN = "You are not currently logged in";

    @Override
    public CommandResult execute() {
        undoRedoStack.clearStack();
        if (!model.checkIsLoggedIn()) {
            return new CommandResult(MESSAGE_USER_NOT_LOGGED_IN);
        }
        model.logout();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
