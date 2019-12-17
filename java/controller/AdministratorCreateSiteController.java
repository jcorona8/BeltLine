package BeltLineApplication.java.controller;

import BeltLineApplication.java.database.ManagerDAO;
import BeltLineApplication.java.database.SiteDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import BeltLineApplication.java.limiter.TextFieldLimit;

import java.sql.SQLException;

/**
 * Completed
 * @author Yaroslava
 */
public class AdministratorCreateSiteController {
    @FXML
    private ChoiceBox<String> managerChoiceCombo;
    @FXML
    private TextFieldLimit nameTextField;
    @FXML
    private TextFieldLimit zipcodeTextField;
    @FXML
    private TextFieldLimit addressTextField;
    @FXML
    private CheckBox everyday;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Method will start when Administrator Create Site scene.
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> list = ManagerDAO.getManagerList();
        managerChoiceCombo.setItems(list);

        //set limit on textFields
        nameTextField.setMaxLength(50);
        zipcodeTextField.setMaxLength(5);
        addressTextField.setMaxLength(50);
    }

    /**
     * Create method refers to the create button
     * This method will create a new site when invoked
     */
    public void create() {
        if (!nameTextField.getText().isEmpty() || !zipcodeTextField.getText().isEmpty() || !managerChoiceCombo.getSelectionModel().getSelectedItem().isEmpty()) {
            try {
                SiteDAO.createSite(nameTextField.getText(), addressTextField.getText(), Integer.parseInt(zipcodeTextField.getText()), everyday.isSelected(), managerChoiceCombo.getSelectionModel().getSelectedItem());
            } catch (SQLException e) {
                System.out.println("Error with creating a new site: " + e);
            } finally {
                alert.setTitle("Created Site");
                alert.setHeaderText(null);
                alert.setContentText("Success! Site has been created successfully!");
            }
        }
    }
}
