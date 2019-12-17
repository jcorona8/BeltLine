package BeltLineApplication.java.database;

import BeltLineApplication.java.controller.UserLoginController;
import BeltLineApplication.java.model.Email;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailDAO {
    public static String getUsername(String email) {
        System.out.println("READ MEEEE!");
        String username ="";
        String query = "SELECT Username From Email where email = '" + email + "';";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);

            if (rs.next()) {
                username = rs.getString("Username");
            }
            return username;
        } catch (Exception e) {
            System.out.println("Error with get username query" + e);
        }
        return username;
    }

    /**
     * register email
     * @param username
     * @param email
     */
    public static void registerEmail(String username, String email) {
        String query =
                "INSERT INTO email" +
                        "(Username, Email)" +
                        "VALUES ('" + username + "','" + email + "');";
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with Register Email Query" + e);
        }
    }

    public static ObservableList<String> populateEmails() throws SQLException, ClassNotFoundException{
        String query = "SELECT email FROM Email" +
                "WHERE Username = '" + UserLoginController.getUsername() +"';";
        ResultSet rs = Connector.dbExecuteQuery(query);
        ObservableList<String> email = getEmails(rs);
        return email;
    }

    private static ObservableList<String> getEmails(ResultSet rs) throws SQLException {
        ObservableList<String> emails = FXCollections.observableArrayList();
        if (rs.next()) {
            String email = rs.getString("Email");
            emails.add(email);
        }
        return emails;
    }
}
