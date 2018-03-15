package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CellMap;

import java.io.IOException;
import java.util.Optional;

/**
 * Represents a storage for {@link CellMap}.
 */
public interface CellMapStorage {

    /**
     * Returns the file path of the CellMap data file.
     */
    String getCellMapFilePath();

    /**
     * Returns map of cells from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<CellMap> readCellMap() throws DataConversionException, IOException;

    /**
     * Saves the given {@link CellMap} to the storage.
     *
     * @param cellMap cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCellMap(CellMap cellMap) throws IOException;

}

