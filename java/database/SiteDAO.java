
package BeltLineApplication.java.database;

import BeltLineApplication.java.controller.UserLoginController;
import BeltLineApplication.java.model.Site;
import BeltLineApplication.java.model.Transit;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Site DAO is the class that is in between the connector and the controller
 * @author Yaroslava
 * @author Julia
 */
public class SiteDAO {
    /**
     * creates a site
     * @param sname
     * @param address
     * @param zipcode
     * @param openEveryday
     * @param managerUsername
     * @throws SQLException
     */
    public static void createSite(String sname, String address, int zipcode, Boolean openEveryday, String managerUsername) throws SQLException {
        String query =
                "INSERT INTO site" +
                        "(SName, Address, Zipcode, OpenEverday, managerUsername)" +
                        "VALUES ('" + sname + "','" + address + "','" + zipcode + "','" + openEveryday + "', '" +
                        managerUsername + "');";
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with create site query" + e);
        }
    }

    /**
     *
     * @param sname
     * @param address
     * @param zipcode
     * @param openEverday
     * @param managerUsername
     */
    public static void updateSite(String sname, String address, int zipcode, Boolean openEverday, String managerUsername) {
        String open;
        if (openEverday) {
            open = "Y";
        } else {
            open = "N";
        }
        String query = "Update Site Set SiteName = '" + sname + "', SiteZipcode = " + zipcode + "', SiteAddress = '" + address + "', OpenEveryday = '" + open + "', ManagerUsername = '" + managerUsername + "';";

        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with update site query" + e);
        }
    }

    public static String getManagerUsername(String manager) {
        String query = "Select managerUsername from Site JOIN Join on User.username = Site.managerUsername WHERE " + manager + " = concat(User.FirstName, \'\', User.LastName)";
        String managerUsername = "";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            while(rs.next()) {
                managerUsername = rs.getString("ManagerUsername");
            }
        } catch (Exception e) {
            System.out.println("Error with create site query" + e);
        }
        return managerUsername;
    }

    /**
     *
     * @param username
     * @return
     */
    public static boolean checkSiteExist(String username) {
        String query = "Select exists (select ManagerUsername from Site where ManagerUsername = "
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
            System.out.println("Error getting if site exists on Manager Create Event" + e);
        }
        return false;
    }

    /**
     *
     * @param manager
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static String getSite(String manager) throws SQLException , ClassNotFoundException {
        String query = "Select SName from Site where ManagerUsername = "
                + "'" + manager + "';";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            String siteName = rs.getString("SName");
            return siteName;

        } catch (Exception e) {
            System.out.println("Error getting site name for Manager Create Event");
        }
        return null;
    }

    /**
     *
     * @param date
     * @throws ParseException
     */
    public static void visitSite(String date) throws ParseException {
        //actually get sname
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date jDate = sd.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(jDate.getTime());
        String query = "INSERT INTO VisitSite"
                + "VALUES (" + UserLoginController.getUsername() + ", " + "sname, " + sqlDate + ");"; //username sname and date
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
        } catch (Exception e) {
            System.out.println("Error with SQL");
        }
    }

    /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ObservableList<String> getManagerList() throws SQLException, ClassNotFoundException {
        String query = "select concat(FirstName, '" + "' LastName) from User JOIN Site ON Site.ManagerUsername = User.Username;";
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with getting managerUsername from ManagerList get List" + e);
        }
        ResultSet rs = Connector.dbExecuteQuery(query);
        while (rs.next()) {
            String username = rs.getString("ManagerUsername");
            list.add(username);
        }
        return list;
    }

    /**
     *
     * @param site
     * @param manager
     * @param openEverday
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ObservableList<Site> filter(String site, String manager, String openEverday) throws SQLException, ClassNotFoundException{
        //create where statements for each variable
        if (!site.isEmpty()) {
            site = " site = '" + site + "'";
        }
        if (!manager.isEmpty()) {
            manager = " manager = '" + manager + "'";
        }
        if (!openEverday.isEmpty()) {
            openEverday = " openEverday = '" + openEverday + "'";
        }

        String query =
                "select site, manager, openEverday from site where " + site + manager + openEverday + ";";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
        } catch (SQLException e) {
            System.out.println("Something is wrong with your SQL: " + e);
            throw e;
        }
        ResultSet rs = Connector.dbExecuteQuery(query);
        ObservableList<Site> row = getSiteList(rs);

        return row;
    }

    /**
     * returns the list of transit based on the result set
     * @param rs
     * @return
     * @throws SQLException
     */
    private static ObservableList<Site> getSiteList(ResultSet rs) throws SQLException {
        ObservableList<Site> list = FXCollections.observableArrayList();
        if (rs.next()) {
            Site s = new Site();
            s.setSname(rs.getString("SName"));
            s.setManagerUsername(rs.getString("ManagerUsername"));
            s.setOpenEveryday(rs.getInt("OpenEveryday"));
            list.add(s);
        }
        return list;
    }

    /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ObservableList<Site> populateSite() throws SQLException, ClassNotFoundException {
        String query = "Select Site.SName, concat(User.FirstName,\" \", User.LastName) as 'Manager', Site.OpenEveryday\n" +
                "from Site join User on Site.ManagerUsername = User.Username\n" +
                "where Site.OpenEveryday = 'OpenEveryday' and Site.SName = 'sname' and concat(User.FirstName,\" \", User.LastName) = 'manager name' ;\n";
        try {
            ResultSet rs = Connector.dbExecuteQuery(query);
            ObservableList<Site> s = getSiteList(rs);
            return s;
        } catch (Exception e) {
            System.out.println("Error with update site query" + e);
        }
        return null;
    }

    public static void delete(Site site) {
        String delete = "Delete from site WHERE SName = '" + site.getSname()
                + "' and ManagerUsername = '" + site.getManagerUsername()
                + "' and OpenEverday = '" + site.getOpenEveryday() + "';";
        try {
            Connector.dbExecuteUpdate(delete);
        } catch (Exception e) {
            System.out.println("Error with delete transit query" + e);
        }
    }


    public static ObservableList<String> getSites() throws SQLException, ClassNotFoundException {
        String query = "select SName from site;";
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            Connector.dbExecuteUpdate(query);
        } catch (Exception e) {
            System.out.println("Error with site names" + e);
        }
        ResultSet rs = Connector.dbExecuteQuery(query);
        while (rs.next()) {
            String sites = rs.getString(1);
            list.add(sites);
        }
        return list;
    }

    public static void logSiteVisit(String username, String sname, String logDate) throws ParseException {
        SimpleDateFormat ld = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date jlDate = ld.parse(logDate);
        java.sql.Date sqllDate = new java.sql.Date(jlDate.getTime());

        String query = "Insert into VisitSite ('Username', 'Sname', 'VisitEventDate')" +
                "values ( " + username + ", " + sname + ", " + sqllDate + ");";
        try{
            Connector.dbExecuteQuery(query);
        } catch (Exception e){
            System.out.println("error with log site visit query");
        }
    }

}