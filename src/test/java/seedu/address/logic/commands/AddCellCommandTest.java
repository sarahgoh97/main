//@@author sarahgoh97
package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddCellCommand.MESSAGE_FULL_CELL;
import static seedu.address.logic.commands.AddCellCommand.MESSAGE_NOT_PRISONER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCells.FULL_CELL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class AddCellCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredListValidCellAddress_success() throws Exception {
        Person prisonerToAdd = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        String cellAddress = "2-5";
        AddCellCommand addCellCommand = prepareCommand(INDEX_THIRD_PERSON, cellAddress);

        String expectedMessage = String.format(AddCellCommand.MESSAGE_ADD_CELL_SUCCESS,
                prisonerToAdd.getName().toString(), cellAddress);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPrisonerToCell(prisonerToAdd, cellAddress);

        assertCommandSuccess(addCellCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_inValidIndexUnfilteredListValidCellAddress_success() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        String cellAddress = "2-5";
        AddCellCommand addCellCommand = prepareCommand(outOfBoundIndex, cellAddress);

        assertCommandFailure(addCellCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

    }

    @Test
    public void execute_validIndexUnfilteredListInvalidCellAddress_success() {
        Person prisonerToAdd = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        String cellAddress = "0-0";
        AddCellCommand addCellCommand = prepareCommand(INDEX_THIRD_PERSON, cellAddress);

        assertCommandFailure(addCellCommand, model, String.format(AddCellCommand.MESSAGE_NON_EXISTENT_CELL,
                cellAddress, getMapString()));

    }

    @Test
    public void execute_fullCell_failure() {
        Person prisonerToAdd = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        String cellAddress = FULL_CELL.getCellAddress();
        AddCellCommand addCellCommand = prepareCommand(INDEX_THIRD_PERSON, cellAddress);

        assertCommandFailure(addCellCommand, model,
                String.format(MESSAGE_FULL_CELL, cellAddress, getMapString()));

    }

    @Test
    public void execute_personNotPrisoner_failure() {
        Person prisonerToAdd = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        String cellAddress = "2-5";
        AddCellCommand addCellCommand = prepareCommand(INDEX_SECOND_PERSON, cellAddress);

        assertCommandFailure(addCellCommand, model,
                String.format(MESSAGE_NOT_PRISONER, prisonerToAdd.getName().toString()));

    }

    /*difficult to test, will fix later
    @Test
    public void execute_personInCell_failure() {
        Person prisonerToAdd = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String cellAddress = "2-5";
        AddCellCommand addCellCommand = prepareCommand(INDEX_FIRST_PERSON, cellAddress);

        assertCommandFailure(addCellCommand, model,
                String.format(MESSAGE_ALREADY_IN_CELL,
                        prisonerToAdd.getName().toString(), prisonerToAdd.getAddress().toString()));

    }*/

    private AddCellCommand prepareCommand(Index index, String cellAddress) {
        AddCellCommand addCellCommand = new AddCellCommand(index, cellAddress);
        addCellCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return addCellCommand;
    }

    private String getMapString() {
        return new ShowCellsCommand().getMapString(model.getAddressBook().getCellList().toString());
    }
}
