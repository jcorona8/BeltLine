package BeltLineApplication.java.controller;
import BeltLineApplication.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * Completed
 * @author Julia
 */
public class ManagerVisitorFunctionality {
    @FXML
    Button manageProfile;
    @FXML
    Button manageEvent;
    @FXML
    Button viewStaff;
    @FXML
    Button takeTransit;
    @FXML
    Button viewSiteReport;
    @FXML
    Button viewTransitHistory;
    @FXML
    Button back;
    @FXML
    Button exploreEvent;
    @FXML
    Button exploreSite;
    @FXML
    Button viewVisitHistory;

    public void manageProfile() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/EmployeeManageProfile.fxml"));
        Scene rootScene = new Scene(root, 400, 300);
        Main.pstage.setScene(rootScene);
    }

    public void manageEvent() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerManageEvent.fxml"));
        Scene rootScene = new Scene(root, 750, 600);
        Main.pstage.setScene(rootScene);
    }

    public void viewStaff() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerManageStaff.fxml"));
        Scene rootScene = new Scene(root, 600, 600);
        Main.pstage.setScene(rootScene);
    }

    public void viewSiteReport() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerSiteReport.fxml"));
        Scene rootScene = new Scene(root, 550, 500);
        Main.pstage.setScene(rootScene);
    }

    public void takeTransit() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/UserTakeTransit.fxml"));
        Scene rootScene = new Scene(root, 600, 450);
        Main.pstage.setScene(rootScene);
    }

    public void viewTransitHistory() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/UserTransitHistory.fxml"));
        Scene rootScene = new Scene(root, 600, 450);
        Main.pstage.setScene(rootScene);
    }

    public void back() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/UserLogin.fxml"));
        Scene rootScene = new Scene(root, 280, 215);
        Main.pstage.setScene(rootScene);
    }

    public void exploreEvent() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/VisitorExploreSite.fxml"));
        Scene rootScene = new Scene(root, 725, 650);
        Main.pstage.setScene(rootScene);
    }

    public void exploreSite() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/VisitorExploreSite.fxml"));
        Scene rootScene = new Scene(root, 725, 650);
        Main.pstage.setScene(rootScene);
    }

    public void viewVisitHistory()throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/VisitorVisitHistory.fxml"));
        Scene rootScene = new Scene(root, 600, 450);
        Main.pstage.setScene(rootScene);
    }

}
