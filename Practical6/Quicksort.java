import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Quicksort {
    public static void quickSort(int[] array,int start,int end) {
        int piv;

        if (start < end) {
            piv = partition(array,start,end);//piv is partitioning index
            //sort before piv
            quickSort(array,start,piv - 1);
            //sort after piv
            quickSort(array,piv + 1,end);
        }
    }

    private static int partition(int[] arr,int start,int end) {
        int pivot = arr[end];
        int pi = start;

        for (int i = start; i <= end - 1;i++) {
            if (arr[i] <= pivot) {
                int swapTemp = arr[pi];//swap Array[pi] with A[i]
                arr[pi] = arr[i];
                arr[i] = swapTemp;

                pi++;
            }
        }
        int swapTemp = arr[pi];//swap Array[pi] with Array[end]
        arr[pi] = arr[end];
        arr[end] = swapTemp;

        return pi;
    }

    public static void EnquickSort(int[]arr, int start,int end) {
        int piv;
        if (end <= start + 500) {//if below CUTOFF(500) it will sort array by using insertion sort
            Sorts.insertionSort(arr);
            return;
        }
        shuffle(arr); // shuffling array

        if (start < end) {
            piv = EnPartition(arr,start,end);
            //sort before piv
            EnquickSort(arr,start,piv - 1);
            //sort after piv
            EnquickSort(arr,piv + 1,end);
        }
    }

    public static int[] shuffle(int[] arr) {
        Random random = new Random();

        for (int i = 0;i < arr.length;i++) {
            int randInt = random.nextInt(arr.length);
            int temp = arr[randInt];
            arr[randInt] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    private static int median(int a, int b, int c) {
        if ((a < b && b < c) || (c < b && b < a)) {
            return b;
        } else if ((b < a && a < c) || (c < a && a < b)) {
            return a;
        } else {
            return c;
        }
    }

    private static int EnPartition(int[]arr,int start,int end) {
        int n = median(start,end/2,end); // using the middle element as the pivot
        int pivot = arr[n];
        int pi = start;

        for (int i = start; i <= end - 1;i++) {
            if (arr[i] <= pivot) {
                int swapTemp = arr[pi];//swap Array[pi] with A[i]
                arr[pi] = arr[i];
                arr[i] = swapTemp;

                pi++;
            }
        }
        int swapTemp = arr[pi];//swap Array[i] with Array[end]
        arr[pi] = arr[end];
        arr[end] = swapTemp;

        return pi;
    }
    public static void reverse(int[] array) {
        if (array == null || array.length < 2) {
            return; }
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = Sorts.makeArrayOfSize(10000);
        int size = arr.length - 1;
        Sorts.printArray(arr);
        Sorts.insertionSort(arr);
        reverse(arr); // testing the worst case scenario
        System.out.println("\n");
        Stopwatch timer = new Stopwatch();
        EnquickSort(arr,0,size); //Calling function
        double time  = timer.elapsedTime();
        Sorts.printArray(arr);
        StdOut.println("\nElapsed time = " + time);
    }

}
