

import java.util.Arrays;

import data_structure.array.BasicArraySort.BasicArraySort;

public class ProblemRunMain {
    public static void main(String[] args) {
        int[] arr1 = {1,-5,2,4,3};
        int[] arr2 = {2,1,1,3,2,5,4};
        int[] arr3 = {6,1,7};


        System.out.println( Arrays.toString(BasicArraySort.solution(arr1)));
        System.out.println( Arrays.toString(BasicArraySort.solution(arr2)));
        System.out.println( Arrays.toString(BasicArraySort.solution(arr3)));
    }
}
