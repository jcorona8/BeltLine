package BeltLineApplication.java.model;

public class Site {
    private String sname;
    private String address;
    private int zipCode;
    private int openEveryday;
    private String managerUsername;

    public String getSname() {
        return this.sname;
    }

    public String getAddress() {
        return this.address;
    }

    public int getZipCode() {
        return this.zipCode;
    }

    public String getOpenEveryday() {
        if (this.openEveryday == 1){
            return "yes";
        }
        return "no";
    }

    public String getManagerUsername() {
        return this.managerUsername;
    }

    public void setSname(String name) {
        this.sname = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setOpenEveryday(int num) {
        this.openEveryday = num;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

}
