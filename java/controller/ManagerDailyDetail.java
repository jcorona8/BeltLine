package BeltLineApplication.java.controller;
import BeltLineApplication.Main;
import BeltLineApplication.java.database.TransitDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class ManagerDailyDetail {
    @FXML
    Button back;
    @FXML
    TableColumn eventName;
    @FXML
    TableColumn staffName;
    @FXML
    TableColumn totalVisits;
    @FXML
    TableColumn totalRevenue;


    public void initialize() {
        //eventName.setCellValueFactory();
    }
    //get info for the table

    public void back() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerSiteReport.fxml"));
        Scene rootScene = new Scene(root, 550, 500);
        Main.pstage.setScene(rootScene);
    }
}
