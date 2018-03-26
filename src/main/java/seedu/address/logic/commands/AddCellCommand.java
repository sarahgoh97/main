package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;


/**
 * Adds a prisoner to a cell in the address book.
 */
public class AddCellCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "addcell";
    public static final String COMMAND_ALIAS = "ac";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a prisoner to the specified cell."
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "CELLADDRESS (block-unit)"
            + "Example: " + COMMAND_WORD + " 1 1-1";

    public static final String MESSAGE_ADD_CELL_SUCCESS = "Prisoner %s added to %s.";

    public final Index index;

    private Person prisonerToAdd;
    private String cellAddress;

    /**
     * @param index of the person in the filtered person list to edit
     * @param cellAddress cell to be added to
     */
    public AddCellCommand(Index index, String cellAddress) {
        requireNonNull(index);
        requireNonNull(cellAddress);

        this.index = index;
        this.cellAddress = cellAddress;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        prisonerToAdd = model.getFilteredPersonList().get(index.getZeroBased());
        model.addPrisonerToCell(prisonerToAdd, cellAddress);
        return new CommandResult(String.format(MESSAGE_ADD_CELL_SUCCESS, prisonerToAdd.getName(), cellAddress));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCellCommand // instanceof handles nulls
                && this.index.equals(((AddCellCommand) other).index) // state check
                && Objects.equals(this.prisonerToAdd, ((AddCellCommand) other).prisonerToAdd));
    }
}
