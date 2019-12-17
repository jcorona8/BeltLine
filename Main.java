package BeltLineApplication;

import BeltLineApplication.java.database.Connector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    public static Stage pstage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        pstage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/UserLogin.fxml"));
        primaryStage.setTitle("BeltLine");
        Scene rootScene = new Scene(root, 280, 215);
        primaryStage.setScene(rootScene);
        primaryStage.show();
        primaryStage.setResizable(true);
        primaryStage.centerOnScreen();
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //inserting data
        //insertSQL();

        launch(args);
    }

    public static void initialize() {
        //TODO: run sql + insert
    }

    private static void insertSQL() throws SQLException {
        try {
            String sql="INSERT INTO Employee "+
                    "VALUES ('james.smith', 'jsmith123', 'Approved', 'James', 'Smith')";
            Connector.dbExecuteUpdate(sql);
        } catch (ClassNotFoundException e) {
            System.out.println("User already created: " + e);
        }
    }
}
