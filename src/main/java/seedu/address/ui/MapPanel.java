package seedu.address.ui;

import java.net.URL;
import java.util.Date;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import org.fxmisc.easybind.EasyBind;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.commons.events.ui.NewResultAvailableEvent;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.cell.Cell;
import seedu.address.model.person.Person;

/**
 * The cellMap of the App.
 */
public class MapPanel extends UiPart<Region> {

    private static final String FXML = "MapPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(MapPanel.class);

    @FXML
    private Label cellAddress11;
    @FXML
    private Label cellAddress12;
    @FXML
    private Label cellAddress13;
    @FXML
    private Label cellAddress14;
    @FXML
    private Label cellAddress15;
    @FXML
    private Label cellAddress21;
    @FXML
    private Label cellAddress22;
    @FXML
    private Label cellAddress23;
    @FXML
    private Label cellAddress24;
    @FXML
    private Label cellAddress25;
    @FXML
    private Label cellAddress31;
    @FXML
    private Label cellAddress32;
    @FXML
    private Label cellAddress33;
    @FXML
    private Label cellAddress34;
    @FXML
    private Label cellAddress35;

    public MapPanel(ObservableList<Cell> cellList) {
        super(FXML);
        setConnections(cellList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Cell> cellList) {
        cellAddress11.setText(Integer.toString(cellList.get(0).getNumberOfPrisoners()));
        cellAddress12.setText(Integer.toString(cellList.get(1).getNumberOfPrisoners()));
        cellAddress13.setText(Integer.toString(cellList.get(2).getNumberOfPrisoners()));
        cellAddress14.setText(Integer.toString(cellList.get(3).getNumberOfPrisoners()));
        cellAddress15.setText(Integer.toString(cellList.get(4).getNumberOfPrisoners()));
        cellAddress21.setText(Integer.toString(cellList.get(5).getNumberOfPrisoners()));
        cellAddress22.setText(Integer.toString(cellList.get(6).getNumberOfPrisoners()));
        cellAddress23.setText(Integer.toString(cellList.get(7).getNumberOfPrisoners()));
        cellAddress24.setText(Integer.toString(cellList.get(8).getNumberOfPrisoners()));
        cellAddress25.setText(Integer.toString(cellList.get(9).getNumberOfPrisoners()));
        cellAddress31.setText(Integer.toString(cellList.get(10).getNumberOfPrisoners()));
        cellAddress32.setText(Integer.toString(cellList.get(11).getNumberOfPrisoners()));
        cellAddress33.setText(Integer.toString(cellList.get(12).getNumberOfPrisoners()));
        cellAddress34.setText(Integer.toString(cellList.get(13).getNumberOfPrisoners()));
        cellAddress35.setText(Integer.toString(cellList.get(14).getNumberOfPrisoners()));
    }

    @Subscribe
    public void handleAddressBookChangedEvent(AddressBookChangedEvent abce) {
        logger.info(LogsCenter.getEventHandlingLogMessage(abce, "Changing map"));
        setConnections(abce.data.getCellList());
    }

}
