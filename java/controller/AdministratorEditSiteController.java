package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import BeltLineApplication.java.database.SiteDAO;
import BeltLineApplication.java.model.Site;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.sql.SQLException;

/**
 * Completed
 * @author Yaroslava
 */
public class AdministratorEditSiteController {
    @FXML
    private TextField name;
    @FXML
    private TextField zipcode;
    @FXML
    private TextField address;
    @FXML
    private ChoiceBox<String> manager;
    @FXML
    private CheckBox openEveryday;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private static Site site;

    public void initialize() throws SQLException, ClassNotFoundException {
        Site item = getSite();

        //start here...
        name.setText(item.getSname());
        ObservableList<String> list = SiteDAO.getManagerList();
        manager.setItems(list);
        manager.setValue(item.getManagerUsername());
        openEveryday.setSelected(item.toString().equals("Y"));

    }

    /**
     * get site object
     * @return
     */
    public static Site getSite() {
        return site;
    }

    /**
     * set site
     * @param s
     */
    public static void setSite(Site s) {
        site = s;
    }

    /**
     * goes to the manage site page
     * @throws Exception
     */
    public void back() throws Exception {
        Parent administratorManageSite = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorManageSite.fxml"));
        Scene rootScene = new Scene(administratorManageSite, 350, 250);
        Main.pstage.setScene(rootScene);
    }

    /**
     * update site
     */
    public void update() {
        //make sure none of them are empty
        if (!name.getText().isEmpty() || !address.getText().isEmpty() || !zipcode.getText().isEmpty() || !manager.getSelectionModel().isEmpty()) {
            //try to update the site
            String managerUsername = "";
            try {
                managerUsername = SiteDAO.getManagerUsername(manager.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                System.out.println("Issue with SQL" + e);
                throw e;
            }
            try {
                SiteDAO.updateSite(name.getText(), address.getText(), Integer.parseInt(zipcode.getText()), openEveryday.isSelected(), managerUsername);
            } catch (Exception e) {
                System.out.println("Issue with SQL" + e);
                throw e;
            }
            alert.setTitle("Update Site");
            alert.setHeaderText(null);
            alert.setContentText("Success! Site has been updated Successfully!");
        }
    }
}
