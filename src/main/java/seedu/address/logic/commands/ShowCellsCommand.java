package seedu.address.logic.commands;

import seedu.address.model.cell.CellMap;

/**
 * Shows all cells and number of prisoners in each of them to the user.
 */
public class ShowCellsCommand extends Command {

    public static final String COMMAND_WORD = "map";
    public static final String COMMAND_ALIAS = "m";

    public static final String MESSAGE_SUCCESS = "Shown cells with number of people in them.";


    @Override
    public CommandResult execute() {
        String cells = model.getAddressBook().getCellList().toString();
        String map = getMapString(cells);
        return new CommandResult(map + "\n" + MESSAGE_SUCCESS);
    }

    public String getMapString(String cells) {
        for (int i = 1; i <= CellMap.MAX_ROW; i++) {
            cells = cells.replace(", " + i + "-1", i + "-1");
        }
        cells = cells.substring(1, cells.length() - 1);

        return cells;
    }
}
