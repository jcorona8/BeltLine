package BeltLineApplication.java.controller;
import BeltLineApplication.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;


public class VisitorExploreSite {
    @FXML
    Button filter;
    @FXML
    Button siteDetail;
    @FXML
    Button transitDetail;
    @FXML
    Button back;
    @FXML
    CheckBox includeVisited;

    public static void initialize() {

    }
    public void filter() throws Exception{
        //filter
    }

    public void back() throws Exception {
        //need to add back to all employee hybrid users
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/fxml/VisitorFunctionalityOnly.fxml"));
        Scene rootScene = new Scene(root, 250, 350);
        Main.pstage.setScene(rootScene);
    }

    public void siteDetail() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/fxml/VisitorSiteDetail.fxml"));
        Scene rootScene = new Scene(root, 550, 250);
        Main.pstage.setScene(rootScene);
    }

    public void transitDetail() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/fxml/VisitorTransitDetail.fxml"));
        Scene rootScene = new Scene(root, 550, 450);
        Main.pstage.setScene(rootScene);
    }

    public void includeVisited() throws Exception {
        //include visited
    }

}
