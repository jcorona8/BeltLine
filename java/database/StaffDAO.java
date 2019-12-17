package BeltLineApplication.java.database;

import BeltLineApplication.java.model.Staff;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffDAO {
    public static void registerStaff(String username) {
        String query =
                "INSERT INTO staff" +
                        "(Username)" +
                        "VALUES ('" + username + "');";
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with Register Employee Query" + e);
        }
    }

    public static ObservableList<Staff> manageStaff() throws SQLException, ClassNotFoundException {
        String query = "select concat(user.firstname + \" \" + user.lastname) as 'Staff Name', COUNT(assignto.startdate) as '# Event Shifts'\n" +
                "from user\n" +
                "join assignto on user.username = assignto.staffusername;";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            ObservableList<Staff> staff = FXCollections.observableArrayList();
            ;
            while (rs.next()) {
                Staff s = new Staff();
                s.setName(rs.getString(1));
                s.setShifts(rs.getInt(2));
                staff.add(s);
            }

        } catch (Exception e) {
            System.out.println("Error with getting staff" + e);
        }
        return null;

    }

    public static ObservableList<Staff> filterStaff(String fName, String lName, String sDate, String eDate, String site) throws ClassNotFoundException, SQLException{
        ObservableList<Staff> staff = FXCollections.observableArrayList();
        String query = "";
        if (!fName.isEmpty() || !lName.isEmpty() || !sDate.isEmpty() || !eDate.isEmpty() || !site.isEmpty()) {
            int count = 0;
            if (!fName.isEmpty()) {
                fName = " user.firstname= " + fName;
                count++;
            }
            if (!lName.isEmpty()) {
                if (count == 1) {
                    lName = " and user.lastname = " + lName;
                    count++;
                } else {
                    lName = " user.lastname = " + lName;
                    count++;
                }
            }
            if (!sDate.isEmpty()) {
                if (count >= 1) {
                    sDate = " and assignto.startdate >= " + sDate;
                    count++;
                } else {
                    sDate = " assignto.startdate >=" + sDate;
                    count++;
                }

            }
            if (!eDate.isEmpty()) {
                if (count >= 1) {
                    eDate = " and assignto.enddate <= " + eDate;
                    count++;
                } else {
                    eDate = " assignto.enddate <= " + eDate;
                    count++;
                }

            }
            if (!site.isEmpty()) {
                if (count >= 1) {
                    site = " and assignto.sitename = " + site;
                } else {
                    site = " assignto.sitename = " + site;
                }
            }
            query = "select concat(user.firstname, ' ', user.lastname) as 'Staff Name', COUNT(assignto.startdate) as '# Event Shifts' " +
                    "from user join assignto on user.username = assignto.staffusername " +
                    "where" + fName + lName + sDate + eDate + site + ";";
        }

        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            while (rs.next()) {
                Staff s = new Staff();
                s.setName(rs.getString(1));
                s.setShifts(rs.getInt(2));
                staff.add(s);
            }
        } catch (Exception e){
                System.out.println("Error with filtering manage staff" + e);
        }
        return staff;
    }
}
