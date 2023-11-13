package pl.pwr.measure;

import pl.pwr.algorithms.TSPAlgorithmType;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class MeasureSuite {

    private final TSPAlgorithmType algorithmType;
    private final int vertexCount;
    private final int iterations;

    private ArrayList<Long> times = null;
    private int completedTaskCount = 0;
    private long totalTime = 0;
    private double averageTime = 0;

    public MeasureSuite(TSPAlgorithmType algorithmType, int vertexCount, int iterations) {
        this.algorithmType = algorithmType;
        this.vertexCount = vertexCount;
        this.iterations = iterations;
    }

    public void run() {
        // Run garbage collector before starting
        System.gc();
        System.out.printf("Running %s algorithm on %d vertices for %d iterations... ", algorithmType, vertexCount, iterations);
        List<MeasureTask> tasks = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            tasks.add(new MeasureTask(algorithmType, vertexCount));
        }
//        ExecutorService executor = Executors.newCachedThreadPool();
        var executor = Executors.newSingleThreadExecutor();
        List<Future<Long>> futures = null;
        try {
            futures = executor.invokeAll(tasks);
        } catch (Exception e) {
            executor.shutdown();
            System.out.println("MeasureSuite interrupted - unable to invoke tasks");
            e.printStackTrace();
            return;
        }
        executor.shutdown();
        // Wait for all tasks to complete
        // Await termination and print current progress
        System.out.println("Done");
        this.times = new ArrayList<>();
        totalTime = 0;
        completedTaskCount = 0;
        for (Future<Long> future : futures) {
            try {
                totalTime += future.get();
                this.times.add(future.get());
                completedTaskCount++;
            } catch (Exception e) {
                System.out.println("MeasureSuite interrupted - unable to get future");
                return;
            }
        }
        this.averageTime = totalTime / (double) iterations / 1000000;
    }

    public void displayResults() {
        System.out.printf("Results for %s algorithm on %d vertices for %d iterations:%n", algorithmType, vertexCount, iterations);
        System.out.printf("Total time: %d ns%n", totalTime);
        System.out.printf("Completed tasks: %d/%d%n", completedTaskCount, iterations);
        System.out.printf("Average time: %d ns = %f ms %n", totalTime / iterations, totalTime / (double) iterations / 1000000);
    }

    public ArrayList<Long> getTimes() {
        return times;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public double getAverageTime() {
        return averageTime;
    }

    public static void runAll(TSPAlgorithmType algorithm, int maxVertexCount, int iterations, String outputFilename) {
        var suites = new ArrayList<MeasureSuite>();
        for (int i = 2; i <= maxVertexCount; i++) {
            suites.add(new MeasureSuite(algorithm, i, iterations));
        }
        // Run all suites
        for (var suite : suites) {
            suite.run();
        }
        // Display results
        for (var suite : suites) {
            suite.displayResults();
            System.out.println("--------------------");
        }
        // Save results to file
        var filename = outputFilename + "/" + algorithm + ".csv";
        System.out.println("Saving results to file: " + filename);
        try (var writer = new FileWriter(filename)) {
            writer.write("vertexCount;averageTime\n");
            for (var suite : suites) {
                writer.write(suite.getVertexCount() + ";" + suite.getAverageTime() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Unable to save results to file");
            e.printStackTrace();
        }
    }
}
