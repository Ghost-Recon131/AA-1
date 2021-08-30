package nearestNeigh;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is required to be implemented.  Naive approach implementation.
 *
 * 
 */
public class NaiveNN implements NearestNeigh{
    Point point = new Point(); // create new instance of Point class
    ArrayList<Point> ListofPoints = new ArrayList<>(); // create arraylist to store all the points

    @Override
    public void buildIndex(List<Point> points) {
        ListofPoints = (ArrayList<Point>) points; // Store the points in an unsorted arraylist
        //may need more work here
    }

    @Override
    public List<Point> search(Point searchTerm, int k) {
        // To be implemented.
        return new ArrayList<>();
    }

    @Override
    public boolean addPoint(Point point) {
        // To be implemented.
        return false;
    }

    @Override
    public boolean deletePoint(Point point) {
        // To be implemented.
        return false;
    }

    @Override
    public boolean isPointIn(Point point) {
        // To be implemented.
        return false;
    }

}
