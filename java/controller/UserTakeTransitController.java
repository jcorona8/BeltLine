package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import BeltLineApplication.java.database.SiteDAO;
import BeltLineApplication.java.database.TransitDAO;
import BeltLineApplication.java.limiter.TextFieldLimit;
import BeltLineApplication.java.model.Transit;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Completed
 * @author Yaroslava
 */
public class UserTakeTransitController {
    @FXML
    private TableView<Transit> transitTable;
    @FXML
    private TextFieldLimit minRange;
    @FXML
    private TextFieldLimit maxRange;
    @FXML
    private ChoiceBox<String> containSite;
    @FXML
    private ChoiceBox<String> transportType;
    @FXML
    private DatePicker transitDate;

    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<Transit> trans = TransitDAO.populateTransit(false);
        transitTable.setItems(trans);

        ObservableList<String> site = SiteDAO.getSites();
        containSite.setItems(site);
        ObservableList<String> type = TransitDAO.getType();
        transportType.setItems(type);

        minRange.setMaxLength(3);
        maxRange.setMaxLength(3);

        //will allow you to select a row without a radiobutton function
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                transitTable.requestFocus();
                transitTable.getSelectionModel().select(0);
                transitTable.scrollTo(0);
            }
        });
    }

    /**
     * filters the list
     *
     * @throws SQLException an sql stuff
     * @throws ClassNotFoundException if not found
     */
    public void filter() throws SQLException, ClassNotFoundException {
        ObservableList<Transit> list = TransitDAO.filter(containSite.getSelectionModel().getSelectedItem(), transportType.getSelectionModel().getSelectedItem(), Double.parseDouble(minRange.getText()), Double.parseDouble(maxRange.getText()), false);
        transitTable.setItems(list);
    }

    /**
     * back to pages depending on user
     */
    public void back() throws Exception {
        String userType = UserLoginController.getUserType();
        if (userType.equals("Manager")) {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerFunctionality.fxml"));
            Scene rootScene = new Scene(root, 350, 235);
            //go to next page
            Main.pstage.setScene(rootScene);
        } else if (userType.equals("ManagerVisitor")) {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerVisitorFunctionality.fxml"));
            Scene rootScene = new Scene(root, 350, 385);
            //go to next page
            Main.pstage.setScene(rootScene);
        } else if (userType.equals("Staff")) {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/StaffFunctionality.fxml"));
            Scene rootScene = new Scene(root, 235, 275);
            //go to next page
            Main.pstage.setScene(rootScene);
        } else if (userType.equals("StaffVisitor")) {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/StaffVisitorFunctionality.fxml"));
            Scene rootScene = new Scene(root, 350, 325);
            //go to next page
            Main.pstage.setScene(rootScene);
        } else if (userType.equals("Administrator")) {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorFunctionalityOnly.fxml"));
            Scene rootScene = new Scene(root, 350, 235);
            //go to next page
            Main.pstage.setScene(rootScene);
        } else if (userType.equals("AdministratorVisitor")) {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorFunctionality.fxml"));
            Scene rootScene = new Scene(root, 350, 275);
            //go to next page
            Main.pstage.setScene(rootScene);
        } else if (userType.equals("Visitor")) {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/VisitorFunctionality.fxml"));
            Scene rootScene = new Scene(root, 250, 320);
            //go to next page
            Main.pstage.setScene(rootScene);
        } else {
            Parent userFunctionality = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/userFunctionality.fxml"));
            Scene rootScene = new Scene(userFunctionality, 250, 200);
            //go to next page
            Main.pstage.setScene(rootScene);
        }
    }

    /**
     *
     */
    public void logTransit() throws ParseException {
        //check if it is null
        if (transitDate != null) {
            TransitDAO.logTransit(getRoute(), transportType.getSelectionModel().getSelectedItem(), transitDate.toString());
        }
    }

    /**
     * get a route
     * @return String
     */
    public String getRoute() {
        //make sure this exists first
        if (transitTable.getSelectionModel().getSelectedCells().get(0) != null) {
            //get selected row
            TablePosition pos = transitTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            String route = transitTable.getItems().get(row).getRoute();
            return route;
        }
        return "";
    }
}
