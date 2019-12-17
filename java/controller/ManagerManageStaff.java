package BeltLineApplication.java.controller;
import BeltLineApplication.Main;
import BeltLineApplication.java.database.SiteDAO;
import BeltLineApplication.java.database.StaffDAO;
import BeltLineApplication.java.model.Staff;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.sql.SQLException;

public class ManagerManageStaff {
    @FXML
    Button filter;
    @FXML
    Button back;
    @FXML
    ChoiceBox<String> sites;
    @FXML
    TableView<Staff> staffTable;
    @FXML
    TableColumn<Staff, String> staffName;
    @FXML
    TableColumn<Staff, Integer> numEventShifts;
    @FXML
    TextField fName;
    @FXML
    TextField lName;
    @FXML
    TextField sDate;
    @FXML
    TextField eDate;

    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> list = SiteDAO.getSites();
        sites.setItems(list);
        sites.getSelectionModel();

        ObservableList<Staff> staff = StaffDAO.manageStaff();
        staffTable.setItems(staff);

    }

    public void filter() throws SQLException, ClassNotFoundException {
       ObservableList<Staff> list = StaffDAO.filterStaff(fName.getText(), lName.getText(), sDate.getText(), eDate.getText(), sites.getSelectionModel().toString());
       staffTable.setItems(list);

    }

    public void back() throws Exception {
        if (UserLoginController.getUserType().equals("ManagerVisitor")){
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerVisitorFunctionality.fxml"));
            Scene rootScene = new Scene(root, 325, 280);
            Main.pstage.setScene(rootScene);
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/ManagerFunctionality.fxml"));
            Scene rootScene = new Scene(root, 350, 250);
            Main.pstage.setScene(rootScene);
        }
    }
}
