package pl.pwr;

import org.apache.commons.cli.*;
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
            TSPInstance tspInstance = new TSPInstance(fis);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
