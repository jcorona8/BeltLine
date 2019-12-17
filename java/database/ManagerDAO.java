package BeltLineApplication.java.database;

import BeltLineApplication.java.model.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDAO {

    public static ObservableList<String> getManagerList() throws SQLException, ClassNotFoundException {
        ObservableList<String> list = FXCollections.observableArrayList();
        String query =
                "SELECT username from Manager m LEFT JOIN Site s ON m.username = s.managerUsername where s.managerUsername is NULL';";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            while (rs.next()) {
                String managerName = rs.getString("username");
                list.add(managerName);
            }
        } catch (SQLException e) {
            System.out.println("Something is wrong with your SQL: " + e);
            throw e;
        }
        return list;
    }

    public static void registerManager(String username) {
        String query =
                "INSERT INTO manager" +
                        "(Username)" +
                        "VALUES ('" + username + "');";
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with Register Employee Query" + e);
        }
    }

//    public static void dailyDetailPop() throws Exception {
//        String query = "Select Event.EName, Event.SName, Event.StartDate, Event.EndDate, count(distinct AssignTo.StaffUsername) as 'Staff Count'\n" +
//                "from Event \n" +
//                "join AssignTo on Event.EName = AssignTo.EName and Event.StartDate = AssignTo.StartDate and Event.SName = AssignTo.SiteName\n" +
//                "where Event.Description  like \"%keyword%\" and Event.Ename = 'ename' and Event.StartDate > 'before' and Event.EndDate < 'after'\n" +
//                "group by Event.Ename, Event.SName, Event.StartDate, Event.EndDate;\n";
//        while (rs.next()) {
//            //Iterate Row
//            ObservableList<String> row = FXCollections.observableArrayList();
//            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                //Iterate Column
//                row.add(rs.getString(i));
//            }
//            System.out.println("Row [1] added " + row);
//            data.add(row);
//
//        }
//        tableview.setItems(data);
//    } catch(Exception e){
//        System.out.println("error");
//    }
}
