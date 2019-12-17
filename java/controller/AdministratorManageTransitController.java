package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import BeltLineApplication.java.database.SiteDAO;
import BeltLineApplication.java.database.TransitDAO;
import BeltLineApplication.java.limiter.TextFieldLimit;
import BeltLineApplication.java.model.Transit;
import javafx.application.Platform;
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
public class AdministratorManageTransitController {
    @FXML
    private ChoiceBox<String> transportType;
    @FXML
    private ChoiceBox<String> containSite;
    @FXML
    private TextFieldLimit route;
    @FXML
    private TextFieldLimit minRange;
    @FXML
    private TextFieldLimit maxRange;
    @FXML
    private TableView<Transit> transitTable;
    @FXML
    private TableColumn<Transit, String> routeCol;
    @FXML
    private TableColumn<Transit, String> transportTypeCol;
    @FXML
    private TableColumn<Transit, Double> priceCol;
    @FXML
    private TableColumn<Transit, Integer> connectedSitesCol;
    @FXML
    private TableColumn<Transit, Integer> transitLoggedCol;
    private String userType;

    /**
     * initializes first
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<Transit> trans = TransitDAO.populateTransit(true);
        for (int i = 0; i < trans.size(); i++) {
            routeCol.setCellValueFactory(new PropertyValueFactory<>(trans.get(i).getRoute()));
            transportTypeCol.setCellValueFactory(new PropertyValueFactory<>(trans.get(i).getType()));
            //priceCol.setCellFactory(new PropertyValueFactory<>(trans.get(i).getPrice()));
            //connectedSitesCol.setCellValueFactory(new PropertyValueFactory<>(trans.get(i).getNumberOfConnectedSites()));
            //transitLoggedCol.setCellValueFactory(new PropertyValueFactory<>(trans.get(i).getNumberOfTransitLogged()));
        }

        ObservableList<String> site = SiteDAO.getSites();
        containSite.setItems(site);
        ObservableList<String> type = TransitDAO.getType();
        transportType.setItems(type);


        //might need to add health at th
        route.setMaxLength(50);
        minRange.setMaxLength(5);
        maxRange.setMaxLength(5);

        //will allow you to select a row without a radiobutton function
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                transitTable.requestFocus();
                transitTable.getSelectionModel().select(0);
                transitTable.scrollTo(0);
            }
        });
    }

    /**
     * filters the list
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void filter() throws SQLException, ClassNotFoundException {
        ObservableList<Transit> list = TransitDAO.filter(route.getText(), Double.parseDouble(minRange.getText()), Double.parseDouble(maxRange.getText()), containSite.getSelectionModel().getSelectedItem().toString(), transportType.getSelectionModel().getSelectedItem().toString(), true);
        transitTable.setItems(list);
    }

    /**
     * goes back depending on user type
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
     * deletes an existing row if selected
     */
    public void delete() {
        //make sure this exists first
        if (transitTable.getSelectionModel().getSelectedCells().get(0) != null) {
            //get selected row
            TablePosition pos = transitTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            Transit item = transitTable.getItems().get(row);
            TransitDAO.delete(item);
        }
    }

    /**
     * edit a row if selected
     * @throws Exception
     */
    public void edit() throws Exception {
        //make sure the table exists first
        if (transitTable.getSelectionModel().getSelectedCells().get(0) != null) {
            //get selected row
            TablePosition pos = transitTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            Transit item = transitTable.getItems().get(row);
            AdministratorEditTransitController.setTransit(item);

            Parent administratorEditSite = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorEditTransit.fxml"));
            Scene rootScene = new Scene(administratorEditSite, 405, 245);
            Main.pstage.setScene(rootScene);
        }
    }

    /**
     * allows user to create a new site
     * @throws Exception
     */
    public void create() throws Exception {
        Parent administratorCreateSite = FXMLLoader.load(getClass().getResource("/BeltLineApplication/resources/fxml/AdministratorCreateTransit.fxml"));
        Scene rootScene = new Scene(administratorCreateSite, 600, 450);
        Main.pstage.setScene(rootScene);
    }
}
