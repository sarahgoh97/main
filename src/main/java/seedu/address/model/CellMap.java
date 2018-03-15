package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.cell.Cell;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class CellMap {
    public static final int MAX_ROW = 2;
    public static final int MAX_COL = 5;
    private Cell[][] cellMap;

    /*
     * Represents a fixed-sized map of the cells in the prison.
     * Initialised at start of program.
     */
    public CellMap() {
        resetData();
    }

    /*
     * Prints a map of the cells on the map and their addresses
     */
    public void printCellMap() {
        System.out.print(getCellMap());
    }

    /*
     * Returns a map of the cells and their addresses
     */
    public String getCellMap() {
        StringBuilder sb = new StringBuilder();
        for (Cell[] cArray: cellMap) {
            for (Cell c: cArray) {
                sb.append(c.getCellAddress() + " [" + c.getNumberOfPrisoners() + "] ");
            }
            sb.delete(sb.length()-1, sb.length());
            sb.append("\n");
        }
        return sb.toString();
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
                cellMap[currRow][currCol] = new Cell(currRow, currCol);
            }
        }
    }
}
