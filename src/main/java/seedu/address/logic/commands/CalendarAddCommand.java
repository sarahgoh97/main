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
 * Adds an event to the Google Calendar.
 */
public class CalendarAddCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "calAdd";
    public static final String COMMAND_ALIAS = "calA";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the calendar. "
            + "Parameters: "
            + PREFIX_EVENT + "EVENT NAME "
            + PREFIX_LOCATION + "EVENT LOCATION "
            + PREFIX_START + "EVENT START TIME "
            + PREFIX_END + "EVENT END TIME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT + "Finish Software Engineering Milestone "
            + PREFIX_LOCATION + "NUS UTown Residence"
            + PREFIX_START + "2018-04-01T15:00:00"
            + PREFIX_END + "2018-04-01T17:00:00";

    private final String calEventName;
    private final String calEventLocation;
    private final DateTime calStartDateTime;
    private final DateTime calEndDateTime;
    private final String toAdd;


    /**
     * Creates an CalendarAddCommand to add the specified {@code Event}
     */
    public CalendarAddCommand(String eventName, String eventLocation, DateTime startDateTime, DateTime endDateTime) {
        requireNonNull(eventName);
        requireNonNull(eventLocation);
        requireNonNull(startDateTime);
        requireNonNull(endDateTime);
        calEventName = eventName;
        calEventLocation = eventLocation;
        calStartDateTime = startDateTime;
        calEndDateTime = endDateTime;
        toAdd = calEventName + " " + calEventLocation + " " + calStartDateTime.toString() + " "
                + calEndDateTime.toString();
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        try {
            String successMessage = new Calendar().addEvent(calEventName, calEventLocation, calStartDateTime,
                    calEndDateTime);
            return new CommandResult(String.format(successMessage, toAdd));
        } catch (IOException e) {
            throw new CommandException(e.toString());
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalendarAddCommand // instanceof handles nulls
                && toAdd.equals(((CalendarAddCommand) other).toAdd));
    }
}
