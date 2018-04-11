//@@author philos22
package seedu.address.logic.parser;

import java.io.IOException;

import seedu.address.logic.commands.CalendarDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new CalendarDeleteCommandParser object
 */
public class CalendarDeleteCommandParser implements Parser<CalendarDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CalendarDeleteCommand
     * and returns the message of whether execution was successful or not.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CalendarDeleteCommand parse(String args) throws ParseException {
        return new CalendarDeleteCommand(args.trim());
    }
}
