import java.util.Arrays;

public class MergeSort {
    /**
     * divide-and conquer
     * 1. divide
     * 2. conquer each subarray as same as the original arr (recursively)
     * 3. combine solutions from each subarr
     * @param arr
     * @return
     */
    public static int[] sort(int[] arr) {
        // base case: not divide
        if (arr.length <= 1) return arr;
        
        // divide
        int mid = arr.length/2;
        int[] leftHalf = Arrays.copyOfRange(arr, 0, mid);
        int[] rightHalf = Arrays.copyOfRange(arr, mid, arr.length);

        // solve subproblems recursively
        leftHalf = sort(leftHalf);
        rightHalf = sort(rightHalf);

        // combine

        int i = 0; 
        int j = 0;
        int k = 0;

        while (i < arr.length) {
            if (k >= rightHalf.length 
            || j < leftHalf.length && leftHalf[j] <= rightHalf[k]) {
                arr[i] = leftHalf[j];
                j++;
            } else {
                arr[i] = rightHalf[k];
                k++;
            }
            i++;
        }
        leftHalf = null;
        rightHalf = null;
      
        return arr;
    }
}
