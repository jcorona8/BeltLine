package BeltLineApplication.java.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Yaroslava
 */
public class VisitorDAO extends UserDAO {
    /**
     * register a visitor
     * @param username
     * @throws SQLException
     */
    public static void registerVisitor(String username)throws SQLException {
        String query =
                "INSERT INTO visitor" +
                        "(Username)" +
                        "VALUES ('" + username + "');";
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with Register Visitor Query" + e);
        }

    }

    /**
     * if it is a visitor
     * @param username
     * @return boolean
     * @throws SQLException
     */
    public static boolean isVisitor(String username) throws SQLException {
        String query = "Select exists (select username from Transit where username = "
                + "'" + username + "') as 'Exists?';";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            while (rs.next()) {
                String num = rs.getString(0);
                if (Integer.parseInt(num) == 1) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error with getting transport type on Check if Visitor" + e);
        }
        return false;
    }

    /**
     * deletes the visitor
     * @param username
     */
    public static void delete(String username) {
        String query = "DELETE from Visitor WHERE Username = '" + username + "';";
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Issue with update query for User" + e);
        }
    }
}
