package pl.pwr.measure;

import pl.pwr.algorithms.TSPAlgorithm;

import java.util.concurrent.Callable;

public class MeasureTask implements Callable<Long> {

        private final TSPAlgorithm algorithm;

        public MeasureTask(TSPAlgorithm algorithm) {
            this.algorithm = algorithm;
        }

        @Override
        public Long call() {
            long startTime = System.nanoTime();
            algorithm.solve();
            long endTime = System.nanoTime();
            return endTime - startTime;
        }
}
