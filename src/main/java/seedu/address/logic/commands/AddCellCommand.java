package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.cell.exceptions.AlreadyInCellException;
import seedu.address.model.cell.exceptions.FullCellException;
import seedu.address.model.cell.exceptions.NonExistentCellException;
import seedu.address.model.cell.exceptions.NotPrisonerException;
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
    public static final String MESSAGE_FULL_CELL = "Cell %s is already full. Here is the map:\n%s";
    public static final String MESSAGE_NON_EXISTENT_CELL = "This cell %s does not exist. Here is the map:\n%s";
    public static final String MESSAGE_NOT_PRISONER = "%s is not a prisoner.";
    public static final String MESSAGE_ALREADY_IN_CELL = "%s is already in cell %s";

    public final Index index;

    public Person prisonerToAdd;
    public String cellAddress;

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
        requireNonNull(model);
        requireNonNull(prisonerToAdd);
        try {
            model.addPrisonerToCell(prisonerToAdd, cellAddress);
        } catch (FullCellException fce) {
            throw new CommandException(String.format(MESSAGE_FULL_CELL,
                    cellAddress, new ShowCellsCommand().getMapString(
                            model.getAddressBook().getCellList().toString())));
        } catch (NonExistentCellException nece) {
            throw new CommandException(String.format(MESSAGE_NON_EXISTENT_CELL,
                    cellAddress, new ShowCellsCommand().getMapString(
                            model.getAddressBook().getCellList().toString())));
        } catch (NotPrisonerException npe) {
            throw new CommandException(String.format(MESSAGE_NOT_PRISONER, prisonerToAdd.getName()));
        } catch (AlreadyInCellException aice) {
            throw new CommandException(String.format(MESSAGE_ALREADY_IN_CELL,
                    prisonerToAdd.getName(), prisonerToAdd.getAddress()));
        }
        return new CommandResult(String.format(MESSAGE_ADD_CELL_SUCCESS, prisonerToAdd.getName(), cellAddress));
    }

    @Override
    protected void preprocessUndoableCommand() throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        prisonerToAdd = lastShownList.get(index.getZeroBased());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCellCommand // instanceof handles nulls
                && this.index.equals(((AddCellCommand) other).index) // state check
                && Objects.equals(this.prisonerToAdd, ((AddCellCommand) other).prisonerToAdd));
    }
}
