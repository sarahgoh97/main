//@@author philos22
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import java.io.IOException;

import com.google.api.client.util.DateTime;

import seedu.address.Calendar;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Deletes an event from the Google Calendar.
 */
public class CalendarDeleteCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "calDel";
    public static final String COMMAND_ALIAS = "calD";
    private final String toDel;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an event from the calendar. \n"
            + "Parameter: EVENT NUMBER (from the event list in Calendar command)"
            + "Example: \n" + COMMAND_WORD + " 12\n"
            + "Deletes the 12th event listed in cal command";


    /**
     * Creates an CalendarAddCommand to add the specified {@code Event}
     */
    public CalendarDeleteCommand(String eventID) {
        requireNonNull(eventID);
        toDel = eventID;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        try {
            String successMessage = new Calendar().delEvent(toDel);
            return new CommandResult(String.format(successMessage, toDel));
        } catch (IOException e) {
            throw new CommandException(e.toString());
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalendarDeleteCommand // instanceof handles nulls
                && toDel.equals(((CalendarDeleteCommand) other).toDel));
    }
}
