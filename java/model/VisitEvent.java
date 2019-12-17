package BeltLineApplication.java.model;

public class VisitEvent {
    private String username;
    private String sName;
    private String visitEventDate;
    private String eName;
    private String startDate;

    public String getUsername() { return username; }

    public String getsName() { return sName; }

    public String getVisitEventDate() { return visitEventDate; }

    public void setUsername(String username) { this.username = username; }

    public void setsName(String sName) { this.sName = sName; }

    public void setVisitEventDate(String visitEventDate) { this.visitEventDate = visitEventDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getStartDate() {
        return startDate;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteName() {
        return eName;
    }

}
