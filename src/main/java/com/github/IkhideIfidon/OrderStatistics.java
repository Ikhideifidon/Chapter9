package com.github.IkhideIfidon;

import java.util.Random;

public class OrderStatistics {

    private static final Random random = new Random();

    /**
     * Find Minimum and Maximum in a given array simultaneously with a less leading constant
     * @param A: Given array of integers.
     * @return : An array of two elements.
     */
    public static int[] minMax(int[] A) {
        if (A == null) return null;
        int n = A.length;
        if (n == 1) return new int[] {A[0], A[0]};

        int i, min, max;
        if (n % 2 == 0) {
            if (A[0] < A[1]) {
                min = A[0];
                max = A[1];
            } else {
                max = A[0];
                min = A[1];
            }
            i = 2;
        } else {
            min = max = A[0];
            i = 1;
        }

        while (i < n - 1) {
            if (A[i] < A[i+1]) {
                if (A[i] < min)
                    min = A[i];
                if  (A[i+1] > max)
                    max = A[i+1];
            } else {
                if (A[i+1] < min)
                    min = A[i+1];
                if (A[i] > max)
                    max = A[i];
            }
            i += 2;
        }
        return new int[] {min, max};
    }

    public static int randomizedPartition(int[] A, int left, int right) {
        if (A == null) return -1;
        int n = A.length;
        if (n <= 1)                                     // Is "A" an empty array or just s single element array?
            return n == 0 ? -1 : 0;

        int x = random.nextInt(right - left + 1) + left;
        swap(A, x, right);
        int pivot = A[right];
        int i = left;
        int j = left - 1;

        for (; i < right; i++) {
            if (A[i] <= pivot)
                swap(A, ++j, i);
        }
        swap(A, ++j, right);
        return j;
    }

    private static void swap(int[] A, int from, int to) {
        int temp = A[from];
        A[from] = A[to];
        A[to] = temp;
    }

    /**
     * Hoare's partition returns the correct index of the chosen pivot in the array,
     * but does not put the element at the correct place.
     * @param A: A Given array of integers
     * @param left: leftmost index.
     * @param right: Rightmost index
     * @return : Correct index of the chosen pivot element.
     */
    public static int doHoarePartitioning(int[] A, int left, int right) {
        if (A == null) return -1;
        int n = A.length;
        if (n <= 1)                                     // Is "A" an empty array or just s single element array?
            return n == 0 ? -1 : 0;

        int pivot = A[left];
        int i = left - 1;
        int j = right + 1;

        while (true) {

            do
                i++;
            while (A[i] < pivot);

            do
                j--;
            while (A[j] > pivot);

            if (i < j)
                swap(A, j, i);
            else
                return j;
        }

    }

    public static int randomizedHoarePartition(int[] A, int left, int right) {
        int i = random.nextInt(right - left + 1) + left;
        swap(A, i, left);
        return doHoarePartitioning(A, left, right);
    }

    public static void quickSort(int[] A, int left, int right) {
        if (left == right) return;
        int k = randomizedHoarePartition(A, left, right);
        quickSort(A, left, k);
        quickSort(A, k + 1, right);
    }

    // Selection in Expected Linear Time
    public static int smallestKthElement(int[] A, int k, int left, int right) {
        if (A == null)
            throw new NullPointerException("The input array cannot be null");
        int n = A.length;
        if (k <= 0 || k > n || (left == right && k > 1))
            throw new ArrayIndexOutOfBoundsException("'k' must be within the range of 1 <= k <= array length");

        return randomizedSelectHelper(A, k, left, right);
    }

    private static int randomizedSelectHelper(int[] A, int k, int left, int right) {
        if (left == right) return A[left];                          // If A is a single element array
        int pivot = randomizedPartition(A, left, right);
        if (pivot + 1 == k)
            return A[pivot];
        else if (pivot + 1 < k)
            return randomizedSelectHelper(A, k,pivot + 1, right);
        return randomizedSelectHelper(A, k, left, pivot - 1);
    }

}
