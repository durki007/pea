package pl.pwr.measure;

import pl.pwr.algorithms.TSPAlgorithmType;
import pl.pwr.structures.FullGraphFactory;

import java.util.concurrent.Callable;

public class MeasureTask implements Callable<Long> {

    private final TSPAlgorithmType algorithmType;
    private final int vertexCount;

    public MeasureTask(TSPAlgorithmType algorithmType, int vertexCount) {
        this.algorithmType = algorithmType;
        this.vertexCount = vertexCount;
    }

    @Override
    public Long call() {
        var instance = FullGraphFactory.generateRandom(vertexCount);
        var algorithm = TSPAlgorithmType.getAlgorithm(algorithmType, instance);
        long startTime = System.nanoTime();
        algorithm.solve();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
