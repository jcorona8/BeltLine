package BeltLineApplication.java.model;

import javafx.collections.ObservableList;

public class Employee extends User {
    private int employeeID;
    private String phone;
    private String address;
    private String city;
    private String state;
    private int zipCode;
    private String sname;
    private ObservableList<String> email;

    public int getEmployeeID() {
        return this.employeeID;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getZipCode() {
        return this.zipCode;
    }

    public void setEmployeeID(int id) {
        this.employeeID = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(int zipCode) {
        this.zipCode = zipCode;
    }

}
