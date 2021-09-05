package generation;

import nearestNeigh.Category;
import nearestNeigh.Point;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGeneration {
    // List of randomly generated point objects
    private static List<Point> randomPointsList = new ArrayList<>();
    Random random = new Random();

    private double randLat = -39.3225797382;
    private double randLon = 140.079357579;

    public void GenerateData(long numElements, String dataOutputFileName) {
        for(long i=0, j = 1; i < numElements; i++ , j++){
            Category category = Point.parseCat(getRandomCategory()); // convert the generated string result into Category
            double min = 0.0000000001; //minimum & maximum values to add to latitude / longitude
            double max = 0.0000000009;
            double randomValue = min + (max - min) * random.nextDouble(); // generate value within the range
            randLat = randLat + randomValue; // add to class wide variable so we are always ascending
            randLon = randLon + randomValue;

            Point point = new Point(String.valueOf(j), category, randLat, randLon);
            randomPointsList.add(point); //add new point to arraylist
        }
        // Output File when data is generated
        OutputFile(dataOutputFileName);
    }

    public List<Point> getRandomPointsList() {
        return randomPointsList;
    }

    //Create integer array of 3 numbers, representing 3 enum values, then generate random number between 0-2 to access
    // the number at the array location, then return a Category
    private String getRandomCategory(){
        int[] Integers = new int[3];
        Integers[0] = 1;
        Integers[1] = 2;
        Integers[2] = 3;
        int Result = Integers[random.nextInt(2)];

        return switch (Result) {
            case 1 -> "RESTAURANT";
            case 2 -> "EDUCATION";
            case 3 -> "HOSPITAL";
            default -> null;
        };
    }

    private void OutputFile(String dataOutputFileName){
        try{
            //Create necessary file writers & buffered writer to output generated data as a file
            File file = new File(dataOutputFileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writeFileToDisk = new BufferedWriter(fileWriter);

            for (Point point : randomPointsList) {
                writeFileToDisk.write(point.toString()); //converts point object to string
                writeFileToDisk.newLine(); //create new line for next output
            }
            writeFileToDisk.flush(); //take data in memory & write to disk
            writeFileToDisk.close();
        }catch(IOException e){
            System.err.println(e);
        }
    }

}
