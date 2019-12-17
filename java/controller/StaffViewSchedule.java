package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import BeltLineApplication.java.database.EventDAO;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.sql.SQLException;

public class StaffViewSchedule {
    @FXML
    Button filter;
    @FXML
    Button back;
    @FXML
    Button viewEvent;
    @FXML
    TableView<String> schedule;
    @FXML
    TableColumn eName;
    @FXML
    TableColumn sName;
    @FXML
    TableColumn sDate;
    @FXML
    TableColumn eDate;
    @FXML
    TableColumn staffCount;
    @FXML
    TextField eventName;
    @FXML
    TextField descKey;
    @FXML
    TextField startDate;
    @FXML
    TextField endDate;



    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> sched = EventDAO.populateStaffSchedule();
        schedule.setItems(sched);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                schedule.requestFocus();
                schedule.getSelectionModel().select(0);
                schedule.scrollTo(0);
            }
        });
    }

    public void filter() throws ClassNotFoundException, SQLException{
        //filter, event name, description keyword, start date, end date
        ObservableList<String> list = EventDAO.staffSchedFilter(eventName.getText(), staffCount.getText() , startDate.getText(), endDate.getText());
        schedule.setItems(list);

    }

    public void back() throws Exception {
        //back to hybrid visitor as well
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/StaffFunctionality.fxml"));
        Scene rootScene = new Scene(root, 750, 600);
        Main.pstage.setScene(rootScene);
    }

    public void viewEvent() throws Exception {
        //if they have chosen a button go to that event
        if(schedule.getSelectionModel() != null) {
            TablePosition pos = schedule.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();


            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/StaffEventDetail.fxml"));
            Scene rootScene = new Scene(root, 600, 400);
            Main.pstage.setScene(rootScene);

        }
    }
}
