//@@author zacci
package seedu.address.logic.commands;

import java.util.ArrayList;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.HideMapEvent;
import seedu.address.model.person.NameContainsKeywordsPredicate;

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
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(new ArrayList<String>()));
        EventsCenter.getInstance().post(new HideMapEvent());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
