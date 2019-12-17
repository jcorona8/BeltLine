package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import BeltLineApplication.java.database.EmailDAO;
import BeltLineApplication.java.database.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

/**
 * Completed
 * @author Yaroslava
 */
public class UserLoginController {

    @FXML
    private Label email;
    @FXML
    private Label password;
    @FXML
    private TextField emailText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Button login;
    @FXML
    private Button register;

    private Alert errorAlert = new Alert(AlertType.ERROR);
    private static String userType;
    private static String username;

    /**
     * set user type
     * @param user
     */
    public static void setUserType(String user) {
        userType = user;
    }

    /**
     * get user type
     * @return
     */
    public static String getUserType() {
        return userType;
    }

    /**
     * set username
     * not stored in the database, only for front end
     * @param user
     */
    public static void setUsername(String user) {
        username = user;
    }

    /**
     * get username
     * not stored in the database, only for front end
     * @return
     */
    public static String getUsername() {
        return username;
    }

    /**
     * login into the app
     * @throws Exception
     */
    public void login() throws Exception {
        // if email and/or password is empty, do not login
        if (!emailText.getText().isEmpty() || !passwordText.getText().isEmpty()) {

            //fist get username from email.
            String username = EmailDAO.getUsername(emailText.getText());
            setUsername(username);

            String userType = UserDAO.isUser(username);
            setUserType(userType);

            if (UserDAO.isApproved(username)) {
                //login user
                if (UserDAO.loginUser(emailText.getText(), passwordText.getText())) {
                    if (userType.equals("Manager")) {
                        Parent managerFunc = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerFunctionality.fxml"));
                        Scene rootScene = new Scene(managerFunc, 350, 235);
                        //go to next page
                        Main.pstage.setScene(rootScene);
                    } else if (userType.equals("ManagerVisitor")) {
                        Parent managerVisitor = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerVisitorFunctionality.fxml"));
                        Scene rootScene = new Scene(managerVisitor, 350, 385);
                        //go to next page
                        Main.pstage.setScene(rootScene);
                    } else if (userType.equals("Staff")) {
                        Parent staffFunc = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/StaffFunctionality.fxml"));
                        Scene rootScene = new Scene(staffFunc, 235, 275);
                        //go to next page
                        Main.pstage.setScene(rootScene);
                        return;
                    } else if (userType.equals("StaffVisitor")) {
                        Parent staffVis = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/StaffVisitorFunctionality.fxml"));
                        Scene rootScene = new Scene(staffVis, 350, 325);
                        //go to next page
                        Main.pstage.setScene(rootScene);
                        return;
                    } else if (userType.equals("Administrator")) {
                        Parent Administrator = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorFunctionalityOnly.fxml"));
                        Scene rootScene = new Scene(Administrator, 350, 235);
                        //go to next page
                        Main.pstage.setScene(rootScene);
                        return;
                    } else if (userType.equals("AdministratorVisitor")) {
                        Parent Adminfunction = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorFunctionality.fxml"));
                        Scene rootScene = new Scene(Adminfunction, 350, 275);
                        //go to next page
                        Main.pstage.setScene(rootScene);
                        return;
                    } else if (userType.equals("Visitor")) {
                        Parent visitorFunc = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/VisitorFunctionality.fxml"));
                        Scene rootScene = new Scene(visitorFunc, 250, 320);
                        //go to next page
                        Main.pstage.setScene(rootScene);
                        return;
                    } else {
                        Parent userFunctionality = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/userFunctionality.fxml"));
                        Scene rootScene = new Scene(userFunctionality, 250, 200);
                        //go to next page
                        Main.pstage.setScene(rootScene);
                    }
                } else {
                    errorAlert.setTitle("User UserLoginController");
                    errorAlert.setHeaderText("Email and password do not match");
                    errorAlert.setContentText("Please try again.");
                    errorAlert.showAndWait();
                }
            } else {
                errorAlert.setTitle("User UserLoginController");
                errorAlert.setHeaderText("Not an approved user");
                errorAlert.setContentText("Please wait until you are approved.");
                errorAlert.showAndWait();
            }
        } else {
            errorAlert.setTitle("User UserLoginController");
            errorAlert.setHeaderText("Email and password must be filled");
            errorAlert.setContentText("Please try again or if this is your first time, register");
            errorAlert.showAndWait();
        }
    }

    /**
     * goes to register page
     * @throws Exception
     */
    public void register() throws Exception {
        Parent registerNavigation = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/RegisterNavigation.fxml"));
        Scene registerNavigationScene = new Scene(registerNavigation, 250, 300);
        Main.pstage.setScene(registerNavigationScene);
    }
}
