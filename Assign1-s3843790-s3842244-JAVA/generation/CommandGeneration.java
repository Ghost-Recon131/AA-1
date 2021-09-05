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
    private List<Point> randomPointsList = DataGeneration.getRandomPointsList(); //get list of points previously generated
    private static List<Command> randomCommandList = new ArrayList<>();
    Random random = new Random();
    int MaxK = 20; //maximum number of neighbours to search for

    public void GenerateKnnSearches(int numCommands, String commandOutputFileName){
        for(int i = 0; i < numCommands; i++){
            int location = getRandomLocation();
            String category = randomPointsList.get(location).getCategory().toString();
            double lat = randomPointsList.get(location).getLat();
            double lon = randomPointsList.get(location).getLon();
            Command command = new Command("S", category, lat, lon, getRandomKValue()); // String Operation, String Category, double lat, double lon, int k
            randomCommandList.add(command);
        }
        OutputFile(commandOutputFileName);
    }

    public void GenerateDynamicPointsSet(int numCommands, String commandOutputFileName){
        //TODO Use this method to generate commands for scenario 2
    }

    private int getRandomLocation() {
        return random.nextInt(randomPointsList.size());
    }

    private int getRandomKValue() {
        return random.nextInt(MaxK);
    }

    private void OutputFile(String dataOutputFileName){
        try{
            //Create necessary file writers & buffered writer to output generated data as a file
            File file = new File(dataOutputFileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writeFileToDisk = new BufferedWriter(fileWriter);

            for (Command command : randomCommandList) {
                if(command.getOperation().equals("S")){
                    writeFileToDisk.write(command.searchCommandToString()); //converts point object to string
                }else{
                    writeFileToDisk.write(command.actionCommandToString()); //converts point object to string
                }
                writeFileToDisk.newLine(); //create new line for next output
            }
            writeFileToDisk.flush(); //take data in memory & write to disk
            writeFileToDisk.close();
        }catch(IOException e){
            System.err.println(e);
        }
    }

}
