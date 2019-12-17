package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import BeltLineApplication.java.database.TransitDAO;
import BeltLineApplication.java.limiter.TextFieldLimit;
import BeltLineApplication.java.model.Transit;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.sql.SQLException;

/**
 * Completed
 * @author Yaroslava
 */
public class AdministratorEditTransitController {
    @FXML
    private Label transportType;
    @FXML
    private TextFieldLimit route;
    @FXML
    private TextFieldLimit price;
    @FXML
    private ListView<String> connectedSites;
    private static Transit transit;

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public void initialize() throws SQLException, ClassNotFoundException {
        Transit item = getTransit();

        //set Transport Type style
        transportType.setStyle("-fx-font-weight: bold");
        transportType.setStyle("-fx-font-style: italic");

        //set the text
        transportType.setText(item.getType());
        route.setText(item.getRoute());
        price.setText(Double.toString(item.getPrice()));

        ObservableList<String> list = TransitDAO.getConnectedSites();
        connectedSites.setItems(list);
        connectedSites.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        for (String site: list) {
            connectedSites.getSelectionModel().select(site);
        }

        //adjust max length
        route.setMaxLength(100);
        price.setMaxLength(9);
    }

    public static void setTransit(Transit item) {
        transit = item;
    }

    public static Transit getTransit() {
        return transit;
    }

    public void update() {
        //make sure none of them are empty
        if (!route.getText().isEmpty() || !price.getText().isEmpty() || connectedSites.getSelectionModel().getSelectedItems().size() < 2) {
            //try to update the site
            try {
                TransitDAO.updateTransit(transportType.getText(), route.getText(), Double.parseDouble(price.getText()));
            } catch (Exception e) {
                System.out.println("Issue with SQL for update transit" + e);
                throw e;
            } try {
                //goes through connectedSite and adds it to the connected table
                String connectedSite;
                while (connectedSites.getSelectionModel().selectedIndexProperty() != null) {
                    connectedSite = connectedSites.getSelectionModel().selectedIndexProperty().toString();
                    TransitDAO.connect(connectedSite, route.getText(), transportType.getText());
                    connectedSites.getSelectionModel().selectNext(); //does this work?
                }
            } catch (Exception e) {
                System.out.println("Issue with connected sites at administrator edit transit");
            } finally {
                alert.setTitle("Update Transit");
                alert.setHeaderText(null);
                alert.setContentText("Success! Transit has been updated Successfully!");
            }
        }
    }

    public void back() throws Exception {
        Parent administratorManageTransit = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorManageTransit.fxml"));
        Scene rootScene = new Scene(administratorManageTransit, 650, 450);
        Main.pstage.setScene(rootScene);
    }
}
