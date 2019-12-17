package BeltLineApplication.java.database;

import BeltLineApplication.java.model.Administrator;
import BeltLineApplication.java.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    /**
     * Register a User
     */
    public static void registerUser(String username, String password, String fname, String lname) throws SQLException {
        //password = hash(password);
        String query =
                "INSERT INTO user" +
                        "(Username, Password, Status, FirstName, LastName)" +
                        "VALUES ('" + username + "','" + password + "','" + "Pending" + "','" + fname + "', '" +
                        lname + "');";
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with Register User Query" + e);
        }
    }

//    public static String hash(String password) {
//        String pass = password;
//        String query = "DECLARE @pswd NVARCHAR(50) = '" + password + "'; \n" +
//                "DECLARE @salt VARBINARY(4) = CRYPT_GEN_RANDOM(4);\n" +
//                "DECLARE @hash VARBINARY(50); \n" +
//                "SET @hash = 0x0200 + @salt + HASHBYTES('SHA2_512', CAST(@pswd AS VARBINARY(MAX)) + @salt); \n" +
//                "SELECT @hash AS Password;";
//        try {
//            ResultSet rs = Connector.dbExecuteQuery(query);
//            while (rs.next()) {
//                pass = rs.getString(0);
//            }
//        } catch (Exception e) {
//            System.out.println("Error with hash User Query" + e);
//        }
//        return pass;
//    }

    /**
     * UserLoginController a User
     */
    public static boolean loginUser(String email, String password) throws SQLException, ClassNotFoundException {
        String query =
                "select * from user join email on user.username = email.username where email = '"
                        + email + "' and password = '" + password + "';";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            return (rs != null);
        } catch (SQLException e) {
            System.out.println("Something is wrong with your SQL: " + e);
            throw e;
        }

    }

    /**
     * approves user
     *
     * @param username
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static boolean isApproved(String username) throws SQLException, ClassNotFoundException {
        String query = "Select Status From User Where Username = '" + username + "';";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
           if (rs != null) {
               rs.next();
               String status = rs.getString("Status");
               return (status.equalsIgnoreCase("Approved"));
           }
        } catch (Exception e) {
            System.out.println("Something is wrong with your SQL: " + e);
            throw e;
        }
        return false;
    }

    /**
     * @param rs
     * @return
     * @throws SQLException
     */
    private static ObservableList<User> getUsers(ResultSet rs) throws SQLException {
        ObservableList<User> list = FXCollections.observableArrayList();
        if (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString("Username"));
            user.setEmailCount(rs.getInt("Email Count"));
            user.setUserType(rs.getString("User Type"));
            user.setStatus(rs.getString("Status"));
            list.add(user);
        }
        return list;
    }

    public static String isUser(String username) throws SQLException, ClassNotFoundException {
        String mang = "Manager";
        String mangVisitor = "ManagerVisitor";
        String admin = "Administrator";
        String adminVisitor = "AdministratorVisitor";
        String staff = "Staff";
        String staffVisitor = "StaffVisitor";
        String visitor = "Visitor";
        String user = "User";

        String query;
        String where = " WHERE username = '" + username + "';\n";

        //if manager
        try {
            query = "SELECT username from " + mang + where;
            ResultSet rs = Connector.dbExecuteQuery(query);
            if (rs.next()) {
                try {
                    query = "SELECT username from " + visitor + where;
                    ResultSet second = Connector.dbExecuteQuery(query);
                    if (second.next()) {
                        return mangVisitor;
                    }
                    return mang;
                } catch (Exception e) {
                    System.out.println("Error with getting admin visitor type " + e);
                }
            }
        } catch (Exception e) {
            System.out.println("Error with getting manager type " + e);
        }

        //if admin
        try {
            query = "SELECT username from " + admin + where;
            ResultSet rs = Connector.dbExecuteQuery(query);
            if (rs.next()) {
                try {
                    query = "SELECT username from " + visitor + where;
                    ResultSet second = Connector.dbExecuteQuery(query);
                    if (second.next()) {
                        return adminVisitor;
                    }
                    return admin;
                } catch (Exception e) {
                    System.out.println("Error with getting admin visitor type " + e);
                }
            }
        } catch (Exception e) {
            System.out.println("Error with getting staff type " + e);
        }

        //if staff
        try {
            query = "SELECT username from " + staff + where;
            ResultSet rs = Connector.dbExecuteQuery(query);
            if (rs.next()) {
                try {
                    query = "SELECT username from " + visitor + where;
                    ResultSet second = Connector.dbExecuteQuery(query);
                    if (second.next()) {
                        return staffVisitor;
                    }
                    return staff;
                } catch (Exception e) {
                    System.out.println("Error with getting staff visitor type " + e);
                }
            }
        } catch (Exception e) {
            System.out.println("Error with getting staff type " + e);
        }

        //if visitor
        try {
            query = "SELECT username from " + visitor + where;
            ResultSet rs = Connector.dbExecuteQuery(query);
            if (rs.next()) {
                return visitor;
            }
        } catch (Exception e) {
            System.out.println("Error with getting visitor type " + e);
        }

        return user;
    }

    public static ObservableList<User> filter(String username, String type, String status) throws SQLException, ClassNotFoundException {
        String query = "Select User.Username, Count(Email) as 'Email Count', UserType as 'Type', Status \n" +
                "from User join Email on User.Username = Email.Username\n" +
                "where  Username = \"" + username + "\" and UserType = \"" + type +
                "\" and Status = \"" + status + "\"\n" +
                "group by Email.username;\n";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            ObservableList<User> user = getUsers(rs);
            return user;
        } catch (Exception e) {
            System.out.println("Error with update transit query" + e);
        }
        return null;
    }

    public static ObservableList<User> populateUser() throws SQLException, ClassNotFoundException {
        String query = "Select User.Username, Count(Email) as 'Email Count', UserType as 'Type', Status \n" +
                "from User join Email on User.Username = Email.Username\n" +
                "group by Email.username;\n";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            ObservableList<User> user = getUsers(rs);
            return user;
        } catch (Exception e) {
            System.out.println("Error with update transit query" + e);
        }
        return null;
    }

    public static void approve(String username) {
        String query = "UPDATE User SET Status = \"Approved\" Where Username = '" + username + "';";
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with user approve Query" + e);
        }
    }

    public static void decline(String username) {
        String query = "UPDATE User SET Status = \"Declined\" Where Username = '" + username + "';";
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with user decline Query" + e);
        }
    }
}
