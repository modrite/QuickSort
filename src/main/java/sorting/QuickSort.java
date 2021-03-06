package sorting;

import java.util.Random;

public class QuickSort {

    private final long[] data;
    private int len;

    // create int array
    public QuickSort(int max) {
        data = new long[max];
        len = 0;
    }

    // init
    public void quickSort() {
        recursiveQuicksort(0, len - 1);
    }

    // recursive quicksort algorithm
    public void recursiveQuicksort(int left, int right) {
        int size = right - left + 1;
        if (size >= 3) {
            long median = medianOf3(left, right);    // determine partition
            int partition = partitionIterator(left, right, median);  // create partition
            recursiveQuicksort(left, partition - 1);    // sort left partition
            recursiveQuicksort(partition + 1, right);   // sort right partition
        } else {
            simpleSort(left, right);
        }
    }

    // to determine location of the pivot ->
    public long medianOf3(int left, int right) {
        int center = (left + right) / 2;
        if (data[left] > data[center])
            swap(left, center);
        if (data[left] > data[right])
            swap(left, right);
        if (data[center] > data[right])
            swap(center, right);
        swap(center, right - 1);
        return data[right - 1];     // return pointer to location of median
    }

    public int partitionIterator(int left, int right, long pivot) {
        int leftIndex = left;
        int rightIndex = right - 1;

        while (true) {
            while (data[++leftIndex] < pivot) // scan for larger int
                ;
            while (data[--rightIndex] > pivot)  // scan for smaller int
                ;
            if (leftIndex >= rightIndex) // pointers crossed -> break
                break;
            else
                swap(leftIndex, rightIndex); // pointers never crossed -> swap elements
        }
        swap(leftIndex, right - 1); // restore pivot
        return leftIndex; // return pivot location
    }

    // for partitions with <= 3 elements
    public void simpleSort(int left, int right) {
        int size = right - left + 1;
        if (size <= 1)
            return;
        if (size == 2) {
            if (data[left] > data[right])
                swap(left, right);
        } else {
            if (data[left] > data[right - 1])
                swap(left, right - 1);
            if (data[left] > data[right])
                swap(left, right);
            if (data[right - 1] > data[right])
                swap(right - 1, right);
        }
    }

    public void swap(int index1, int index2) {
        long temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }

    // to build an array
    public void insert(long value) {
        data[len] = value;
        len++;
    }

    // to display data
    public void display() {
        for (int j = 0; j < len; j++)
            System.out.print(data[j] + " ");
        System.out.println("");
    }

    // main method
    public static void main(String[] args) {

        Random rand = new Random(); // to be able to generate random numbers
        int[] array = new int[150]; // here you can select how many numbers you want to print

        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(999);
        }

        QuickSort arr = new QuickSort(array.length); // init array

        int n;
        // convert and fill array
        for (int j : array) {
            n = j;
            arr.insert(n);
        }

        System.out.println("\nUnsorted numbers: ");
        // display unsorted numbers (the original array)
        arr.display();

        // Start Timer
        long firstNs = System.nanoTime();

        // QuickSort execute
        arr.quickSort();

        // Stop Timer
        long lastNs = System.nanoTime();

        System.out.println("\nSorted numbers: ");
        // display sorted numbers
        arr.display();

        long timeNs = lastNs - firstNs;
        System.out.println("\nIn order to sort data in ascending order using a QuickSort algorithm on an array of " + array.length +
                " unordered numbers required the following amount of time: " + timeNs + " nanoseconds");
    }

}
