package BeltLineApplication.java.model;

public class User {
    //initialization of variables
    private String username;
    private String password;
    private String status;
    private String fname;
    private String lname;
    private String name;
    private int emailCount;
    private String userType;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
    public int getEmailCount() { return emailCount; }
    public String getUserType() { return userType; }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    public void setName(String name){this.name = name;}

    public String getName() { return name; }

    public void setEmailCount(int emailCount) { this.emailCount = emailCount; }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
