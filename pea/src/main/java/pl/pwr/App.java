package pl.pwr;

import org.apache.commons.cli.*;
import pl.pwr.algorithms.TSPBruteForce;
import pl.pwr.structures.*;

import java.io.FileInputStream;

public class App {
    public static void main(String[] args) throws ParseException {
        // Command line options
        Options options = new Options();
        options.addOption(new Option("f", "file", true, "Input file"));
        // Parse command line options
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        // Determine mode of operation
        if (cmd.hasOption("f")) {
            fileReadMode(cmd);
        } else {
            interactiveMode();
        }
    }

    private static void interactiveMode() {
        // Interactive mode
        System.out.println("Interactive mode");
    }

    private static void fileReadMode(CommandLine cmd) {
        // File mode
        String filename = cmd.getOptionValue("f");
        System.out.println("File mode: " + filename);
        // Open file
        try (FileInputStream fis = new FileInputStream(filename)) {
            // Read file
            TSPInstance tspInstance = TSPInstance.createFromFile(fis);
            // Solve
            TSPSolution tspSolution = new TSPBruteForce(tspInstance).solve();
            // Display solution
            tspSolution.display();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
