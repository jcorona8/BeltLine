package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import BeltLineApplication.java.database.EmailDAO;
import BeltLineApplication.java.database.EmployeeDAO;
import BeltLineApplication.java.database.VisitorDAO;
import BeltLineApplication.java.limiter.TextFieldLimit;
import BeltLineApplication.java.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

/**
 * Completed
 * @author Yaroslava
 */
public class EmployeeManageProfileController {
    @FXML
    private TextFieldLimit fname;
    @FXML
    private TextFieldLimit lname;
    @FXML
    private TextFieldLimit phone;
    @FXML
    private Label username;
    @FXML
    private Label empID;
    @FXML
    private Label siteName;
    @FXML
    private Label address;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button add;
    @FXML
    private CheckBox visitorAccount;
    @FXML
    private TextFieldLimit emailTextField;

    //button for email stuff
    private Button remove = new Button("remove");
    private Label email = new Label();
    private ObservableList<Button> buttons = FXCollections.observableArrayList();
    private ObservableList<Label> labels = FXCollections.observableArrayList();

    private static Employee emp;
    private int counter;

    /**
     * initializes data
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void initialize() throws SQLException, ClassNotFoundException {
        Employee emp = EmployeeDAO.populate();
        ObservableList<String> emails = EmailDAO.populateEmails();

        //populates data
        fname.setText(emp.getFname());
        lname.setText(emp.getLname());
        username.setText(emp.getUsername());
        siteName.setText(emp.getSname());
        empID.setText("" + emp.getEmployeeID());
        phone.setText(emp.getPhone());
        address.setText(emp.getAddress());

        //sees if visitor
        visitorAccount.setSelected(VisitorDAO.isVisitor(UserLoginController.getUsername()));

        //add emails
        counter = emails.size();
        buttons.add(add);
        for (int i = 0; i < emails.size(); i++) {
            addEmail();
        }
    }

    /**
     * gets Employee object
     *
     * @return
     */
    public static Employee getEmployee() {
        return emp;
    }

    /**
     * sets employee object
     *
     * @param employee
     */
    public static void setEmployee(Employee employee) {
        emp = employee;
    }

    /**
     * goes back
     *
     * @throws Exception
     */
    public void back() throws Exception {
        if (UserLoginController.getUserType().equals("Manager")) {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerFunctionality.fxml"));
            Scene rootScene = new Scene(root, 350, 235);
            //go to next page
            Main.pstage.setScene(rootScene);
        } else if (UserLoginController.getUserType().equals("ManagerVisitor")) {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerVisitorFunctionality.fxml"));
            Scene rootScene = new Scene(root, 350, 385);
            //go to next page
            Main.pstage.setScene(rootScene);
        } else if (UserLoginController.getUserType().equals("Staff")) {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/StaffFunctionality.fxml"));
            Scene rootScene = new Scene(root, 235, 275);
            //go to next page
            Main.pstage.setScene(rootScene);
        } else if (UserLoginController.getUserType().equals("StaffVisitor")) {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/StaffVisitorFunctionality.fxml"));
            Scene rootScene = new Scene(root, 350, 325);
            //go to next page
            Main.pstage.setScene(rootScene);
        } else if (UserLoginController.getUserType().equals("Administrator")) {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorFunctionality.fxml"));
            Scene rootScene = new Scene(root, 350, 235);
            //go to next page
            Main.pstage.setScene(rootScene);
        } else if (UserLoginController.getUserType().equals("AdministratorVisitor")) {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorFunctionality.fxml"));
            Scene rootScene = new Scene(root, 350, 275);
            //go to next page
            Main.pstage.setScene(rootScene);
        }
    }

    /**
     * update on click
     * @throws Exception
     */
    public void update() throws Exception {
        EmployeeDAO.update(fname.getText(), lname.getText(), username.getText(), Integer.parseInt(phone.getText()));

        //delete visitor if it became unselected
        if (!visitorAccount.isSelected()) {
            //check if it was an account
            if (VisitorDAO.isVisitor(UserLoginController.getUsername())) {
                VisitorDAO.delete(UserLoginController.getUsername());
            }
        }

        for (int i=0; i < labels.size(); i++) {
            //check if email is database
            if (EmailDAO.getUsername(labels.get(i).toString()) == null) {
                //register email
                EmailDAO.registerEmail(UserLoginController.getUsername(), labels.get(i).toString());
            }
        }
    }

    /**
     * add email / set to label
     */
    public void addEmail() {
        email.setStyle("-fx-layoutY: " + emailTextField.getLayoutY());
        email.setStyle("-fx-locationX: " + emailTextField.getLayoutX());

        int num = getCounter();

        if (num < 3) {
            //adjust the remove button
            remove = new Button("Remove");
            email = new Label(emailTextField.getText());
            String numStr = "" + num;

            remove.setId(numStr);
            email.setId(numStr);

            remove.setStyle("-fx-pref-width: " + 100);
            num++;
            setCounter(num);
            remove.setOnAction(e -> removeEmail());
            remove.setLayoutX(add.getLayoutX());
            remove.setLayoutY(add.getLayoutY());
            add.setLayoutY(add.getLayoutY() + 33);

            //adjust new label / field
            emailTextField.setLayoutY(add.getLayoutY());
            email.setLayoutY(remove.getLayoutY() + 3);
            email.setLayoutX(emailTextField.getLayoutX());
            emailTextField.setText("");
            anchorPane.getChildren().addAll(email, remove);
            buttons.add(remove);
            labels.add(email);
        }
    }

    /**
     * removes email from the email list
     */
    public void removeEmail() {
        if (getCounter() >= 0) {
            anchorPane.getChildren().remove(buttons.remove(getCounter()));
            anchorPane.getChildren().remove(labels.remove(getCounter()-1));
            setCounter(getCounter()-1);
        }
    }

    /**
     * counter for the emails
     * @return
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * setter for the counter
     * @param c
     */
    public void setCounter(int c) {
        this.counter = c;
    }
}
