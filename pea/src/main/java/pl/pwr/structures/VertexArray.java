package pl.pwr.structures;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// This class is a decorator for ArrayList<Integer> and adds the functionality of generating permutations of a list.
public class VertexArray {
    private final int[] array;
    private final BigInteger permutationCount;
    private BigInteger permutationIndex;

    public VertexArray(int vertexCount) {
        array = new int[vertexCount];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        permutationCount = getPermutationCount(vertexCount);
        permutationIndex = BigInteger.ZERO;
    }

    public VertexArray(int[] array){
        this.array = array;
        permutationCount = getPermutationCount(array.length);
        permutationIndex = BigInteger.ZERO;
    }

    public int get(int index){
        return array[index];
    }

    public int size() {
        return array.length;
    }

    // Generates next mathematical permutation of elements in ArrayList
    public void nextPermutation() {
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }
        if (i <= 0) {
            Collections.reverse(Arrays.asList(array));
            permutationIndex = permutationCount;
            return;
        }
        int j = array.length - 1;
        while (array[j] <= array[i - 1]) {
            j--;
        }
        int a = array[i - 1];
        array[i - 1] = array[j];
        array[j] = a;
        j = array.length - 1;
        while (i < j) {
            a = array[i];
            array[i] = array[j];
            array[j] = a;
            i++;
            j--;
        }
        permutationIndex = permutationIndex.add(BigInteger.ONE);
    }

    public boolean hasNextPermutation() {
        return permutationIndex.compareTo(permutationCount) < 0;
    }

    // Fisher–Yates shuffle
    public void randomPermutation() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

    public BigInteger getPermutationCount() {
        return permutationCount;
    }

    public long getPermutationCountExact() throws ArithmeticException {
        return permutationCount.longValueExact();
    }

    private static BigInteger getPermutationCount(int n) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(BigInteger.valueOf(n)) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        return result;
    }

    public void display() {
        System.out.print("[->");
        for (int j : array) {
            System.out.printf("%3d ->", j);
        }
        System.out.println("]");
    }

    public int[] getArray() {
        return array;
    }

    public static VertexArray generateRandom(int vertexCount) {
        VertexArray vertexArray = new VertexArray(vertexCount);
        vertexArray.randomPermutation();
        return vertexArray;
    }
}
