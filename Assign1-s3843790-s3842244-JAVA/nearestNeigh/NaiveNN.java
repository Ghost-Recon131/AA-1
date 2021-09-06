package nearestNeigh;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is required to be implemented. Naive approach implementation.
 *
 *
 */
public class NaiveNN implements NearestNeigh {
    // create arraylist to store all the points
    List<Point> pointsList = new ArrayList<>();

    @Override
    public void buildIndex(List<Point> points) {
        long startTime = System.nanoTime();
        // copy
        pointsList = points;

        long endTime = System.nanoTime();
        System.err.println("Naive buildIndex time taken = " + ((double)(endTime - startTime)) / Math.pow(10, 9) + " sec");
    }

    @Override
    public List<Point> search(Point searchTerm, int k) {
        long startTime = System.nanoTime();

        List<Point> searchedPointsList = pointsList; // ArrayList for storing points
        List<Point> sortedSearchedPointsList = new ArrayList<>(); // ArrayList for storing sorted points

        for (int i = 0; i < searchedPointsList.size(); i++) { // Calculate the distance for every point in arraylist
            searchedPointsList.get(i).setDistance(searchedPointsList.get(i).distTo(searchTerm));
        }

        // This nested for loop sorts distance in ascending order from the searchTerm
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

        // Checks that the points are of the same category, then adds to another arraylist
        for (int i = 0; i <= k ; i++) {
            if(searchedPointsList.get(i).categoryIsSame(searchTerm)){
                sortedSearchedPointsList.add(searchedPointsList.get(i));
            }
        }

        long endTime = System.nanoTime();
        System.err.println("Naive search time taken = " + ((double)(endTime - startTime)) / Math.pow(10, 9) + " sec");

        return sortedSearchedPointsList;
    }

    @Override
    public boolean addPoint(Point point) {
        long startTime = System.nanoTime();

        boolean status;
        if (isPointInNonTimed(point)) { //check that point is not already existing, then adds it
            status =  false;
        } else {
            pointsList.add(point);
            status = true;
        }
        long endTime = System.nanoTime();
        System.err.println("Naive addPoint time taken = " + ((double)(endTime - startTime)) / Math.pow(10, 9) + " sec");

        return status;
    }

    @Override
    public boolean deletePoint(Point point) { //check that point exists, then removes it
        long startTime = System.nanoTime();

        boolean status;
        if (isPointInNonTimed(point)) {
            pointsList.remove(point);
            status =  true;
        } else {
            status =  false;
        }
        long endTime = System.nanoTime();
        System.err.println("Naive deletePoint time taken = " + ((double)(endTime - startTime)) / Math.pow(10, 9) + " sec");

        return status;
    }

    @Override
    public boolean isPointIn(Point point) { //check that point exists in the data structure
        long startTime = System.nanoTime();

        boolean status = false;
        for (int i = 0; i < pointsList.size(); i++) {
            if (pointsList.get(i).equals(point)) {
                status =  true;
            }
        }
        long endTime = System.nanoTime();
        System.err.println("Naive isPointIn time taken = " + ((double)(endTime - startTime)) / Math.pow(10, 9) + " sec");

        return status;
    }

    // Used so it does not print out extra times in console, otherwise is the same code
    public boolean isPointInNonTimed(Point point) { //check that point exists in the data structure
        boolean status = false;
        for (int i = 0; i < pointsList.size(); i++) {
            if (pointsList.get(i).equals(point)) {
                status =  true;
            }
        }
        return status;
    }

}
