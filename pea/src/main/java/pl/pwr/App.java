package pl.pwr;

import org.apache.commons.cli.*;
import org.apache.commons.io.*;
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
            // File mode
            String filename = cmd.getOptionValue("f");
            System.out.println("File mode: " + filename);
            // Open file
            try (FileInputStream fis = new FileInputStream(filename)) {
                // Read file
                String content = IOUtils.toString(fis, "UTF-8");
                System.out.println(content);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } else {
            // Interactive mode
            System.out.println("Interactive mode");
        }
        System.out.println("Test");
        MatrixGraph.test();
    }
}
