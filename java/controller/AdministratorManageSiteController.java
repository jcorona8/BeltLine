package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import BeltLineApplication.java.database.ManagerDAO;
import BeltLineApplication.java.database.SiteDAO;
import BeltLineApplication.java.model.Site;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

/**
 * Completed
 * @author Yaroslava
 */
public class AdministratorManageSiteController {
    @FXML
    private TableView<Site> site;
    @FXML
    private ChoiceBox<String> manager;
    @FXML
    private ChoiceBox openEveryday;
    @FXML
    private ChoiceBox<String> sites;
    @FXML
    private TableColumn<Site, String> nameCol;
    @FXML
    private TableColumn<Site, String> managerCol;
    @FXML
    private TableColumn<Site, String> openEverydayCol;

    /**
     * initializes the first data
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<Site> sit = SiteDAO.populateSite();
        for(int i = 0; i < sit.size(); i++) {
            nameCol.setCellValueFactory(new PropertyValueFactory<>(sit.get(i).getSname()));
            managerCol.setCellValueFactory(new PropertyValueFactory<>(sit.get(i).getManagerUsername()));
        }

        ObservableList<String> siteComb = SiteDAO.getSites();
        sites.setItems(siteComb);

        ObservableList<String> mang = ManagerDAO.getManagerList();
        manager.setItems(mang);

        //will allow you to select a row without a radiobutton function
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                site.requestFocus();
                site.getSelectionModel().select(0);
                site.scrollTo(0);
            }
        });
    }

    /**
     * filters liset
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void filter() throws SQLException, ClassNotFoundException {
        ObservableList<Site> list = SiteDAO.filter(sites.getSelectionModel().getSelectedItem().toString(), manager.getSelectionModel().getSelectedItem().toString(), openEveryday.getSelectionModel().getSelectedItem().toString());
        for(int i = 0; i < list.size(); i++) {
            nameCol.setCellValueFactory(new PropertyValueFactory<>(list.get(i).getSname()));
            managerCol.setCellValueFactory(new PropertyValueFactory<>(list.get(i).getManagerUsername()));
        }
    }

    /**
     * goes to the back page based on user type
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
     * goes to delete page
     */
    public void delete() {
        //make sure this exists first
        if (site.getSelectionModel().getSelectedCells().get(0) != null) {
            //get selected row
            TablePosition pos = site.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            Site item = site.getItems().get(row);
            SiteDAO.delete(item);
        }
    }

    /**
     * goes to edit page
     * @throws Exception
     */
    public void edit() throws Exception {
        //make sure the table exists first
        if (site.getSelectionModel().getSelectedCells().get(0) != null) {
            //get selected row
            TablePosition pos = site.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            Site item = site.getItems().get(row);
            AdministratorEditSiteController.setSite(item);

            Parent administratorEditSite = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorEditSite.fxml"));
            Scene rootScene = new Scene(administratorEditSite, 405, 245);
            Main.pstage.setScene(rootScene);
        }
    }

    /**
     * goes to create page
     * @throws Exception
     */
    public void create() throws Exception {
        Parent administratorCreateSite = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorCreateSite.fxml"));
        Scene rootScene = new Scene(administratorCreateSite, 600, 450);
        Main.pstage.setScene(rootScene);
    }
}
