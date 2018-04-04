package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_ALIAS = "c";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final int minSecurityLevel = 3;

    @Override
    /**
     * Returns the minSecurityLevel to caller
     */
    public int getMinSecurityLevel() {
        return minSecurityLevel;
    }


    @Override
    public CommandResult executeUndoableCommand() {
        requireNonNull(model);
        model.resetData(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
