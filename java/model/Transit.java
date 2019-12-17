package BeltLineApplication.java.model;

public class Transit {
    private String type;
    private String route;
    private double price;
    private int numberOfConnectedSites;
    private int numberOfTransitLogged;

    public String getType() {
        return this.type;
    }

    public String getRoute() {
        return this.route;
    }

    public double getPrice() {
        return this.price;
    }

    public int getNumberOfConnectedSites() {
        return numberOfConnectedSites;
    }

    public int getNumberOfTransitLogged() {
        return numberOfTransitLogged;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNumberOfTransitLogged(int numberOfTransitLogged) {
        this.numberOfTransitLogged = numberOfTransitLogged;
    }

    public void setNumberOfConnectedSites(int numberOfConnectedSites) {
        this.numberOfConnectedSites = numberOfConnectedSites;
    }
}
