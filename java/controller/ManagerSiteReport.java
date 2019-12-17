package BeltLineApplication.java.controller;
import BeltLineApplication.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class ManagerSiteReport {

    @FXML
    Button filter;
    @FXML
    Button back;
    @FXML
    Button dailyDetail;

    public void filter() {
        //filter
    }

    public void back() throws Exception {
        //back to hybrid visitor as well
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerFunctionality.fxml"));
        Scene rootScene = new Scene(root, 350, 250);
        Main.pstage.setScene(rootScene);
    }

    public void dailyDetail() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerDailyDetail.fxml"));
        Scene rootScene = new Scene(root, 650, 500);
        Main.pstage.setScene(rootScene);
    }

}
