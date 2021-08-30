package nearestNeigh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is required to be implemented. Naive approach implementation.
 *
 *
 */
public class NaiveNN implements NearestNeigh {
    // create new instance of Point class
    Point point = new Point();
    // create arraylist to store all the points
    List<Point> pointsList = new ArrayList<>();

    @Override
    public void buildIndex(List<Point> points) {
        pointsList = points; // copy
    }

    @Override
    public List<Point> search(Point searchTerm, int k) {
        List<Point> searchedPointsList = pointsList;
        List<Point> sortedSearchedPointsList = new ArrayList<>();

        for (int i = 0; i < searchedPointsList.size(); i++) {
            searchedPointsList.get(i).setDistance(searchedPointsList.get(i).distTo(searchTerm));
        }

        for(int i=0;i<searchedPointsList.size()-1;i++){
            int m = i;
            for(int j=i+1;j<searchedPointsList.size();j++){
                if(searchedPointsList.get(m).getDistance() > searchedPointsList.get(j).getDistance())
                    m = j;
            }
            //swapping elements at position i and m
            Point temp = searchedPointsList.get(i);
            searchedPointsList.set(i, searchedPointsList.get(m));
            searchedPointsList.set(m, temp);
        }

        for (int i = 0; i < k ; i++) {
            sortedSearchedPointsList.add(searchedPointsList.get(i));
        }

        return sortedSearchedPointsList;
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
