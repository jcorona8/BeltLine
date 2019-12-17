package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Completed
 * @author Yaroslava
 */
public class AdministratorVisitorFunctionalityController {
    /**
     * manage Profile
     * @throws Exception
     */
    public void manageProfile() throws Exception {
        Parent administratorManageProfile = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/EmployeeManageProfile.fxml"));
        Scene rootScene = new Scene(administratorManageProfile, 600, 400);
        Main.pstage.setScene(rootScene);
    }

    /**
     * manage User
     * @throws Exception
     */
    public void manageUser() throws Exception {
        Parent administratorManageUser = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorManageUser.fxml"));
        Scene rootScene = new Scene(administratorManageUser, 600, 450);
        Main.pstage.setScene(rootScene);
    }

    /**
     * manage Transit
     * @throws Exception
     */
    public void manageTransit() throws Exception {
        Parent administratorManageTransit = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorManageTransit.fxml"));
        Scene rootScene = new Scene(administratorManageTransit, 550, 475);
        Main.pstage.setScene(rootScene);
    }

    /**
     * manage Site
     * @throws Exception
     */
    public void manageSite() throws Exception {
        Parent administratorManageSite = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorManageSite.fxml"));
        Scene rootScene = new Scene(administratorManageSite, 600, 450);
        Main.pstage.setScene(rootScene);
    }

    /**
     * take transit
     * @throws Exception
     */
    public void takeTransit() throws Exception {
        Parent userTakeTransit = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/UserTakeTransit.fxml"));
        Scene rootScene = new Scene(userTakeTransit, 600, 450);
        Main.pstage.setScene(rootScene);
    }

    /**
     * view Transit History
     * @throws Exception
     */
    public void viewTransitHistory() throws Exception {
        Parent userTransitHistory = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/UserTransitHistory.fxml"));
        Scene rootScene = new Scene(userTransitHistory, 600, 450);
        Main.pstage.setScene(rootScene);
    }

    /**
     * explore event
     * @throws Exception
     */
    public void exploreEvent() throws Exception {
        Parent exploreEvent = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/VisitorExploreEvent.fxml"));
        Scene rootScene = new Scene(exploreEvent, 725, 650);
        Main.pstage.setScene(rootScene);
    }

    /**
     * explore sites
     * @throws Exception
     */
    public void exploreSite() throws Exception {
        Parent exploreSite = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/VisitorExploreSite.fxml"));
        Scene rootScene = new Scene(exploreSite, 725, 650);
        Main.pstage.setScene(rootScene);
    }

    /**
     * view visit history
     * @throws Exception
     */
    public void viewVisitHistory() throws Exception {
        Parent visitHistory = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/VisitorVisitHistory.fxml"));
        Scene rootScene = new Scene(visitHistory, 600, 450);
        Main.pstage.setScene(rootScene);
    }

    /**
     * back to login
     * @throws Exception
     */
    public void back() throws Exception {
        Parent userLogin = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/UserLogin.fxml"));
        Scene rootScene = new Scene(userLogin, 250, 300);
        Main.pstage.setScene(rootScene);
    }
}
