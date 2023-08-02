import java.util.Arrays;

public class EfficientSorts {

    public static void merge(int[] array, int low, int mid , int high) {

        int[] leftArray = new int[mid - low + 1];
        int[] rightArray = new int[high - mid];

        for (int i = 0; i < leftArray.length; i++) {
            leftArray[i] = array[low + i];
        }
        for (int i = 0; i < rightArray.length; i++) {
            rightArray[i] = array[mid + i + 1];
        }

        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = low; i < high + 1; i++) { // copying both arrays back to 'array'
            if (leftIndex < leftArray.length && rightIndex < rightArray.length) { // if both sub arrays havent yet been copied
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < leftArray.length) { // if right sub array has been copied
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < rightArray.length) { // if left sub array has been copied
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }

    public static void sort(int[] array, int low, int high) {
        if (low < high) {
            int mid = (low+high)/2;
            sort(array, low, mid);//sorts first half of array
            sort(array , mid+1, high);//sorts second half of array

            merge(array, low, mid, high);//merges the two sorted arrays
        }
    }

    public static void sortEn(int[] array, int low, int high) {
        if (high <= low + 100) {//if below CUTOFF(10) it will sort array by using insertion sort
            //System.out.println("here");
            Sorts.insertionSort(array);
            return;
        }

        int mid = (low + high) / 2;

        if (array[mid] <= array[mid + 1]) {//checks if the array is already sorted
            return;
        }
        if (low < high) {
            mid = (low+high)/2;
            sort(array, low, mid);//sorts first half of array
            sort(array , mid+1, high);//sorts second half of array

            merge(array, low, mid, high);//merges the two sorted arrays
        }
    }

    public static void main(String[] args) {
        int[] arr = Sorts.makeArrayOfSize(100000);
        Stopwatch timer = new Stopwatch();
        //sort(arr,0, arr.length-1);
        sortEn(arr,0, arr.length-1);
        //Sorts.insertionSort(arr);
        double time  = timer.elapsedTime();
        Sorts.printArray(arr);
        StdOut.println("\nelapsed time = " + time);
    }
}
