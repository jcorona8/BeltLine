package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class VisitorVisitHistory {
    @FXML
    Button filter;
    @FXML
    Button back;

    public void filter() throws Exception {
        //filter
    }

    public void back() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/VisitorFunctionalityOnly.fxml"));
        Scene rootScene = new Scene(root, 250, 350);
        Main.pstage.setScene(rootScene);
    }


}
