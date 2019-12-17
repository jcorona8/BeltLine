package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import BeltLineApplication.java.database.EventDAO;
import BeltLineApplication.java.model.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class VisitorEventDetail {
    @FXML
    Button logVisit;
    @FXML
    Button back;
    @FXML
    Label event;
    @FXML
    Label site;
    @FXML
    Label sDate;
    @FXML
    Label eDate;
    @FXML
    Label price;
    @FXML
    Label remaining;
    @FXML
    Label description;
    @FXML
    TextField lV;
    private Event selectedEvent;

    public void initialize(Event e){
        selectedEvent = e;
        event.setText(selectedEvent.getEname());
        site.setText(selectedEvent.getSname());
        sDate.setText(selectedEvent.getStartDate());
        eDate.setText(selectedEvent.getEndDate());
        price.setText(Double.toString(selectedEvent.getPrice()));
        remaining.setText(Integer.toString(selectedEvent.getCapacity() - selectedEvent.getTotalVisits()));
    }
    public void logVisit() throws Exception {
        EventDAO.logEventVisit(UserLoginController.getUsername(), event.getText(), site.getText(), sDate.getText(), lV.getText());
    }

    public void back() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/VisitorExploreSite.fxml"));
        Scene rootScene = new Scene(root, 725, 650);
        Main.pstage.setScene(rootScene);
    }


}