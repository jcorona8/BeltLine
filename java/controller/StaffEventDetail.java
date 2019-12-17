package BeltLineApplication.java.controller;
import BeltLineApplication.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class StaffEventDetail {
    @FXML
    Button back;

    //populate all info
    public void initialize() {

    }

    public void back() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/StaffViewSchedule.fxml"));
        Scene rootScene = new Scene(root, 600, 600);
        Main.pstage.setScene(rootScene);
    }
}
