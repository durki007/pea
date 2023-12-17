package pl.pwr.structures;

public enum TspFileFormat {
    TSPLIB,
    FULL_GRAPH;

    public static TspFileFormat parse(String formatName) {
        return switch (formatName) {
            case "tsplib" -> TSPLIB;
            case "full_graph" -> FULL_GRAPH;
            default -> FULL_GRAPH;
        };
    }
}
