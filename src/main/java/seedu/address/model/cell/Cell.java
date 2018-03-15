package seedu.address.model.cell;

import seedu.address.model.person.Person;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CELL_FULL;

/*
 * Represents a cell in the prison.
 * Guarantees: cell cannot exceed maximum number of people
 */
public class Cell {
    private final int MAX_SIZE = 2;
    private final ArrayList<Person> prisoners;
    private String cellAddress;

    /*
     * Represents a cell in the Prison.
     */
    protected Cell(int row, int column) {
        prisoners = new ArrayList<Person>(MAX_SIZE);
        cellAddress = ++row + "-" + ++column;
    }

    public ArrayList<Person> getPrisoners() { return prisoners; }

    public String getCellAddress() { return cellAddress; }
}
