package BeltLineApplication.java.database;

import java.sql.*;
import javax.sql.rowset.*;

//The Connector class allows connection to the MYSQL database.
// We can write simple queries here that will allow users to insert
// data to the database.
public class Connector {
    private static final String DBConnector = "com.mysql.cj.jdbc.Driver";
    private static Connection con = null;

    public static void connect() throws SQLException, ClassNotFoundException {
        //create connection
        try {
            Class.forName(DBConnector);
        } catch (ClassNotFoundException e) {
            System.out.println("Your database connection is not working.");
            e.printStackTrace();
            throw e;
        }

        try {
            //this is your Connector to the database. Make sure you change the user/password and even maybe the connection url
            //if you have a different one.
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/beltline?useSSL=false", "root", "Robin12#");
        } catch (SQLException e) {
            System.out.println("Connection Failed." + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void disconnect() throws SQLException {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Failed to disconnect" + e);
            throw e;
        }
    }

    /**
     * This dbExecuteQuery only makes sure the query does not have an error
     *
     * @param query a String
     * @return ResultSet from the query
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public static ResultSet dbExecuteQuery(String query) throws SQLException, ClassNotFoundException {
        Statement statement = null;
        ResultSet rs = null;
        CachedRowSet crs = null;

        try {
            connect();
            System.out.println("Select Statement: " + query);
            statement = con.createStatement();
            rs = statement.executeQuery(query);

            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
        } catch (Exception e) {
            System.out.println("Your error: " + e);
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            disconnect();
        }
        return crs;
    }

    public static void dbExecuteUpdate(String query) throws SQLException, ClassNotFoundException {
        Statement statement = null;

        try {
            connect();
            statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Your error: " + e);
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            disconnect();
        }
    }
}
