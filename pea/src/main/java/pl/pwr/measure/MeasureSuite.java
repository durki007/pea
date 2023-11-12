package pl.pwr.measure;

import pl.pwr.algorithms.TSPAlgorithmType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class MeasureSuite {
    private MeasureSuite() {
        throw new UnsupportedOperationException("This class should not be instantiated");
    }

    public static void run(TSPAlgorithmType algorithmType, int vertexCount, int iterations) {
        System.out.printf("Running %s algorithm on %d vertices for %d iterations%n", algorithmType, vertexCount, iterations);
        List<Future<Long>> futures;
        try (ExecutorService executor = Executors.newCachedThreadPool()) {
            futures = new ArrayList<>();
            for (int i = 0; i < iterations; i++) {
                futures.add(executor.submit(new MeasureTask(algorithmType, vertexCount)));
            }
        } catch (Exception e) {
            System.out.println("MeasureSuite interrupted - unable to create executor");
            return;
        }
        // Await termination and print current progress
        while (MeasureSuite.getCompletedTaskCount(futures) < iterations) {
            System.out.printf("Progress: %d/%d%n", MeasureSuite.getCompletedTaskCount(futures), iterations);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("MeasureSuite interrupted");
                return;
            }
        }
        System.out.println("Done");
        long totalTime = 0;
        for (Future<Long> future : futures) {
            try {
                totalTime += future.get();
            } catch (Exception e) {
                System.out.println("MeasureSuite interrupted - unable to get future");
                return;
            }
        }
        System.out.printf("Total time: %d ns%n", totalTime);
        System.out.printf("Average time: %d ns%n", totalTime / iterations);
    }

    private static int getCompletedTaskCount(List<Future<Long>> futures) {
        int count = 0;
        for (Future<Long> future : futures) {
            if (future.isDone()) {
                count++;
            }
        }
        return count;
    }
}
