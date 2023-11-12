package pl.pwr.measure;

import pl.pwr.algorithms.TSPAlgorithmType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class MeasureSuite {
    private MeasureSuite() {
        throw new UnsupportedOperationException("This class should not be instantiated");
    }

    public static void run(TSPAlgorithmType algorithmType, int vertexCount, int iterations) {
        // Run garbage collector before starting
        System.gc();
        System.out.printf("Running %s algorithm on %d vertices for %d iterations%n", algorithmType, vertexCount, iterations);
        List<MeasureTask> tasks = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            tasks.add(new MeasureTask(algorithmType, vertexCount));
        }
//        ExecutorService executor = Executors.newCachedThreadPool();
        var executor = Executors.newSingleThreadExecutor();
//        var executor = Executors.newFixedThreadPool(4);
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
        long totalTime = 0;
        int completedTaskCount = 0;
        for (Future<Long> future : futures) {
            try {
                totalTime += future.get();
                completedTaskCount++;
            } catch (Exception e) {
                System.out.println("MeasureSuite interrupted - unable to get future");
                return;
            }
        }
        System.out.printf("Total time: %d ns%n", totalTime);
        System.out.printf("Completed tasks: %d/%d%n", completedTaskCount, iterations);
        System.out.printf("Average time: %d ns = %f ms %n", totalTime / iterations, totalTime / (double) iterations / 1000000);
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
