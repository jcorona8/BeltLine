package BeltLineApplication.java.database;

import BeltLineApplication.java.controller.UserLoginController;
import BeltLineApplication.java.model.Take;
import BeltLineApplication.java.model.Transit;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Yaroslava
 */
public class TransitDAO {
    /**
     * @param route
     * @param minRange
     * @param maxRange
     * @param containSite
     * @param transportType
     * @return ObservableList<Object>
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ObservableList<Transit> filter(String route, Double minRange, Double maxRange, String containSite, String transportType, Boolean ifAdmin) throws SQLException, ClassNotFoundException {
        //get minRange and maxRange values
        if (maxRange == 0.0) {
            maxRange = 999.99;
        }

        String price = "price BETWEEN " + minRange + " AND " + maxRange;

        //create where statements for each variable
        if (!route.isEmpty()) {
            route = " route = '" + route + "'";
        }
        if (!containSite.isEmpty()) {
            containSite = " site = '" + containSite + "'";
        }
        if (!transportType.isEmpty()) {
            transportType = " type = '" + transportType + "'";
        }

        String query =
                "select * from transit where " + route + containSite + transportType + ";";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
        } catch (SQLException e) {
            System.out.println("Something is wrong with your SQL: " + e);
            throw e;
        }
        ResultSet rs = Connector.dbExecuteQuery(query);
        ObservableList<Transit> row = getTransitList(rs, ifAdmin);

        return row;
    }

    /**
     * overloaded
     * @param site
     * @param type
     * @param minRange
     * @param maxRange
     * @return a list of sites
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ObservableList<Transit> filter(String site, String type, double minRange, double maxRange, Boolean ifAdmin) throws SQLException, ClassNotFoundException {
        if (maxRange == 0.0) {
            maxRange = 999.99;
        }

        String query = "Select Transit.Route, Transit.Type, Price, c.NumConnectedSites as 'Number of Connected Sites'\n" +
                "from Transit \n" +
                "join Connect on Transit.Type = Connect.Type\n" +
                "join (select transit.route, transit.type, count(*) as 'NumConnectedSites' from Transit join Connect\n" +
                "on Transit.Type = Connect.Type\n" +
                "group by transit.route, transit.type) as c on c.route = transit.route\n" +
                "where (" + site + " = '' or SName = '" + site + "') and \n" +
                "(Transit.Price > " + minRange + " or " + minRange + " = '' ) and \n" +
                "(Transit.Price < " + maxRange + " or \" + maxRange + \" = '')  and \n" +
                "(Connect.Type =  '" + type + "'  or\" + type + \"= '');\n";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
        } catch (SQLException e) {
            System.out.println("Something is wrong with your filter sql for user: " + e);
            throw e;
        }
        ResultSet rs = Connector.dbExecuteQuery(query);
        ObservableList<Transit> row = getTransitList(rs, ifAdmin);

        return row;
    }

    /**
     * create a transit
     *
     * @param type
     * @param route
     * @param price
     * @throws SQLException
     */
    public static void createTransit(String type, String route, double price) throws SQLException {
        String query =
                "INSERT INTO transit" +
                        "(Type, Route, Price)" +
                        "VALUES ('" + type + "','" + route + "','" + price + "');";
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with create site query" + e);
        }
    }

    /**
     * update connect
     *
     * @param sname
     * @param route
     * @param type
     */
    public static void connect(String sname, String route, String type) {
        String query =
                "update connect set route = " + "'" + route + "', type = "
                        + "'" + type + "' where sname = " + "'" + sname + "';";
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with connect query" + e);
        }
    }

    /**
     * Get connected sites from site
     *
     * @return connected sites list
     */
    public static ObservableList<String> getConnectedSites() throws SQLException, ClassNotFoundException {
        String query = "select SName from site;";
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with getting connected sites from Administrator Create Transit" + e);
        }
        ResultSet rs = Connector.dbExecuteQuery(query);
        while (rs.next()) {
            String site = rs.getString("SName");
            list.add(site);
        }
        return list;
    }

    /**
     * get type from transit
     *
     * @return ObservableList<String>
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ObservableList<String> getType() throws SQLException, ClassNotFoundException {
        String query = "select Type from Transit;";
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with getting transport type on Administrator Create Transit" + e);
        }
        ResultSet rs = Connector.dbExecuteQuery(query);
        while (rs.next()) {
            String site = rs.getString("type");
            list.add(site);
        }
        return list;
    }

    /**
     * check if route and type match another other route and type as a primary key
     *
     * @param route
     * @param type
     * @return boolean
     */
    public static boolean checkRouteType(String route, String type) {
        String query = "Select exists (select route, type from Transit where route = "
                + "'" + route + "' type = '" + type + "') as 'Exists?';";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            while (rs.next()) {
                String num = rs.getString(0);
                if (Integer.parseInt(num) == 1) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error with getting transport type on Administrator Create Transit" + e);
        }
        return false;
    }

    /**
     * updates transit
     *
     * @param route
     * @param price
     */
    public static void updateTransit(String type, String route, double price) {
        String updateTransit =
                "update transit\n" +
                        "set Price = '" + price + "', Route = '" + route + "';";
        try {
            Connector.dbExecuteUpdate(updateTransit);
        } catch (Exception e) {
            System.out.println("Error with update transit query" + e);
        }
    }

    /**
     * deletes a transit object from the database
     *
     * @param transit
     */
    public static void delete(Transit transit) {
        String delete = "Delete from transit WHERE Type = '" + transit.getType()
                + "' and Route = '" + transit.getRoute()
                + "' and Price = '" + transit.getPrice() + "';";
        try {
            Connector.dbExecuteUpdate(delete);
        } catch (Exception e) {
            System.out.println("Error with delete transit query" + e);
        }
    }

    /**
     * returns a list of transit objects
     * @param ifAdmin
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ObservableList<Transit> populateTransit(Boolean ifAdmin) throws SQLException, ClassNotFoundException {
        String query = "Select transit.type, transit.route, price, s.NumSite as '# of Connected Site', l.Numlog as '# of Transit Logged'\n" +
                "from transit \n" +
                "join (select Transit.type, Transit.route, Count(*) as 'NumSite' \n" +
                "from transit join connect on transit.route=connect.route and transit.type = connect.type\n" +
                "group by Transit.type, Transit.route) s on s.type = transit.type and s.route = transit.route join (select Take.type, Take.route, count(*) as 'Numlog' \n" +
                "from take group by take.type, take.route) l on l.type=Transit.type and s.route = transit.route;\n";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            ObservableList<Transit> transit = getTransitList(rs, ifAdmin);
            return transit;
        } catch (Exception e) {
            System.out.println("Error with update transit query" + e);
        }
        return null;
    }

    /**
     * list of take objects
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ObservableList<Take> populateTransitList() throws SQLException, ClassNotFoundException {
        String query = "select Take.Date, Take.Route, Take.Type, Transit.Price \n" +
                "from Take\n" +
                "join Transit on Take.Route = Transit.Route and Take.Type = Transit.Type\n" +
                "join Connect on Take.Route = Connect.Route and Take.Type = Connect.Type;\n";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            ObservableList<Take> transit = getTransitTake(rs);
            return transit;
        } catch (Exception e) {
            System.out.println("Error with update transit query" + e);
        }
        return null;
    }

    /**
     * filter take
     * @param startDate
     * @param endDate
     * @param route
     * @param type
     * @param site
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws ParseException
     */
    public static ObservableList<Take> filterTake(String startDate, String endDate, String route, String type, String site) throws SQLException, ClassNotFoundException, ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

        //parse dates
        java.util.Date jDate = sd.parse(startDate);
        java.sql.Date sDate = new java.sql.Date(jDate.getTime());

        //parse end dates
        java.util.Date jDateF = sd.parse(endDate);
        java.sql.Date eDate = new java.sql.Date(jDateF.getTime());
        String query = "select Take.Date, Take.Route,  Take.Type, Transit.Price \n" +
                "from Take\n" +
                "join Transit on Take.Route = Transit.Route and Take.Type = Transit.Type\n" +
                "join Connect on Take.Route = Connect.Route and Take.Type = Connect.Type\n" +
                "where Take.Date between '" + sDate + "' and '" + eDate + "' and Take.Type = '" + type +"' \n" +
                "and Take.Route = '" + route + "' and Connect.SName = '" + site + "' and Take.Username = '" + UserLoginController.getUsername() + "';\n";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            ObservableList<Take> transit = getTransitTake(rs);
            return transit;
        } catch (Exception e) {
            System.out.println("Error with update transit query" + e);
        }
        return null;
    }

    private Transit getTransit(ResultSet rs) throws SQLException {
        Transit transit = null;
        if (rs.next()) {
            transit = new Transit();
            transit.setType(rs.getString("Type"));
            transit.setRoute(rs.getString("Route"));
            transit.setPrice(rs.getDouble("Price"));
            transit.setNumberOfConnectedSites(rs.getInt("# of Connected Sites"));
            transit.setNumberOfTransitLogged(rs.getInt("# of Transit Logged"));
        }
        return transit;
    }

    /**
     * returns the list of transit based on the result set
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private static ObservableList<Transit> getTransitList(ResultSet rs, Boolean ifAdmin) throws SQLException {
        ObservableList<Transit> trans = FXCollections.observableArrayList();
        if (rs.next()) {
            Transit transit = new Transit();
            transit.setType(rs.getString("Type"));
            transit.setRoute(rs.getString("Route"));
            transit.setPrice(rs.getDouble("Price"));
            transit.setNumberOfConnectedSites(rs.getInt("# of Connected Sites"));
            if (ifAdmin) {
                transit.setNumberOfTransitLogged(rs.getInt("# of Transit Logged"));
            }
            trans.add(transit);
        }
        return trans;
    }

    private static ObservableList<Take> getTransitTake(ResultSet rs) throws SQLException {
        ObservableList<Take> trans = FXCollections.observableArrayList();
        if (rs.next()) {
            Take transit = new Take();
            transit.setDate(rs.getString("Date"));
            transit.setRoute(rs.getString("Route"));
            transit.setType(rs.getString("Type"));
            transit.setPrice(rs.getDouble("Price"));
            trans.add(transit);
        }
        return trans;
    }

    /**
     * log transit
     * @param route
     * @param type
     * @param date
     * @throws ParseException
     */
    public static void logTransit(String route, String type, String date) throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date jDate = sd.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(jDate.getTime());

        String query = "INSERT INTO TAKE"
                + "(Username, Route, Type, Date)"
                + "VALUES (" + UserLoginController.getUsername() + ", " + route + ", " + type + ", " + sqlDate + ");";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
        } catch (Exception e) {
            System.out.println("Error with logging transit");
        }
    }
}
