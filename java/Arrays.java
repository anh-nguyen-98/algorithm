public class Arrays {
    public static void rotate(int[][] matrix) {
        int n = matrix.length -1;
        int start = 0;
        while (start <= n-start) {
            
            for (int j = start; j < n-start; j++) {
                int top = matrix[start][j];
                int temp = top;

                // save left edge to top edge
                int left = matrix[n-j][start];
                matrix[start][j] = left;

                int bottom = matrix[n-start][n-j];
                matrix[n-j][start] = bottom;

                int right = matrix[j][n-start];
                matrix[n-start][n-j] = right;

                matrix[j][n-start] = temp;

            }
            // next layer
            start +=1;
        }
    }

    /**
     * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, 
     * representing the number of elements in nums1 and nums2 respectively.Merge nums1 and nums2 into a single array 
     * sorted in non-decreasing order. The final sorted array should not be returned by the function, 
     * but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, 
     * where the first m elements denote the elements that should be merged, 
     * and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int prev = m+n-1;
        while (i >= 0 || j >= 0) {
            if (j < 0 || (i >= 0 && nums1[i] >= nums2[j])) {
                nums1[prev] = nums1[i];
                i--;
            }
            else {
                nums1[prev] = nums2[j];
                j--;
            }
            prev--;
        }
    }
    
}
