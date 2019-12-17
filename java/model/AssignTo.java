package BeltLineApplication.java.model;

public class AssignTo {
    private String staffUsername;
    private String eName;
    private String startDate;
    private String siteName;

    public String getStaffUsername(){
        return staffUsername;
    }

    public String getEName(){
        return eName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setStaffUsername(String staffUsername) {
        this.staffUsername = staffUsername;
    }

    public void setEName(String eName) {
        this.eName = eName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
