package pl.pwr.algorithms;

import pl.pwr.structures.TSPInstance;

public enum TSPAlgorithmType {
    BRUTE_FORCE,
    BRANCH_AND_BOUND,
    DYNAMIC_PROGRAMMING,
    GREEDY,
    LOCAL_SEARCH,
    TABU_SEARCH,
    SIMULATED_ANNEALING,
    GENETIC;

    public static TSPAlgorithm getAlgorithm(TSPAlgorithmType algorithmType, TSPInstance instance) {
        return switch (algorithmType) {
            case BRUTE_FORCE -> new TSPBruteForce(instance);
            case DYNAMIC_PROGRAMMING -> new TSPDynamicRecursive(instance);
            case LOCAL_SEARCH -> new TSPLocalSearch(instance);
            case TABU_SEARCH -> new TSPTabuSearch(instance);
            default -> throw new IllegalArgumentException("Unknown algorithm type");
        };
    }

    public static TSPAlgorithmType parse(String algorithmName) {
        return switch (algorithmName) {
            case "bf" -> BRUTE_FORCE;
            case "bnb" -> BRANCH_AND_BOUND;
            case "dp" -> DYNAMIC_PROGRAMMING;
            case "gr" -> GREEDY;
            case "sa" -> SIMULATED_ANNEALING;
            case "ge" -> GENETIC;
            case "ls" -> LOCAL_SEARCH;
            case "ts" -> TABU_SEARCH;
            default -> throw new IllegalArgumentException("Unknown algorithm name: " + algorithmName);
        };
    }
}

