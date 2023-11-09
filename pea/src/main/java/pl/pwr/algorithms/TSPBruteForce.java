package pl.pwr.algorithms;

import java.util.Optional;

import pl.pwr.structures.*;

/**
 * TSPBruteForce
 */
public class TSPBruteForce implements TSPAlgorithm {

    private final TSPInstance instance;

    public TSPBruteForce(TSPInstance instance) {
        this.instance = instance;
    }
    public TSPSolution solve() {
        return new TSPSolution(0);
    }
}
