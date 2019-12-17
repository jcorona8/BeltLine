package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import BeltLineApplication.java.database.EmailDAO;
import BeltLineApplication.java.database.UserDAO;
import BeltLineApplication.java.database.VisitorDAO;
import BeltLineApplication.java.limiter.PasswordFieldLimit;
import BeltLineApplication.java.limiter.TextFieldLimit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

/**
 * Completed
 * @author Yaroslava
 */
public class RegisterVisitorController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextFieldLimit fname;
    @FXML
    private TextFieldLimit lname;
    @FXML
    private TextFieldLimit username;
    @FXML
    private PasswordFieldLimit password;
    @FXML
    private PasswordFieldLimit confirmPassword;
    @FXML
    private TextFieldLimit emailTextField;
    @FXML
    private Button add;
    private int counter;
    private Alert errorAlert = new Alert(AlertType.ERROR);
    private Button remove = new Button("remove");
    private Label email = new Label();
    private ObservableList<Button> buttons = FXCollections.observableArrayList();
    private ObservableList<Label> labels = FXCollections.observableArrayList();

    public void initialize() {
        //set limit on textFields
        fname.setMaxLength(50);
        lname.setMaxLength(50);
        username.setMaxLength(50);
        emailTextField.setMaxLength(50);

        password.setMaxLength(50);
        confirmPassword.setMaxLength(50);

        buttons.add(add);
    }

    public void register() throws Exception {
        Parent login = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/userLogin.fxml"));
        Scene rootScene = new Scene(login, 280, 215);

        //None of the fields can be empty
        if (!username.getText().isEmpty() || !password.getText().isEmpty() || !confirmPassword.getText().isEmpty() || !fname.getText().isEmpty() || !lname.getText().isEmpty() || !labels.isEmpty()) {
            //password must equal password
            if (password.getText().equals(confirmPassword.getText()) && password.getText().length() > 7) {
                UserDAO.registerUser(username.getText(), password.getText(), fname.getText(), lname.getText());
                if (!labels.isEmpty()) {
                    for (int i=0; i<labels.size(); i++) {
                        EmailDAO.registerEmail(username.getText(), labels.get(i).getText());
                    }
                }
                VisitorDAO.registerVisitor(username.getText());
                Main.pstage.setScene(rootScene);
            } else {
                errorAlert.setTitle("Password Fail");
                errorAlert.setHeaderText("Passwords do not match");
                errorAlert.setContentText("Please try again");
                errorAlert.showAndWait();
            }
        } else {
            errorAlert.setTitle("Required Fields");
            errorAlert.setHeaderText("All fields are required");
            errorAlert.setContentText("Please try again");
            errorAlert.showAndWait();
        }
    }

    public void back() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/RegisterNavigation.fxml"));
        Scene rootScene = new Scene(root, 250, 300);
        Main.pstage.setScene(rootScene);
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
