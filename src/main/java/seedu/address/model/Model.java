package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.cell.exceptions.AlreadyInCellException;
import seedu.address.model.cell.exceptions.FullCellException;
import seedu.address.model.cell.exceptions.NonExistentCellException;
import seedu.address.model.cell.exceptions.NotImprisonedException;
import seedu.address.model.cell.exceptions.NotPrisonerException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyAddressBook newData);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Returns the session */
    Session getSession();

    /** Clears existing session*/
    void logout();

    /** Logs in verified user and assigns security level to the session */
    void login(String username, int securityLevel);

    /** Returns Session details to caller */
    public String getSessionDetails();

    /** Returns Session security level to caller */
    public int getSecurityLevel();

    /** Deletes the given person. */
    void deletePerson(Person target) throws PersonNotFoundException;

    /** Adds the given person */
    void addPerson(Person person) throws DuplicatePersonException;

    //@@author sarahgoh97
    /** Adds given prisoner into a cell */
    void addPrisonerToCell(Person prisoner, String cellAddress)
            throws FullCellException, NonExistentCellException,
            NotPrisonerException, AlreadyInCellException;

    /** Deletes given prisoner from a cell from undo command*/
    void deletePrisonerFromCell(Person prisoner, String cellAddress);

    /**Deletes given prisoner from a cell from DeleteCellCommand */
    void deletePrisonerFromCell(Person prisoner) throws PersonNotFoundException, NotImprisonedException;

    //@@author
    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     *
     * @throws DuplicatePersonException if updating the person's details causes the person to be equivalent to
     *      another existing person in the list.
     * @throws PersonNotFoundException if {@code target} could not be found in the list.
     */
    void updatePerson(Person target, Person editedPerson)
            throws DuplicatePersonException, PersonNotFoundException;

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

}
