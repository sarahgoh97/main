package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddCellCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCellCommand object
 */
public class AddCellCommandParser implements Parser<AddCellCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCellCommand
     * and returns an AddCellCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCellCommand parse (String args) throws ParseException {
        try {
            requireNonNull(args);
            String removePrefix = null;
            String unparsedIndex = null;
            if (args.contains(" ")) {
                removePrefix = args.substring(args.indexOf(" ") + 1);
                unparsedIndex = removePrefix.substring(0, removePrefix.indexOf(" "));
            }
            if (removePrefix != null && removePrefix.contains(" ") && unparsedIndex != null) {
                String cellAddress = removePrefix.substring(removePrefix.indexOf(" ") + 1);
                Index index = ParserUtil.parseIndex(unparsedIndex);
                return new AddCellCommand(index, cellAddress);
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCellCommand.MESSAGE_USAGE));
            }
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCellCommand.MESSAGE_USAGE));
        }
    }
}
