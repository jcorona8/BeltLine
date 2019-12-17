package BeltLineApplication.java.model;

public class Take {
    private String username;
    private String route;
    private String type;
    private String date;
    private String site;
    private double price;

    public String getUsername() { return username; }

    public String getRoute() { return route; }

    public String getType() { return type; }

    public String getDate() { return date; }

    public String getSite() { return site; }

    public double getPrice() { return price; }

    public void setUsername(String username) { this.username = username; }

    public void setRoute(String route) { this.route = route; }

    public void setType(String type) { this.type = type; }

    public void setDate(String date) { this.date = date; }

    public void setSite(String site) { this.site = site; }

    public void setPrice(double price) { this.price = price; }
}
