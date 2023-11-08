package pl.pwr.structures;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * TestCase
 */
public class TSPInstance {
    public final MatrixGraph graph;
    public final int minPathLength;
    public final Optional<VertexList> minPath;

    public TSPInstance(MatrixGraph graph, int minPathLength, VertexList minPath) {
        this.graph = graph;
        this.minPathLength = minPathLength;
        this.minPath = Optional.of(minPath);
    }

    public TSPInstance(MatrixGraph graph, int minPathLength) {
        this.graph = graph;
        this.minPathLength = minPathLength;
        this.minPath = Optional.empty();
    }

    public TSPInstance(FileInputStream fis) throws IOException {
        String fileString = IOUtils.toString(fis, "UTF-8");
        var graphDescription = fileString.split("( +)|((\\n\\r)+( ?))|(\r\n)|(\r)|(\n)");
        var filtered = Arrays.stream(graphDescription).filter(s -> s != "").toArray();
        for (var line : filtered) {
            System.out.println(line);
        }
        this.graph = null; // TODO: FIXX
        this.minPathLength = 0;
        this.minPath = Optional.empty();
    }
}
