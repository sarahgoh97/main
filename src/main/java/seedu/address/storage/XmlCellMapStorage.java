package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.CellMap;

/**
 * A class to access CellMap stored as an xml file on the hard disk
 */
public class XmlCellMapStorage implements CellMapStorage {
    private static final Logger logger = LogsCenter.getLogger(XmlCellMapStorage.class);

    private String filePath;

    public XmlCellMapStorage(String filePath) {
        this.filePath = filePath;
    }

    public String getCellMapFilePath() {
        return filePath;
    }

    @Override
    public Optional<CellMap> readCellMap() throws DataConversionException, IOException {
        return readCellMap(filePath);
    }

    /**
     * Similar to {@link #readCellMap()}
     * @param filePath location of map. Cannot be null
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<CellMap> readCellMap(String filePath) throws DataConversionException,
            FileNotFoundException {

        requireNonNull(filePath);

        File cellMapFile = new File(filePath);

        if (!cellMapFile.exists()) {
            logger.info("CellMap file "  + cellMapFile + " not found");
            return Optional.empty();
        }

        XmlSerializableCellMap xmlCellMap = XmlFileStorage.loadCellMapFromSaveFile(new File(filePath));
        try {
            return Optional.of(xmlCellMap.toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + cellMapFile + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveCellMap(CellMap cellMap) throws IOException {
        saveCellMap(cellMap, filePath);
    }

    /**
     * Similar to {@link #saveCellMap(CellMap)}
     * @param filePath location of the data. Cannot be null
     */
    public void saveCellMap(CellMap cellMap, String filePath) throws IOException {
        requireNonNull(cellMap);
        requireNonNull(filePath);

        File file = new File(filePath);
        FileUtil.createIfMissing(file);
        XmlFileStorage.saveCellMapToFile(file, new XmlSerializableCellMap(cellMap));
    }
}
