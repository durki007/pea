package pl.pwr.structures;

import java.util.ArrayList;
import java.util.Optional;

/**
 * MatrixGraph
 */
public class MatrixGraph {

    private ArrayList<ArrayList<Optional<Integer>>> matrix;

    public MatrixGraph(ArrayList<ArrayList<Optional<Integer>>> matrix) {
        this.matrix = matrix;
    }

    public static void test() {
        System.out.println("Hello World!");
    }
}
