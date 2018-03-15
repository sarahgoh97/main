package seedu.address.model.cell;

import seedu.address.model.CellMap;
import seedu.address.model.person.Person;

import java.util.ArrayList;

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
    public Cell(int row, int column) {
        prisoners = new ArrayList<Person>(MAX_SIZE);
        cellAddress = ++row + "-" + ++column;
    }

    public ArrayList<Person> getPrisoners() { return prisoners; }

    public String getCellAddress() { return cellAddress; }

    public int getNumberOfPrisoners() { return prisoners.size(); }

    /**
     * Returns true if a given string is a valid cell.
     */
    public static boolean isValidCellAddress(String test) {
        return test.charAt(0) <= CellMap.MAX_ROW &&
                test.charAt(2) <= CellMap.MAX_COL;
    }
}
