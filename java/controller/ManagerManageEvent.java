package BeltLineApplication.java.controller;
import BeltLineApplication.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class ManagerManageEvent {
    @FXML
    Button create;
    @FXML
    Button filter;
    @FXML
    Button viewEdit;
    @FXML
    Button delete;
    @FXML
    Button back;
    @FXML
    TableColumn name;
    @FXML
    TableColumn staffCount;
    @FXML
    TableColumn duration;
    @FXML
    TableColumn visits;
    @FXML
    TableColumn revenue;


    public void create() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerCreateEvent.fxml"));
        Scene rootScene = new Scene(root, 500, 550);
        Main.pstage.setScene(rootScene);
    }

    public void filter() {
        //filter
    }

    public void viewEdit() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerViewEditEvent.fxml"));
        Scene rootScene = new Scene(root, 500, 850);
        Main.pstage.setScene(rootScene);
    }

    public void delete() throws Exception {
        //delete
    }

    public void back() throws Exception {
        //back to hybrid visitor as well
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerFunctionality.fxml"));
        Scene rootScene = new Scene(root, 350, 250);
        Main.pstage.setScene(rootScene);
    }
}
