package generation;
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

    // Used to create random commands for scenario 1
    public void GenerateKnnSearches(int numCommands, String commandOutputFileName){
        for(int i = 0; i < numCommands; i++){
            int location = getRandomLocation();
            String category = randomPointsList.get(location).getCategory().toString();
            double lat = randomPointsList.get(location).getLat();
            double lon = randomPointsList.get(location).getLon();
            Command command = new Command("S", category, lat, lon, getRandomKValue());
            randomCommandList.add(command);
        }
        OutputFile(commandOutputFileName);
    }

    // Used to create random commands for scenario 2
    public void GenerateDynamicPointsSet(int numCommands, String commandOutputFileName) {
        try{
            for(int i  = 0; i < numCommands; i++){
                int location = getRandomLocation();
                String ID = randomPointsList.get(location).getId();
                String category = randomPointsList.get(location).getCategory().toString();
                double lat = randomPointsList.get(location).getLat();
                double lon = randomPointsList.get(location).getLon();
                String Operation = getRandomOperation();
                Command command;
                if (Operation.equals("S")){
                    command = new Command(Operation, category, lat, lon, getRandomKValue());
                }else{
                    command = new Command(Operation, ID, category, lat, lon);
                }
                randomCommandList.add(command);
            }
        }catch(Exception e){
            System.err.println(e);
        }
        OutputFile(commandOutputFileName);
    }

    // Picks a random point from the generated data
    private int getRandomLocation() {
        return random.nextInt(randomPointsList.size());
    }

    // Creates a random operation
    private String getRandomOperation() throws Exception {
        int[] Integers = new int[4];
        Integers[0] = 1;
        Integers[1] = 2;
        Integers[2] = 3;
        Integers[3] = 4;
        int Result = Integers[random.nextInt(4)];

        return switch (Result) {
            case 1 -> "A";
            case 2 -> "D";
            case 3 -> "C";
            case 4 -> "S";
            default -> throw new Exception();
        };
    }

    // Returns a random K value to search for
    private int getRandomKValue() {
        return random.nextInt(MaxK);
    }

    //Handles outputting the commands into a text file
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
