package BeltLineApplication.java.controller;
import BeltLineApplication.Main;
import BeltLineApplication.java.database.EventDAO;
import BeltLineApplication.java.database.SiteDAO;
import BeltLineApplication.java.model.Event;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import javax.sql.rowset.serial.SerialException;
import java.sql.SQLException;

public class VisitorExploreEvent {
    @FXML
    Button filter;
    @FXML
    Button eventDetail;
    @FXML
    Button back;
    @FXML
    CheckBox includeSoldOut;
    @FXML
    CheckBox includeVisited;
    @FXML
    TableView<Event> eventTable;
    @FXML
    ChoiceBox<String> sites;
    @FXML
    TextField visitsLow;
    @FXML
    TextField visitsHigh;
    @FXML
    TextField priceLow;
    @FXML
    TextField priceHigh;
    @FXML
    TextField name;
    @FXML
    TextField dKey;
    @FXML
    TextField sDate;
    @FXML
    TextField eDate;


    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> list = SiteDAO.getSites();
        sites.setItems(list);
        sites.getSelectionModel();

        ObservableList<Event> event = EventDAO.exploreEvent();
        eventTable.setItems(event);

        includeVisited.setSelected(false);
        includeSoldOut.setSelected(false);
    }

    public void filter() throws Exception {
        ObservableList<Event> list = EventDAO.filterEvents(name.getText(), dKey.getText(), sDate.getText() , eDate.getText() ,Double.parseDouble(priceLow.getText()), Double.parseDouble(priceHigh.getText()), Integer.parseInt(visitsLow.getText()), Integer.parseInt(visitsHigh.getText()), includeSoldOut.isSelected(), includeVisited.isSelected() ,sites.getSelectionModel().toString());
        eventTable.setItems(list);
    }

    public void eventDetail() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BeltLineApplication/resources/fxml/VisitorEventDetail.fxml"));
        Parent root = loader.load();
        Scene rootScene = new Scene(root, 600, 450);
        Main.pstage.setScene(rootScene);
        VisitorEventDetail controller = loader.getController();
        controller.initialize(eventTable.getSelectionModel().getSelectedItem());
    }

    public void back() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/VisitorFunctionalityOnly.fxml"));
        Scene rootScene = new Scene(root, 250, 350);
        Main.pstage.setScene(rootScene);
    }

}
