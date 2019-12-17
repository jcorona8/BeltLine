package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import BeltLineApplication.java.database.TransitDAO;
import BeltLineApplication.java.database.UserDAO;
import BeltLineApplication.java.limiter.TextFieldLimit;
import BeltLineApplication.java.model.Transit;
import BeltLineApplication.java.model.User;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;

import java.sql.SQLException;

/**
 * Completed
 * @author Yaroslava
 */
public class AdministratorManageUserController {
    @FXML
    private TableView<User> manageUserTable;
    @FXML
    private TextFieldLimit username;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private ChoiceBox<String> status;
    private User user;

    /**
     * initialize the page
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<User> user = UserDAO.populateUser();
        manageUserTable.setItems(user);


        //will allow you to select a row without a radiobutton function
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                manageUserTable.requestFocus();
                manageUserTable.getSelectionModel().select(0);
                manageUserTable.scrollTo(0);
            }
        });
    }

    /**
     * filters the page
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void filter() throws SQLException, ClassNotFoundException {
        ObservableList<User> list = UserDAO.filter(username.getText(), type.getSelectionModel().getSelectedItem(), status.getSelectionModel().getSelectedItem()) ;
        manageUserTable.setItems(list);
    }

    /**
     * goes to the back page
     * @throws Exception
     */
    public void back() throws Exception {
        if (UserLoginController.getUserType().equals("Administrator")) {
            Parent administratorFunctionality = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorFunctionalityOnly.fxml"));
            Scene rootScene = new Scene(administratorFunctionality, 350, 250);
            Main.pstage.setScene(rootScene);
        } else {
            Parent administratorVisitorFunctionality = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorVisitorFunctionality.fxml"));
            Scene rootScene = new Scene(administratorVisitorFunctionality, 350, 250);
            Main.pstage.setScene(rootScene);
        }
    }

    /**
     * approves the user
     * @throws Exception
     */
    public void approve() throws Exception {
        if (manageUserTable.getSelectionModel().getSelectedCells().get(0) != null) {
            //get selected row
            TablePosition pos = manageUserTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            User item = manageUserTable.getItems().get(row);
            UserDAO.approve(item.getUsername());
        }
    }

    /**
     * declines the user
     * @throws Exception throws exception
     */
    public void decline() throws Exception {
        if (manageUserTable.getSelectionModel().getSelectedCells().get(0) != null) {
            //get selected row
            TablePosition pos = manageUserTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            User item = manageUserTable.getItems().get(row);
            UserDAO.decline(item.getUsername());
        }
    }
}
