package pl.pwr.algorithms;

import pl.pwr.structures.TSPInstance;

public enum TSPAlgorithmType {
    BRUTE_FORCE,
    BRANCH_AND_BOUND,
    DYNAMIC_PROGRAMMING,
    GREEDY,
    SIMULATED_ANNEALING,
    GENETIC;

    public static TSPAlgorithm getAlgorithm(TSPAlgorithmType algorithmType, TSPInstance instance) {
        switch (algorithmType) {
            case BRUTE_FORCE:
                return new TSPBruteForce(instance);
//            case BRANCH_AND_BOUND:
//                return new TSPBranchAndBound(instance);
//            case DYNAMIC_PROGRAMMING:
//                return new TSPDynamicProgramming(instance);
//            case GREEDY:
//                return new TSPGreedy(instance);
//            case SIMULATED_ANNEALING:
//                return new TSPSimulatedAnnealing(instance);
//            case GENETIC:
//                return new TSPGenetic(instance);
            default:
                throw new IllegalArgumentException("Unknown algorithm type");
        }
    }

    public static TSPAlgorithmType parse(String algorithmName) {
        return switch (algorithmName) {
            case "bf" -> BRUTE_FORCE;
            case "bnb" -> BRANCH_AND_BOUND;
            case "dp" -> DYNAMIC_PROGRAMMING;
            case "gr" -> GREEDY;
            case "sa" -> SIMULATED_ANNEALING;
            case "ge" -> GENETIC;
            default -> throw new IllegalArgumentException("Unknown algorithm name: " + algorithmName);
        };
    }
}

