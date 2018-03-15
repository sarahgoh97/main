package seedu.address.model.cell;

import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class CellMap {
    private final int maxRow = 2;
    private final int maxCol = 5;
    private Cell[][] cellMap;

    /*
     * Represents a fixed-sized map of the cells in the prison.
     * Initialised at start of program.
     */
    public CellMap() {
        cellMap = new Cell[maxRow][maxCol];
        for (int currRow = 0; currRow < maxRow; currRow++) {
            for (int currCol = 0; currCol < maxCol; currCol++) {
                cellMap[currRow][currCol] = new Cell(currRow, currCol);
            }
        }
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
     * Resets the existing data of this {@code CellMap}.
     */
    public void resetData() {
        cellMap = new Cell[maxRow][maxCol];
        for (int currRow = 0; currRow < maxRow; currRow++) {
            for (int currCol = 0; currCol < maxCol; currCol++) {
                cellMap[currRow][currCol] = new Cell(currRow, currCol);
            }
        }
    }
}
