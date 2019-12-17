package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class ManagerViewEditEvent {
    @FXML
    Button filter;
    @FXML
    Button back;
    @FXML
    Button update;

    public void filter() {
        //filter
    }

    public void back() throws Exception {
        //back to hybrid visitor as well
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerManageEvent.fxml"));
        Scene rootScene = new Scene(root, 750, 600);
        Main.pstage.setScene(rootScene);
    }

    public void update() throws Exception {
       //update
    }
}
