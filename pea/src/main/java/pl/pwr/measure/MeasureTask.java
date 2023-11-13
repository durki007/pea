package pl.pwr.measure;

import pl.pwr.algorithms.TSPAlgorithmType;
import pl.pwr.structures.FullGraphFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

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
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        var instance = FullGraphFactory.generateRandom(vertexCount);
        var algorithm = TSPAlgorithmType.getAlgorithm(algorithmType, instance);
//        long startTime = getThreadTime(bean);
        long startTime = System.nanoTime();
        algorithm.solve();
        long endTime = System.nanoTime();
//        long endTime = getThreadTime(bean);
        return endTime - startTime;
    }

    private static long getThreadTime(ThreadMXBean bean) {
        return bean.getCurrentThreadUserTime();
    }
}
