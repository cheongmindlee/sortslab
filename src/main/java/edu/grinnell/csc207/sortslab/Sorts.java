//package edu.grinnell.csc207.sortslab;

import java.util.Arrays;

//import java.util.Arrays;

/**
 * A collection of sorting algorithms over generic arrays.
 */
public class Sorts {
    /**
     * Swaps indices <code>i</code> and <code>j</code> of array <code>arr</code>.
     * @param <T> the carrier type of the array
     * @param arr the array to swap
     * @param i the first index to swap
     * @param j the second index to swap
     */
    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Sorts the array according to the bubble sort algorithm:
     * <pre>
     * [ unprocessed | i largest elements in order ]
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr) {
        for (int i =arr.length-1; i >= 0; i--){
            T max = arr[i];
            int index = i;
            for(int j =0; j<i; j++){
                if( arr[j].compareTo(max) > 0){
                    max = arr[j];
                    index = j;
                }
            }

            swap(arr, i, index);
        }
    }

    /**
     * Sorts the array according to the selection sort algorithm:
     * <pre>
     * [ i smallest elements in order | unprocessed ]
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] arr) {
        for(int i = 0; i < arr.length; i++){
            T min = arr[i];
            int index = i;
            for(int j = i; j < arr.length; j++){
                if(arr[j].compareTo(min) < 0){
                    min = arr[j];
                    index = j;
                }
            }
            swap(arr, i, index);
        }
    }

    /**
     * Sorts the array according to the insertion sort algorithm:
     * <pre>
     * [ i elements in order | unprocessed ] 
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < i; j++){
                if(arr[i].compareTo(arr[j]) < 0){
                    swap(arr, i, j);
                }
            }
        }
    }

    /**
     * This function will recurvily split the arrays into halves, and call merge on all the halves to sort them
     * @param <T>
     * @param start
     * @param end
     * @param arr
     */
    public static <T extends Comparable<? super T>> void mergeHelper(int start, int end, T[] arr, T[]scratchArr){
        int middle = start + (end-start) / 2;
        if(end-start > 1){
            mergeHelper(start, middle, arr, scratchArr);
            mergeHelper(middle, end, arr, scratchArr);
            merge(start, end, middle, arr, scratchArr);
        }




    }

    public static <T extends Comparable<? super T>> void merge(int start, int end, int middle, T[] arr, T[] scratchArr){
        int startI = start;
        int startJ = middle;
        int index = start;
        //While both of the split arrays are not traversed through
        while(startI < middle && startJ < end){
            if(arr[startI].compareTo(arr[startJ]) <= 0){
                scratchArr[index] = arr[startI];
                startI++;
                index++;
            } else {
                scratchArr[index] = arr[startJ];
                startJ++;
                index++;
            }
        }
        //if one array is not iterated through put the rest of them in the array
        while(startI < middle){
            scratchArr[index] = arr[startI];
            startI++;
            index++;
        }
        while(startJ < end){
            scratchArr[index] = arr[startJ];
            startJ++;
            index++;
        }

        for(int i = 0; i < arr.length; i++){
            arr[i] = scratchArr[i];
        }

    }
    /**
     * Sorts the array according to the merge sort algorithm:
     * <pre>
     * [ sorted | sorted ] -> [ sorted ]
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {
        //T[] scratch = copyOf(arr, arr.length);            Does not work becasue of T[] generic type
        //Copy all elements into a new array - O(n)
        T[] scratch = Arrays.copyOf(arr, arr.length); //(T[]) new Object [arr.length];

        //Hold the middle index of the array
        int middle = arr.length / 2;
        //Sort the left side and rightside
        mergeHelper(0, arr.length, arr, scratch);
        //Sort both arrays
        merge(0, arr.length, middle,arr, scratch);
        //set array to scratc array
        for(int i = 0; i < arr.length; i++){
            arr[i] = scratch[i];
        }
    }
    /**
     * Sorts the array according to the quick sort algorithm:
     * <pre>
     * []
     * </pre>
     * @param <T>
     * @param arr
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
        // TODO: fill me in!
    }
    public static void main(String[] args){
        Integer[] arr = {11, 5, 6, 9, 3, 2, 1, 4};
        mergeSort(arr);
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
    }
}


