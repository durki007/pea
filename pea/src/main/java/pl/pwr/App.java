package pl.pwr;

import org.apache.commons.cli.*;
import pl.pwr.algorithms.TSPAlgorithmType;
import pl.pwr.measure.MeasureSuite;
import pl.pwr.structures.*;

import java.io.FileInputStream;

public class App {
    public static void main(String[] args) throws ParseException {
        // Command line options
        Options options = new Options();
        options.addOption(new Option("f", "file", true, "Input file"));
        options.addOption(new Option("g", "generate", true, "Generate full graph with given vertex count"));
        options.addOption(new Option("a", "algorithm", true, "Algorithm to use"));
        // Parse command line options
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        // Determine mode of operation
        if (cmd.hasOption("f")) {
            fileReadMode(cmd);
        } else if (cmd.hasOption("g")) {
            generateMode(cmd);
        } else {
            interactiveMode(cmd);
        }
    }

    private static void interactiveMode(CommandLine cmd) {
        // Interactive mode
        System.out.println("Full measurement mode");
        var resultPath = "measurements/";
        MeasureSuite.runAll(TSPAlgorithmType.DYNAMIC_PROGRAMMING, 15, 100,resultPath);
        MeasureSuite.runAll(TSPAlgorithmType.BRUTE_FORCE, 10, 100, resultPath);
    }

    private static void generateMode(CommandLine cmd) {
        int vertexCount = Integer.parseInt(cmd.getOptionValue("g"));
        String algorithm = cmd.getOptionValue("a");

        System.out.println("Generate mode: [" + vertexCount + "]");
        System.out.printf("Algorithm: %s%n", TSPAlgorithmType.parse(algorithm));
        var tspInstance = FullGraphFactory.generateRandom(vertexCount);
        tspInstance.getGraph().display();
        // Solve
        TSPSolution tspSolution = TSPAlgorithmType.getAlgorithm(TSPAlgorithmType.parse(algorithm), tspInstance).solve();
        // Display solution
        System.out.println("Expected solution:");
        tspInstance.getSolution().display();
        System.out.println("Generated solution:");
        tspSolution.display();
    }

    private static void fileReadMode(CommandLine cmd) {
        // File mode
        String filename = cmd.getOptionValue("f");
        String algorithm = cmd.getOptionValue("a");
        System.out.println("File mode: " + filename);
        System.out.printf("Algorithm: %s%n", TSPAlgorithmType.parse(algorithm));
        // Open file
        try (FileInputStream fis = new FileInputStream(filename)) {
            // Read file
            TSPInstance tspInstance = TSPInstance.createFromFile(fis);
            // Solve
            TSPSolution tspSolution = TSPAlgorithmType.getAlgorithm(TSPAlgorithmType.parse(algorithm), tspInstance).solve();
            // Display solution
            tspSolution.display();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
