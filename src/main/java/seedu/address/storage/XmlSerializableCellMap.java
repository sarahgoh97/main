package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.cell.CellMap;

/**
 * An Immutable CellMap that is serializable to XML format
 */
@XmlRootElement(name = "cellMap")
public class XmlSerializableCellMap {

    @XmlElement
    private List<XmlAdaptedCell> cellMap;

    /**
     * Creates an empty XmlSerializableCellMap.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableCellMap() {
        cellMap = new ArrayList<XmlAdaptedCell>();
    }

    /**
     * Conversion
     */
    public XmlSerializableCellMap(CellMap src) {
        this();
        cellMap.addAll(src.getCellList().stream().map(XmlAdaptedCell::new).collect(Collectors.toList()));
    }

    /**
     * //to be updated when can add prisoners to cells
     * Converts this cellmap into the model's {@code cellMap} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedPerson}.
     */
    public CellMap toModelType() throws IllegalValueException {
        CellMap cellMap = new CellMap();
        return cellMap;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof XmlSerializableCellMap)) {
            return false;
        }
        XmlSerializableCellMap otherCellMap = (XmlSerializableCellMap) other;
        return otherCellMap.cellMap.equals(otherCellMap.cellMap);
    }
}
