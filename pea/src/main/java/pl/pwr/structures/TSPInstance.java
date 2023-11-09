package pl.pwr.structures;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * TestCase
 */
public class TSPInstance {
    public final MatrixGraph graph;
    public final TSPSolution solution;

    public TSPInstance(MatrixGraph graph, int minPathLength, VertexArray minPath) {
        this.graph = graph;
        this.solution = new TSPSolution(minPathLength, minPath);
    }

    public TSPInstance(MatrixGraph graph, int minPathLength) {
        this.graph = graph;
        this.solution = new TSPSolution(minPathLength);
    }

    public TSPInstance(FileInputStream fis) throws IOException {
        String fileString = IOUtils.toString(fis, StandardCharsets.UTF_8);
        var filtered = new ArrayList<>(Arrays.asList(fileString.split("\\s+")));
        // Create matrix
        int vertexCount = Integer.parseInt(filtered.get(0));
        ArrayList<ArrayList<Optional<Integer>>> matrix = new ArrayList<>();
        for (int i = 1; i <= vertexCount * vertexCount; i++) {
            int x = (i - 1) / vertexCount;
            int y = (i - 1) % vertexCount;
            if (x == 0) {
                matrix.add(new ArrayList<>());
            }
            if (x == y) {
                matrix.get(x).add(Optional.empty());
            } else {
                matrix.get(x).add(Optional.of(Integer.parseInt(filtered.get(i))));
            }
        }
        this.graph = new MatrixGraph(matrix);
        this.solution = new TSPSolution(Integer.parseInt(filtered.getLast().replace("sum_min=", "")));
        // Display info
        System.out.println("Loaded TSP instance from file");
        System.out.println("Vertex count: " + vertexCount);
        System.out.println("Min path length: " + this.solution.minPathLength);
        this.graph.display();
    }

    public TSPSolution getSolution() {
        return solution;
    }

    public MatrixGraph getGraph() {
        return graph;
    }
}

