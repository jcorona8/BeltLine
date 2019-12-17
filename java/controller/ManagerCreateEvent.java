package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import BeltLineApplication.java.database.EventDAO;
import BeltLineApplication.java.database.SiteDAO;
import BeltLineApplication.java.limiter.TextFieldLimit;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.sql.SQLException;

public class ManagerCreateEvent {
    @FXML
    Button back;
    @FXML
    Button createEvent;
    @FXML
    TextFieldLimit name;
    @FXML
    TextFieldLimit price;
    @FXML
    TextFieldLimit startDate;
    @FXML
    TextFieldLimit endDate;
    @FXML
    TextFieldLimit capacity;
    @FXML
    TextFieldLimit minSR;
    @FXML
    TextArea description;
    @FXML
    ListView<String> assignStaff;

    private Alert alert = new Alert(Alert.AlertType.ERROR);



    public void initialize() throws SQLException, ClassNotFoundException{
        ObservableList<String> list = EventDAO.getStaff();
        assignStaff.setItems(list);
        assignStaff.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    private boolean isFull() {
        return (!name.getText().isEmpty() && !price.getText().isEmpty() && !startDate.getText().isEmpty() && !endDate.getText().isEmpty() && !capacity.getText().isEmpty() && !minSR.getText().isEmpty() && !description.getText().isEmpty());
    }


    public void back() throws Exception {
        if (UserLoginController.getUserType().equals("ManagerVisitor")) {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerVisitorFunctionality.fxml"));
            Scene rootScene = new Scene(root, 324, 305);
            Main.pstage.setScene(rootScene);
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerFunctionality.fxml"));
            Scene rootScene = new Scene(root, 350, 250);
            Main.pstage.setScene(rootScene);
        }
    }

    public void createEvent() throws SQLException, ClassNotFoundException {
        //ename and start date are unique
        //two events with same name cannot overlap dates
        //assign staff is staff who are not working other events at the time
        if (SiteDAO.checkSiteExist(UserLoginController.getUsername())){
            if (isFull() && (Double.parseDouble(price.getText()) >= 0) && (Integer.parseInt(capacity.getText()) > 0) && (Integer.parseInt(minSR.getText()) > 0)) {
                try {
                    EventDAO.createEvent(name.getText(), startDate.getText(),SiteDAO.getSite(UserLoginController.getUsername()), endDate.getText(), Double.parseDouble(price.getText()), Integer.parseInt(capacity.getText()) , description.getText(), Integer.parseInt(minSR.getText()));
                }
                catch (SQLException e) {
                    System.out.println("Error with creating a new event: " + e);
                } finally {
                    alert.setTitle("Created Event");
                    alert.setHeaderText(null);
                    alert.setContentText("Success! Event has been created successfully!");
                }
            } else {
                    alert.setTitle("Empty/Incorrect Field");
                    alert.setHeaderText(null);
                    alert.setContentText("One or more fields are empty/incorrect");
            }
        }
    }
}
