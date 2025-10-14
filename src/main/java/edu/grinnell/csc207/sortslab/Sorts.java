package edu.grinnell.csc207.sortslab;

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
        
        //set array to scratc array
        for(int i = 0; i < arr.length; i++){
            arr[i] = scratch[i];
        }
    }

    // public static <T extends Comparable<? super T>> void quickSortHelper2(int start, int end, T[] arr){
    //     int middle = start + (end-start) / 2;
    //     if(end-start > 1){
    //         quickSortHelper(arr, start, middle);
    //         quickSortHelper(arr, middle, end);
    //     }
    // }

    // { 1, 2, 3, 0}
    // {0, 2,}
    public static <T extends Comparable<? super T>> void quickSortHelper(T[] arr, int start, int end){
        //Calculate pivot value
        T pivotVal = arr[end-1];
        int startInd = start;
        int endInd = end - 1;
        while(startInd != endInd){
            if(arr[startInd].compareTo(pivotVal) >= 0){
                if(arr[endInd].compareTo(pivotVal) < 0){
                    swap(arr, startInd, endInd);
                     startInd++;
                     endInd--;
                } else {
                    endInd--;
                }
            } else {
            startInd++;
            }
            
        }

        swap(arr, startInd, end-1);
        if(end - start > 1){
            quickSortHelper(arr, start, start + (end-start) / 2);
            quickSortHelper(arr, start + (end-start) / 2, end);
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
        quickSortHelper(arr, 0, arr.length);
    }


    public static void main(String[] args){
        Integer[] arr = { 1, 2, 3, 0, 3 ,5 ,7, 2};
        quickSort(arr);
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
    }
}


