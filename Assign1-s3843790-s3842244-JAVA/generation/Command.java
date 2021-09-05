package generation;

//Class for generating Command objects
public class Command {
    private String Operation;
    private String Category;
    private double lat;
    private double lon;

    public Command() {
        // Default empty constructor
    }

    public Command(String Operation, String Category, double lat, double lon) {
        this.Operation = Operation;
        this.Category = Category;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Command{" +
                "Operation='" + Operation + '\'' +
                ", Category='" + Category + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }

}
