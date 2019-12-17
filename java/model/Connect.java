package BeltLineApplication.java.model;

public class Connect {
    private String sName;
    private String route;
    private String type;

    public String getRoute() {
        return route;
    }

    public String getsName() {
        return sName;
    }

    public String getType() { return type; }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setType(String type) { this.type = type; }
}
