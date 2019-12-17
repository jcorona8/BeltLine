package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * Completed
 * @author Yaroslava
 */
public class RegisterNavigationController {

    public void userOnly() throws Exception {
        Parent registerUser = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/RegisterUser.fxml"));
        Scene registerUserScene = new Scene(registerUser, 600, 400);
        Main.pstage.setScene(registerUserScene);
    }

    public void visitorOnly() throws Exception {
        Parent registerVisitor = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/RegisterVisitorOnly.fxml"));
        Scene registerVisitorScene = new Scene(registerVisitor, 600, 400);
        Main.pstage.setScene(registerVisitorScene);
    }

    public void employeeOnly() throws Exception {
        Parent registerEmployee = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/RegisterEmployeeOnly.fxml"));
        Scene registerEmployeeScene = new Scene(registerEmployee, 600, 500);
        Main.pstage.setScene(registerEmployeeScene);
    }

    public void employeeVisitor() throws Exception {
        Parent registerEmployeeVisitor = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/RegisterEmployeeVisitor.fxml"));
        Scene registerEmployeeVisitorScene = new Scene(registerEmployeeVisitor, 600, 550);
        Main.pstage.setScene(registerEmployeeVisitorScene);
    }

    public void back() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/UserLogin.fxml"));
        Scene rootScene = new Scene(root, 280, 215);
        Main.pstage.setScene(rootScene);
    }

}
