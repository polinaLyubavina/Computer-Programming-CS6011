import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;


public class Rainfall {

    public ArrayList<Triplet<String, Integer, Double>> dataTriplets = new ArrayList<Triplet<String, Integer, Double>>();
    String cityName; 

    // takes the file's name
    Scanner fileReader;

    // constructor
    public Rainfall (String filename) throws FileNotFoundException {
        fileReader = new Scanner(new FileInputStream(filename));
    }

    // reads from file
    public void readFile() {
        // saves first line for city name
        cityName = fileReader.next();

        while(fileReader.hasNext()) {
            String tempmonth = fileReader.next();
            Integer tempyear = fileReader.nextInt();
            Double temprain = fileReader.nextDouble();

            Triplet<String, Integer, Double> triplet = new Triplet<String, Integer, Double>(tempmonth, tempyear, temprain);
            // adds new triplet to the array
            dataTriplets.add(triplet);
        }
    }

    // writes to file
    public void writeFile(String filename) throws FileNotFoundException {
        // create printer
        PrintWriter printer = new PrintWriter(new FileOutputStream(filename, true));

        // calculate overall rainfall
        double overallRain = 0.0;
        for(int i = 0; i < dataTriplets.size(); i++) {
            overallRain += dataTriplets.get(i).getThird(); 
        }

        // print overall rain to file
        printer.printf("Overall average rainfall for " + cityName +
           " is %.2f inches.\n", overallRain/dataTriplets.size());
        
        // calculate monthly averages
        for(int i = 0; i < 12; i++) {
            Triplet<String, Integer, Double> tempTriplet = dataTriplets.get(i);
            double monthAve = 0.0;
            for(int j = 0; j < dataTriplets.size(); j++) {
                Triplet<String, Integer, Double> secondTempTriplet = dataTriplets.get(j);
                if(secondTempTriplet.getFirst().equals(tempTriplet.getFirst())) {
                    monthAve += secondTempTriplet.getThird(); 
                }
            }
            // write monthly averages to file
            monthAve = monthAve/(dataTriplets.size()/12);
            printer.printf("Average rainfall amount for %s is %.2f inches.\n",
             tempTriplet.getFirst(), monthAve); 
        }        

        // close stream
        printer.close(); 
    }
    
    public static void main(String[] args) throws FileNotFoundException {

        // gives me feeling of warm hope
        Rainfall candleflame = new Rainfall("MaconRainfall.txt");
        Rainfall fireplace = new Rainfall("AtlantaRainfall.txt");

        candleflame.readFile();
        fireplace.readFile();

        candleflame.writeFile("rainfall_data.txt");
        fireplace.writeFile("rainfall_data.txt");

    }
}
