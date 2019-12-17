package BeltLineApplication.java.database;

import BeltLineApplication.java.controller.UserLoginController;
import BeltLineApplication.java.model.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Yaroslava
 */
public class EmployeeDAO extends UserDAO {
    /**
     * Register a Employee
     */
    public static void registerEmployee(String username, String phone, String address, String city, String state, int zipcode) throws SQLException, ClassNotFoundException {
        int random = (int)(Math.random() * 9000000 + 1);
        if(checkEmp(random)) {
            String query =
                    "INSERT INTO employee" +
                            "(Username, EmployeeID, Phone, Address, City, State, Zipcode)" +
                            "VALUES ('" + username + "','" + random + "','" + phone + "','" + address + "', '" +
                            city + "', '" + state + "', '" + zipcode + "');";
            try {
                Connector.dbExecuteUpdate(query);
            } catch (Exception e) {
                System.out.println("Error with Register Employee Query" + e);
            }
        }
        else {
            registerEmployee(username, phone, address, city, state, zipcode);
        }

    }

    /**
     * populate most data
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Employee populate() throws SQLException, ClassNotFoundException {
        if (UserLoginController.getUserType().equals("Manager")) {
            String query = "SELECT * FROM Employee join User on Employee.Username = User.Username" +
                    "JOIN Site on Employee.Username = Staff.ManagerUsername" +
                    "WHERE Username = '" + UserLoginController.getUsername() +"';";
            ResultSet rs = Connector.dbExecuteQuery(query);
            Employee emp = getEmployeeManager(rs);
            return emp;
        } else {
            String query = "SELECT * FROM Employee join User on Employee.Username = User.Username " +
                    "WHERE Employee.Username = '" + UserLoginController.getUsername() +"';";
            ResultSet rs = Connector.dbExecuteQuery(query);
            Employee emp = getEmployee(rs);
            emp.setUsername(rs.getString(1));
            emp.setEmployeeID(rs.getInt(2));
            emp.setPhone(rs.getString(3));
            emp.setAddress(rs.getString(4));
            emp.setCity(rs.getString(5));
            emp.setState(rs.getString(6));
            emp.setZipcode(rs.getInt(7));
            emp.setFname(rs.getString("FirstName"));
            emp.setLname(rs.getString("LastName"));
            return emp;
        }
    }

    private static Employee getEmployee(ResultSet rs) throws SQLException {
        Employee emp = null;
        if (rs.next()) {
            emp = new Employee();
            emp.setFname(rs.getString("Fname"));
            emp.setLname(rs.getString("Lname"));
            emp.setUsername(rs.getString("Username"));
            emp.setSname("");
            emp.setEmployeeID(rs.getInt("EmployeeID"));
            emp.setPhone(rs.getString("Phone"));
            emp.setAddress(rs.getString("Address"));
        }
        return emp;
    }

    private static Employee getEmployeeManager(ResultSet rs) throws SQLException {
        Employee emp = null;
        if (rs.next()) {
            emp = new Employee();
            emp.setFname(rs.getString("Fname"));
            emp.setLname(rs.getString("Lname"));
            emp.setUsername(rs.getString("Username"));
            emp.setSname(rs.getString("Site Name"));
            emp.setEmployeeID(rs.getInt("EmployeeID"));
            emp.setPhone(rs.getString("Phone"));
            emp.setAddress(rs.getString("Address"));
        }
        return emp;
    }

    private static Boolean checkEmp(int random) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM Employee WHERE EmployeeID = \'" + random + "\';";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            return (rs != null);
        } catch (SQLException e) {
            System.out.println("Something is wrong with your SQL: " + e);
            throw e;
        }
    }

    public static void update(String fname, String lname, String username, int phone) {
        //update user first
        String query = "UPDATE User Set FirstName = '" + fname  + "' and LastName = '" +
                lname + "' WHERE Username = '" + username + "';";
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Issue with update query for User" + e);
        }

        //update employee
        query = "UPDATE Employee Set Phone = '" + phone  + "' WHERE Username = '" + username + "';";
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Issue with update query for Employee" + e);
        }
    }
}
