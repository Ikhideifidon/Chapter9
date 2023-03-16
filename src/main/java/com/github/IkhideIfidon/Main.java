package com.github.IkhideIfidon;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] A = {4, 3, 8, 7, 1, 2, 5, 6, 2};
//        System.out.println(Arrays.toString(OrderStatistics.minMax(A)));
//        System.out.println(OrderStatistics.randomizedHoarePartition(A, 0, A.length - 1));
        OrderStatistics.quickSort(A, 0, A.length - 1);
        System.out.println(Arrays.toString(A));

        int[] B = {6, 19, 4, 12, 14, 9, 15, 7, 8, 11, 3, 13, 2, 5, 10};
        System.out.println(OrderStatistics.smallestKthElement(B, (B.length + 1)/2, 0, B.length - 1));

    }
}