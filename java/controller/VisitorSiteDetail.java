package BeltLineApplication.java.controller;

import BeltLineApplication.Main;
import BeltLineApplication.java.database.SiteDAO;
import BeltLineApplication.java.model.Site;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.ParseException;

public class VisitorSiteDetail {
    @FXML
    Button back;
    @FXML
    Button logVisit;
    @FXML
    Label st;
    @FXML
    Label open;
    @FXML
    Label addy;
    @FXML
    TextField visitDate;


    public void initialize(Site s){
        st.setText(s.getSname());
        open.setText(s.getOpenEveryday());
        addy.setText(s.getAddress());

    }

    public void back() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/fxml/VisitorExploreSite.fxml"));
        Scene rootScene = new Scene(root, 725, 650);
        Main.pstage.setScene(rootScene);
    }

    public void logVisit() throws ParseException {
            SiteDAO.logSiteVisit(UserLoginController.getUsername(), st.getText(), visitDate.getText());
    }

}
