package BeltLineApplication.java.database;

import BeltLineApplication.java.controller.UserLoginController;
import BeltLineApplication.java.model.Event;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EventDAO {
    public static void createEvent(String eName, String startDate, String sName, String endDate, Double price, int capacity, String description,
                                   int minStaffReq) throws SQLException {
        String query =
                "INSERT INTO event" +
                        "VALUES ('" + eName + "','" + startDate + "','" + sName + "','" + endDate + "', '" +
                        price + "', '" + capacity + "', '" + description + "','" + minStaffReq + "');";

        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with create event query" + e);
        }
    }

    public static void updateEvent() {
        //update
    }

    public static void deleteEvent() {
        //delete
        String query =
                "DELETE FROM event" +
                        "WHERE";

        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with delete event query" + e);
        }
    }

    public static Boolean uniqueEvent(String eName, String site, String sDate, String eDate) throws ParseException {
        //check for ename and site
        String startDate = "01-02-2013";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf1.parse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        return true;

    }

    public static void visitEvent(String date) throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date jDate = sd.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(jDate.getTime());
        String query = "INSERT INTO VisitEvent ('Username', 'Ename', 'Sname', 'StartDate', 'VisitEventData')"
                + "VALUES (" + UserLoginController.getUsername() + ", " + ");";
    }

    public static ObservableList<String> getStaff() throws SQLException, ClassNotFoundException {
        String query = "SELECT FirstName, LastName" +
                "FROM User WHERE;"; //staff is not assigned to another event during this time
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with getting staff" + e);
        }
        ResultSet rs = Connector.dbExecuteQuery(query);
        while (rs.next()) {
            String fName = rs.getString("FirstName");
            String lName = rs.getString("LastName");
            list.add(fName + " " + lName);
        }
        return list;
    }

    public static ObservableList<String> populateStaffSchedule() throws SQLException, ClassNotFoundException {
        String query = "Select Event.EName, Event.SName, Event.StartDate, Event.EndDate, count(distinct AssignTo.StaffUsername) as 'Staff Count'\n" +
                "from Event \n" +
                "join AssignTo on Event.EName = AssignTo.EName and Event.StartDate = AssignTo.StartDate and Event.SName = AssignTo.SiteName\n" +
                "where Event.Description  like \"%keyword%\" and Event.Ename = 'ename' and Event.StartDate > 'before' and Event.EndDate < 'after'\n" +
                "group by Event.Ename, Event.SName, Event.StartDate, Event.EndDate;\n";
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with getting Schedule for staff" + e);
        }
        ResultSet rs = Connector.dbExecuteQuery(query);
        while (rs.next()) {
            String username = rs.getString("ManagerUsername");
            list.add(username);
        }
        return list;
    }

    public static ObservableList<String> staffSchedFilter(String eName, String dKey, String sDate, String eDate) throws ClassNotFoundException, SQLException {
        String w = null;
        String a = null;
        String b = null;
        String c = null;
        String e = null;
        String d = null;
        String f = null;
        String g = null;
        String h = null;
        String i = null;
        String j = null;
        String date = null;
        if (!eName.isEmpty() || !sDate.isEmpty() || !eDate.isEmpty() || !dKey.isEmpty()) {
            w = "Where";
        }
        if (!eName.isEmpty() && !sDate.isEmpty() && !eDate.isEmpty() && !dKey.isEmpty()) {
            a = " Event.Ename = '" + eName + "'" + " and Event.Description  like '%" + dKey + "%' and Event.StartDate >= '" + sDate + "' and Event.EndDate =< '" + eDate + "'";
        }
        if (!eName.isEmpty() && !sDate.isEmpty() && !eDate.isEmpty()) {
            b = " Event.Ename = '" + eName + "'" + " and Event.StartDate >= '" + sDate + "' and Event.EndDate =< '" + eDate + "'";
        }
        if (!sDate.isEmpty() && !eDate.isEmpty() && !dKey.isEmpty()) {
            c = " Event.Description  like '%" + dKey + "%' and Event.StartDate >= '" + sDate + "' and Event.EndDate =< '" + eDate + "'";
        }
        if (!eName.isEmpty() && !sDate.isEmpty() && !dKey.isEmpty()) {
            d = " Event.Ename = '" + eName + "'" + " and Event.Description  like '%" + dKey + "%' and Event.StartDate >= '" + sDate +  "'";
        }
        if (!eName.isEmpty() && !eDate.isEmpty() && !dKey.isEmpty()) {
            e = " Event.Ename = '" + eName + "'" + " and Event.Description  like '%" + dKey + "%' and Event.EndDate =< '" + eDate + "'";
        }
        if (!eName.isEmpty() && !dKey.isEmpty()) {
            f = " Event.Ename = '" + eName + "'" + " and Event.Description  like '%" + dKey + "'";
        }
        if (!eName.isEmpty() && !sDate.isEmpty()) {
            g = " Event.Ename = '" + eName + "'" + " and Event.StartDate >= '" + sDate +  "'";
        }
        if (!eName.isEmpty() && !eDate.isEmpty()) {
            h = " Event.Ename = '" + eName + "'" + " and Event.EndDate =< '" + eDate + "'";
        }
        if (!dKey.isEmpty() && !sDate.isEmpty()) {
            i = " Event.Description  like '%" + dKey + "%' and Event.StartDate >= '" + sDate +  "'";
        }
        if (!dKey.isEmpty() && !eDate.isEmpty()) {
            j = " Event.Description  like '%" + dKey + "%' and Event.EndDate =< '" + eDate + "'";
        }
        if (!sDate.isEmpty() && !eDate.isEmpty()) {
            date = " Event.StartDate >= '" + sDate + "' and Event.EndDate =< '" + eDate + "'";
        }
        if (!eName.isEmpty()) {
            eName = " Event.Ename = '" + eName + "'";
        }
        if (!dKey.isEmpty()) {
            dKey = " Event.Description  like '%" + dKey + "%'";
        }
        if (!sDate.isEmpty()) {
            sDate = " Event.StartDate >= '" + sDate + "'";
        }
        if (!eDate.isEmpty()) {
            eDate = " Event.EndDate =< '" + eDate + "'";
        }


        String query =
                "Select Event.EName, Event.SName, Event.StartDate, Event.EndDate, count(distinct AssignTo.StaffUsername) as 'Staff Count'\n" +
                        "from Event \n" +
                        "join AssignTo on Event.EName = AssignTo.EName and Event.StartDate = AssignTo.StartDate and Event.SName = AssignTo.SiteName\n" +
                        w + a + b + c + d + e + f + g + h + i + j + date + eName + dKey + sDate + eDate + ";";
        try {
            Connector.dbExecuteQuery(query);
        } catch (SQLException s) {
            System.out.println("Something is wrong with your SQL schedule: " + s);
        }
        ResultSet rs = Connector.dbExecuteQuery(query);
        ObservableList<String> list = FXCollections.observableArrayList();
        while (rs.next()) {
            eName = rs.getString(1);
            String sName = rs.getString(2);
            sDate = rs.getString(3);
            eDate = rs.getString(4);
            String sCount = rs.getString(5);
            list.add(eName + sName + sDate + eDate + sCount);
        }
        return list;
    }

    public static ObservableList<Event> exploreEvent() throws SQLException, ClassNotFoundException {
        ObservableList<Event> event = FXCollections.observableArrayList();

        String query = "select EventName as 'Event Name', SiteName as 'Site Name', TicketPrice as 'Ticket Price',\n" +
                "(Capacity - TotalVisits) as 'Ticket Remaining', TotalVisits as 'Total Visits', MyVisits as 'My Visits'\n" +
                "from (\n" +
                "select Event.EName as 'EventName', Event.SName as 'SiteName', Event.Price as 'TicketPrice', Event.Capacity as 'Capacity',\n" +
                "count(distinct VisitEvent.SName, VisitEvent.Username, VisitEvent.VisitEventDate) as 'TotalVisits',\n" +
                "count(distinct a.veSName, a.veEDate) as 'MyVisits'\n" +
                "from Event\n" +
                "join VisitEvent on Event.Ename = VisitEvent.EName and Event.SName = VisitEvent.SName\n" +
                "left join (select Username, VisitEvent.EName as 'veEName', VisitEvent.SName as 'veSName', VisitEvent.VisitEventDate as 'veEDate'\n" +
                "        from VisitEvent where Username = 'mary.smith') a on VisitEvent.SName = a.veSName and VisitEvent.EName = a.veEName\n" +
                "group by Event.EName, Event.SName, Price, Capacity\n" +
                "having totalvisits between 'low' and 'high', myvisits ‘<> or == 0’, (Capacity - TotalVisits) ‘<> or == 0’\n" +
                ") \n";
            //"where Event.Ename like '%event name%'  and Event.Description like \"%keyword%\" and Event.Sname = 'sname' and Event.StartDate > 'start' and Event.EndDate < 'End' \n" +
                //"and Event.Price between 'low' and 'high' \n"
        try {
            Connector.dbExecuteQuery(query);
        } catch (Exception e) {
            System.out.println("error with explore event table query");
        }
        ResultSet rs = Connector.dbExecuteQuery(query);
        while (rs.next()) {
            Event e = new Event();
            e.setEname(rs.getString(1));
            e.setSname(rs.getString(2));
            e.setPrice(rs.getDouble(3));
            e.setRemaining(rs.getInt(4));
            e.setTotalVisits(rs.getInt(5));
            e.setMyVisits(rs.getInt(6));
            event.add(e);

        }
        return event;

    }

    public static ObservableList<Event> filterEvents(String name, String dKey, String sDate, String eDate, double priceLow, double priceHigh, int visitsLow, int visitsHigh, boolean includeSoldOut, boolean includeVisited, String site) throws SQLException, ClassNotFoundException, ParseException{
        ObservableList<Event> event = FXCollections.observableArrayList();
        String query = "";
        if(!name.isEmpty() || !dKey.isEmpty() || !sDate.isEmpty()|| !eDate.isEmpty() ||(priceHigh != 0.0) || (visitsHigh != 0) || !includeSoldOut || !includeVisited || !site.isEmpty()) {
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date jDate = sd.parse(sDate);
                java.sql.Date sqlDate = new java.sql.Date(jDate.getTime());
                SimpleDateFormat ed = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date jeDate = ed.parse(eDate);
                java.sql.Date sqleDate = new java.sql.Date(jeDate.getTime());
                String date = "";
                String so = "";
                String iv = "";

                if (!name.isEmpty()) {
                    name = " and Event.EName like '%" + name + "%'";
                }
                if (!dKey.isEmpty()) {
                    dKey = " and Event.Description like '%" +dKey + "%'";
                }
                if (priceHigh == 0.0) {
                    priceHigh = 10000000.0;
                }
                if (visitsHigh == 0) {
                    visitsHigh = 100000000;
                }
                if (!site.isEmpty()) {
                   site = "and Event.Sname = '" + site + "'";

                }
                if (!sDate.isEmpty() && !eDate.isEmpty()) {
                    date = " and Event.StartDate >= '" + sqlDate + "' and Event.EndDate <= '" + sqleDate + "'";
                }
                if (!sDate.isEmpty()) {
                    date = " and Event.StartDate >= '" + sqlDate + "'";
                }
                if (!eDate.isEmpty()) {
                    date = "Event.EndDate <= '" + sqleDate + "'";
                }
                if (!includeSoldOut) {
                    so = ", (Capacity - TotalVisits) <> 0";
                }
                if (!includeVisited){
                    iv = ", myvisits ‘<> 0";
                }




            query = "select EventName as 'Event Name', SiteName as 'Site Name', TicketPrice as 'Ticket Price',\n" +
                    "(Capacity - TotalVisits) as 'Ticket Remaining', TotalVisits as 'Total Visits', MyVisits as 'My Visits'\n" +
                    "from (\n" +
                    "select Event.EName as 'EventName', Event.SName as 'SiteName', Event.Price as 'TicketPrice', Event.Capacity as 'Capacity',\n" +
                    "count(distinct VisitEvent.SName, VisitEvent.Username, VisitEvent.VisitEventDate) as 'TotalVisits',\n" +
                    "count(distinct a.veSName, a.veEDate) as 'MyVisits'\n" +
                    "from Event\n" +
                    "join VisitEvent on Event.Ename = VisitEvent.EName and Event.SName = VisitEvent.SName\n" +
                    "left join (select Username, VisitEvent.EName as 'veEName', VisitEvent.SName as 'veSName', VisitEvent.VisitEventDate as 'veEDate'\n" +
                    "        from VisitEvent where Username =" + UserLoginController.getUsername() + ") a on VisitEvent.SName = a.veSName and VisitEvent.EName = a.veEName\n" +
                    "where Event.Price between " + priceLow + " and " + priceHigh + name + dKey + site + date +
                    "group by Event.EName, Event.SName, Price, Capacity\n" +
                    "having totalvisits between " + visitsLow +" and " + visitsHigh + so + iv + ";";

        }
        try {
            Connector.dbExecuteQuery(query);
        } catch (Exception e) {
            System.out.println("error with filter explore event table query");
        }
        ResultSet rs = Connector.dbExecuteQuery(query);
        while (rs.next()) {
            Event e = new Event();
            e.setEname(rs.getString(1));
            e.setSname(rs.getString(2));
            e.setPrice(rs.getDouble(3));
            e.setRemaining(rs.getInt(4));
            e.setTotalVisits(rs.getInt(5));
            e.setMyVisits(rs.getInt(6));
            event.add(e);

        }
        return event;

    }
    public static void logEventVisit(String username, String ename, String sname, String sdate, String logDate) throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date jDate = sd.parse(sdate);
        java.sql.Date sqlDate = new java.sql.Date(jDate.getTime());

        SimpleDateFormat ld = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date jlDate = ld.parse(logDate);
        java.sql.Date sqllDate = new java.sql.Date(jlDate.getTime());

        String query = "Insert into VisitEvent ('Username', 'Ename', 'Sname', 'StartDate', 'VisitEventDate')" +
                "values ( " + UserLoginController.getUsername() + ", "  + ename + ", " + sname + ", " + sqlDate + ", " + sqllDate + ");";
        try{
            Connector.dbExecuteQuery(query);
        } catch (Exception e){
            System.out.println("error with log event visit query");
        }
    }

}
