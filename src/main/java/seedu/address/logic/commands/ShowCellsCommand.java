package seedu.address.logic.commands;

import seedu.address.model.CellMap;

/**
 * Shows all cells and number of prisoners in each of them to the user.
 */
public class ShowCellsCommand extends Command {

    public static final String COMMAND_WORD = "map";
    public static final String COMMAND_ALIAS = "m";

    public static final String MESSAGE_SUCCESS = "Shown cells with number of people in them.";


    @Override
    public CommandResult execute() {
        CellMap cellMap = model.getCellMap();
        return new CommandResult(cellMap + MESSAGE_SUCCESS);
    }
}
