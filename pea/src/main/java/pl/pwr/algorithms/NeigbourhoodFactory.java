package pl.pwr.algorithms;

import pl.pwr.structures.VertexArray;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Neigbourhood factory.
 */
public class NeigbourhoodFactory {

    /**
     * Two opt list.
     *
     * @param path the path
     * @return the list
     */
    public static List<VertexArray> twoOpt(VertexArray path) {
        var neighbourhood = new ArrayList<VertexArray>();
        var vertexCount = path.size();
        for (int i = 0; i < vertexCount - 1; i++) {
            for (int j = i + 1; j < vertexCount; j++) {
                neighbourhood.add(twoOptSwap(path, i, j));
            }
        }
        return neighbourhood;
    }

    /**
     * Two opt list.
     *
     * @param path the path
     * @return new VertexArray with swapped edges
     */
    public static VertexArray twoOptSwap(VertexArray path, int i, int j) {
        var vertexCount = path.size();
        var array = path.getArray().clone();
        var newArray = new int[vertexCount];
        if (i >= 0) System.arraycopy(array, 0, newArray, 0, i);
        int d = 0;
        for (int k = i; k <= j; k++) {
            newArray[k] = array[j - d];
            d++;
        }
        if (array.length - (j + 1) >= 0)
            System.arraycopy(array, j + 1, newArray, j + 1, array.length - (j + 1));
        System.arraycopy(newArray, 0, array, 0, array.length);
        return new VertexArray(newArray);
    }

    public static VertexArray threeOptSwap(VertexArray path, int i, int j, int k) {
        var vertexCount = path.size();
        var array = path.getArray().clone();
        var newArray = new int[vertexCount];
        if (i >= 0) System.arraycopy(array, 0, newArray, 0, i);
        int d = 0;
        for (int l = i; l <= j; l++) {
            newArray[l] = array[j - d];
            d++;
        }
        if (array.length - (j + 1) >= 0)
            System.arraycopy(array, j + 1, newArray, j + 1, array.length - (j + 1));
        System.arraycopy(newArray, 0, array, 0, array.length);
        return new VertexArray(newArray);
    }

}
