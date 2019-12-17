package BeltLineApplication.java.model;

public class Staff extends Employee {
    private Site site = new Site();
    private int shifts = 0;


    public void assignSite(String sName) {
        site.setSname(sName);
    }

    public String getAssignedSite() {
        return site.getSname();
    }

    public void setShifts(int shifts) {
        this.shifts = shifts;
    }

    public int getShifts(){
        return shifts;
    }
}
