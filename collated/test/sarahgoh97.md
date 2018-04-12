# sarahgoh97
###### \java\seedu\address\logic\commands\AddCellCommandTest.java
``` java
package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddCellCommand.MESSAGE_FULL_CELL;
import static seedu.address.logic.commands.AddCellCommand.MESSAGE_NOT_PRISONER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalCells.FULL_CELL;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
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

    public AddCellCommandTest() {
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Test
    public void execute_validIndexUnfilteredListValidCellAddress_success() throws Exception {
        Person prisonerToAdd = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String cellAddress = "2-5";
        AddCellCommand addCellCommand = prepareCommand(INDEX_FIRST_PERSON, cellAddress);

        String expectedMessage = String.format(AddCellCommand.MESSAGE_ADD_CELL_SUCCESS,
                prisonerToAdd.getName().toString(), cellAddress);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
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
        Person prisonerToAdd = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String cellAddress = "0-0";
        AddCellCommand addCellCommand = prepareCommand(INDEX_FIRST_PERSON, cellAddress);

        assertCommandFailure(addCellCommand, model, String.format(AddCellCommand.MESSAGE_NON_EXISTENT_CELL,
                cellAddress, getMapString()));

    }

    @Test
    public void execute_fullCell_failure() {
        Person prisonerToAdd = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String cellAddress = FULL_CELL.getCellAddress();
        AddCellCommand addCellCommand = prepareCommand(INDEX_FIRST_PERSON, cellAddress);

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
```
###### \java\seedu\address\logic\commands\AddCommandTest.java
``` java
        @Override
        public void addPrisonerToCell(Person prisoner, String cellAddress) {
            fail("This method should not be called.");
        }

        @Override
        public void deletePrisonerFromCell(Person prisoner) throws PersonNotFoundException, NotImprisonedException {
            fail("This method should not be called.");
        }

        @Override
        public void deletePrisonerFromCellFromUndo(Person prisoner, String cellAddress) {
            fail("This method should not be called.");
        }

        @Override
        public void addPrisonerToCellFromUndo(Person prisoner, String cellAddress) {
            fail("This method should not be called.");
        }

        @Override
        public void updatePrisonerFromUndo(Person orignal, Person changed) {
            fail("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonListForCell(Predicate <Person> predicate, String cellAddress) {
            fail("This method should not be called.");
        }
```
###### \java\seedu\address\logic\commands\UndoableCommandTest.java
``` java
    @Before
    public void setUp() {
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }
```
###### \java\seedu\address\logic\parser\AddCellCommandParserTest.java
``` java
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.Test;

import seedu.address.logic.commands.AddCellCommand;

public class AddCellCommandParserTest {

    private AddCellCommandParser parser = new AddCellCommandParser();

    @Test
    public void parse_validArgs_returnsAddCellCommand() {
        assertParseSuccess(parser, "ac 1 1-1", new AddCellCommand(INDEX_FIRST_PERSON, "1-1"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "ac 1 sdf32",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCellCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidCellAddress_throwsParseException() {
        assertParseFailure(parser, "ac 1 2=1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCellCommand.MESSAGE_USAGE));
    }
}
```
###### \java\seedu\address\testutil\CellBuilder.java
``` java
package seedu.address.testutil;

import java.util.ArrayList;

import seedu.address.model.cell.Cell;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Cell objects.
 */

public class CellBuilder {
    public static final ArrayList<Person> DEFAULT_PRISONERS = new ArrayList<Person>();
    public static final String DEFAULT_CELLADDRESS = "1-1";
    public static final boolean DEFAULT_IS_LAST = false;

    private ArrayList<Person> prisoners;
    private String cellAddress;
    private boolean isLast;

    public CellBuilder() {
        prisoners = new ArrayList<Person>(DEFAULT_PRISONERS);
        cellAddress = new String(DEFAULT_CELLADDRESS);
        isLast = DEFAULT_IS_LAST;
    }

    /**
     * Initialises the CellBuilder with data of {@code cellToCopy}
     */
    public CellBuilder(Cell celltoCopy) {
        prisoners = celltoCopy.getPrisoners();
        cellAddress = celltoCopy.getCellAddress();
        isLast = celltoCopy.getIsLast();
    }

    /**
     * Sets the {@code prisoners} of the {@code Cell} that we are building.
     */
    public CellBuilder withPrisoners(ArrayList<Person> prisoners) {
        this.prisoners = new ArrayList<Person>(prisoners);
        return this;
    }

    /**
     * Sets the {@code prisoner} of the {@code Cell} that we are building.
     */
    public CellBuilder withPrisoner(Person prisoner) {
        prisoners.add(prisoner);
        return this;
    }

    /**
     * Sets the {@code cellAddress} of the {@code cell} that we are building.
     */
    public CellBuilder withCellAddress(String cellAddress) {
        this.cellAddress = cellAddress;
        return this;
    }

    /**
     * Sets the  {@code isLast} of the {@code cell} that we are building.
     */
    public CellBuilder withIsLast(boolean isLast) {
        this.isLast = isLast;
        return this;
    }

    public Cell build() {
        return new Cell(prisoners, cellAddress, isLast);
    }
}
```
###### \java\seedu\address\testutil\TypicalCells.java
``` java
package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.cell.Cell;

/**
 * A utility class containing a list of {@code Cell} objects to be used in tests.
 */
public class TypicalCells {
    public static final Cell EMPTY_CELL = new CellBuilder().build();
    public static final Cell CELL_WITH_PRISONER = new CellBuilder().withCellAddress("1-2").withPrisoner(CARL).build();
    public static final Cell FULL_CELL = new CellBuilder().withCellAddress("1-3").withPrisoner(ELLE)
            .withPrisoner(GEORGE).build();
    public static final Cell LAST_CELL_OF_ROW = new CellBuilder().withCellAddress("1-5").withIsLast(true).build();


    public static List<Cell> getTypicalCells() {
        return new ArrayList<>(Arrays.asList(EMPTY_CELL, LAST_CELL_OF_ROW, CELL_WITH_PRISONER, FULL_CELL));
    }
}
```
###### \java\systemtests\AddressBookSystemTest.java
``` java
    /**
     * Asserts that the total number of people and sync status in the status bar was changed to the timing of
     * {@code ClockRule#getInjectedClock()}, while the save location remains the same.
     */
    protected void assertStatusBarChangedExceptSaveLocation(int expectedNumberOfPeople) {
        StatusBarFooterHandle handle = getStatusBarFooter();
        String timestamp = new Date(clockRule.getInjectedClock().millis()).toString();
        String expectedSyncStatus = String.format(SYNC_STATUS_UPDATED, timestamp);
        String expectedNumberOfPeopleStatus = expectedNumberOfPeople + " person(s) total.";
        assertEquals(expectedSyncStatus, handle.getSyncStatus());
        assertEquals(expectedNumberOfPeopleStatus, handle.getNumberOfPeopleStatus());
        assertFalse(handle.isSaveLocationChanged());
    }
```
###### \java\systemtests\AddressBookSystemTest.java
``` java
    /**
     * Returns a new model that starts with unfiltered list
     */
    protected Model getNewModel() {
        return testApp.getNewModel();
    }
}
```
