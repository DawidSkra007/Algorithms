import java.util.Arrays;
import java.util.Random;

public class Sorts {

    public static void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length - 1;i++) {//traversing the array

            int min = i;
            for (int j = i + 1; j < arr.length;j++) {//finding smallest integer
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
                //two nested for loops so -> O(n^2)
            int tmp = arr[min];//arr[min] = smallest int in array
            arr[min] = arr[i];
            arr[i] = tmp;//now smallest element is first
        }
    }

    public static void insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; ++i) {//traversing the array
            int key = arr[i];//stores current element in 'key'
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {//while the element before key is bigger then change the position of that element
                arr[j + 1] = arr[j];
                j--;//this this will check every position before key
            }//One for loop and nested while loop so -> O(n^2)
            arr[j + 1] = key;//move the element up to make space
        }
    }

    public static void bogoSort(int[] arr){
        while (!rightOrder(arr)) {//checks if the array is sorted if not it randomly sorts it
            randomsort(arr);
        }
    }

    public static void randomsort(int[] arr) {//sorts the array randomly
        Random rnd = new Random();
        for (int i =0; i < arr.length;i++) {
            int swapPos = rnd.nextInt(arr.length);
            int temp = arr[i];
            arr[i] = arr[swapPos];
            arr[swapPos] = temp;
        }
    }
    public static boolean rightOrder(int[] arr) {//checks if array is sorted
        for (int i =0; i < arr.length-1;i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {//function to print array
        for (int p: arr) {
            System.out.print(p + " ");
        }
    }

    public static int[] makeArrayOfSize(int n) {
        int[] arr = new int[n];
        Random rnd = new Random();

        for (int i = 0; i < n;i++) {
            int number = rnd.nextInt(200);
            arr[i] = number;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = makeArrayOfSize(600);
        Stopwatch timer = new Stopwatch();
        insertionSort(arr);
        double time  = timer.elapsedTime();
        printArray(arr);
        StdOut.println("\nelapsed time = " + time);
    }
}
