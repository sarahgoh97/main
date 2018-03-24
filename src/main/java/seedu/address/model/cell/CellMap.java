package seedu.address.model.cell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Contains the cells of the prison for the PrisonBook
 * Cells are not allowed to exceed the maximum fixed size of the prison
 */
public class CellMap {
    public static final int MAX_ROW = 2;
    public static final int MAX_COL = 5;
    private Cell[][] cellMap;
    private final ObservableList<Cell> internalList = FXCollections.observableArrayList();

    /**
     * Represents a fixed-sized map of the cells in the prison.
     * Initialised at start of program.
     */
    public CellMap() {
        resetData();
    }


    /**
     * Prints a map of the cells on the map and their addresses
     */
    public void printCellMap() {
        System.out.print(cellMap);
    }

    /**
     * Returns the actual array
     */
    public Cell[][] getCellMap() {
        return cellMap;
    }

    /**
     * @param cellAddress has to be within boundaries
     * @return Cell from cellAddress
     */
    public Cell getCell(String cellAddress) {
        int row = Cell.getRow(cellAddress) - 1;
        int col = Cell.getCol(cellAddress) - 1;
        return cellMap[row][col];
    }

    public void setCell(Cell cell, String cellAddress) {
        Cell c = getCell(cellAddress);
        c = cell;
    }

    /**
     * Adds a prisoner to a specified cell.
     */
    public void addPrisonerToCell(Person prisoner, String cellAddress) {
        int row = Cell.getRow(cellAddress) - 1;
        int col = Cell.getCol(cellAddress) - 1;
        addPrisonerToCell(prisoner, row, col);
    }

    private void addPrisonerToCell(Person prisoner, int row, int col) {
        cellMap[row][col].addPrisoner(prisoner);
    }

    /**
     * For storage purposes
     */
    public ObservableList<Cell> getCellList() {
        ObservableList<Cell> cellList = FXCollections.observableArrayList();
        for (Cell[] cArray : cellMap) {
            for (Cell c : cArray) {
                cellList.add(c);
            }
        }
        return FXCollections.unmodifiableObservableList(cellList);
    }

    /**
     * Resets the existing data of this {@code CellMap}.
     */
    public void resetData() {
        cellMap = new Cell[MAX_ROW][MAX_COL];
        for (int currRow = 0; currRow < MAX_ROW; currRow++) {
            for (int currCol = 0; currCol < MAX_COL; currCol++) {
                cellMap[currRow][currCol] = new Cell(currRow + 1, currCol + 1);
            }
        }
    }

    /**
     * Returns a map of the cells and their addresses
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell[] cArray: cellMap) {
            for (Cell c: cArray) {
                sb.append(c.getCellAddress() + " [" + c.getNumberOfPrisoners() + "] ");
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CellMap // instanceof handles nulls
                && this.toString().equals((other.toString())));
    }
}
