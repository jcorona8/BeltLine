package BeltLineApplication.java.model;

public class Event {
    private String ename;
    private String startDate;
    private String sname;
    private String endDate;
    private double price;
    private int capacity;
    private String description;
    private int minStaffReq;
    private int remaining;
    private int totalVisits;
    private int myVisits;

    public String getEname() {
        return this.ename;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getSname() {
        return this.sname;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public double getPrice() {
        return this.price;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public String getDescription() {
        return this.description;
    }

    public int getMinStaffReq() {
        return this.minStaffReq;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMinStaffReq(int minStaffReq) {
        this.minStaffReq = minStaffReq;
    }

    public int getRemaining(){return this.remaining;}

    public void setRemaining(int remaining) {this.remaining = remaining;}

    public void setMyVisits(int myVisits) { this.myVisits = myVisits; }

    public int getMyVisits() { return myVisits; }

    public void setTotalVisits(int totalVisits) { this.totalVisits = totalVisits; }

    public int getTotalVisits() { return totalVisits; }
}
