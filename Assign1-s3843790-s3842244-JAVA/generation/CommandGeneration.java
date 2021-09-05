package generation;

import generation.Command;
import nearestNeigh.Point;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommandGeneration {
    List<Command> listOfCommands = new ArrayList<>();
    DataGeneration dataGeneration = new DataGeneration();
    private List<Point> randomPointsList = dataGeneration.getRandomPointsList(); //get list of points previously generated

    public void GenerateKnnSearches(String commandOutputFileName){
        //TODO Use this method to generate commands for scenario 1

    }

    public void GenerateDynamicPointsSet(String commandOutputFileName){
        //TODO Use this method to generate commands for scenario 2

    }

    private void OutputFile(String dataOutputFileName){
        try{
            //Create necessary file writers & buffered writer to output generated data as a file
            File file = new File(dataOutputFileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writeFileToDisk = new BufferedWriter(fileWriter);

            for (Command command : listOfCommands) {
                writeFileToDisk.write(command.toString()); //converts point object to string
                writeFileToDisk.newLine(); //create new line for next output
            }
            writeFileToDisk.flush(); //take data in memory & write to disk
            writeFileToDisk.close();
        }catch(IOException e){
            System.err.println(e);
        }
    }

}
