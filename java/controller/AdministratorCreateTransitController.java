package BeltLineApplication.java.controller;

import BeltLineApplication.java.database.TransitDAO;
import BeltLineApplication.java.limiter.TextFieldLimit;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.SQLException;

/**
 * Completed
 * @author Yaroslava
 */
public class AdministratorCreateTransitController {
    @FXML
    private TextFieldLimit route;
    @FXML
    private TextFieldLimit price;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private ListView<String> connectedSites;

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private Alert error = new Alert(Alert.AlertType.ERROR);

    public void initialize() throws SQLException, ClassNotFoundException {
        //initialize lists with strings
        ObservableList<String> list = TransitDAO.getConnectedSites();
        ObservableList<String> typeList = TransitDAO.getType();

        //add items from list to connectedsites
        connectedSites.setItems(list);

        //makes sure you can select multiple sites
        connectedSites.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        type.setItems(typeList);

        //adjust max length
        route.setMaxLength(100);
        price.setMaxLength(9);
    }

    public void create() throws SQLException {
        if (!route.getText().isEmpty() ||  !price.getText().isEmpty() || !type.getSelectionModel().isEmpty() || connectedSites.getSelectionModel().getSelectedItems().size() < 2 || Double.parseDouble(price.getText()) < 0) {
            try {
                if (TransitDAO.checkRouteType(route.getText(), type.getSelectionModel().getSelectedItem())) {
                    error.setTitle("Route and Type");
                    error.setHeaderText("This combination of Route and Type have already been selected.");
                    error.setContentText("Please add another combination of Route and Type.");
                    return;
                }
            }
            catch (Exception e) {
                System.out.println("Issue with checkRouteType" + e);
            }
            try {
                TransitDAO.createTransit(route.getText(), type.getSelectionModel().getSelectedItem(), Double.parseDouble(price.getText()));
            }
            catch(SQLException e) {
                System.out.println("Issue with SQL on Administrator Create Transit" + e);
            }
            try {
                //goes through connectedSite and adds it to the connected table
                String connectedSite;
                while (connectedSites.getSelectionModel().selectedIndexProperty() != null) {
                    connectedSite = connectedSites.getSelectionModel().selectedIndexProperty().toString();
                    TransitDAO.connect(connectedSite, route.getText(), type.getSelectionModel().getSelectedItem());
                    connectedSites.getSelectionModel().selectNext(); //does this work?
                }
            } catch (Exception e) {
                System.out.println("Issue with connected sites at administrator create transit");
            }
            alert.setTitle("Created Transport");
            alert.setHeaderText(null);
            alert.setContentText("Success! Transit has been created successfully!");
        }
    }
}
