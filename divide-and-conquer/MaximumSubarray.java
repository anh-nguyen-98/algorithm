import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Implementation of divide-and-conquer strategy
 */

public class MaximumSubarray {
	
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    private static class mSA {
        public int start; // start index of MSA
        public int end; // end index of MSA (inclusive)
        public int sum;  // sum of MSA
    }

    /**
     * Recursive call to find maximum subarray of an array.
     * @param array array
     * @param start start index of array
     * @param end end index of array (inclusive)
     * @return
     */
    public static mSA findMaxSubarray(int[] array, int start, int end){
        // base case
        if (start == end){
            return new mSA(start, end, array[start]);
        }
        // divide
        int mid = (start + end)/2;

        // conquer
        mSA leftMSA = findMaxSubarray(array, start, mid);
        mSA rightMSA = findMaxSubarray(array, mid+1, end);
        mSA crossMSA = findMaxCrossSubarray(array, start, end, mid);

        // combine
        if (leftMSA.getSum() >= rightMSA.getSum()
                && leftMSA.getSum() >= crossMSA.getSum()){
            return leftMSA;
        } else if (rightMSA.getSum() >= leftMSA.getSum()
                && rightMSA.getSum() >= crossMSA.getSum()){
            return rightMSA;
        }
        return crossMSA;
    }

    /**
     * Returns maximum subarray across mid point of array.
     * @param array array
     * @param start start index
     * @param end end index
     * @param mid mid index
     * @return maximum subarray across mid point
     */
    private static mSA findMaxCrossSubarray(int[] array, int start, int end, int mid){

        // finds mLow & leftSum:
        int crossLeftSum = Integer.MIN_VALUE;
        int leftSum = 0;
        int crossStart = 0;
        for (int i = mid; i >= start; i--){
            leftSum += array[i];
            if (leftSum > crossLeftSum){
                crossLeftSum = leftSum;
                crossStart = i;
            }
        }
        // finds mH & rightSum:
        int crossRightSum = Integer.MIN_VALUE;
        int rightSum = 0;
        int crossEnd = 0;
        for (int j = mid+1; j <= end; j++){
            rightSum += array[j];
            if (rightSum > crossRightSum){
                crossRightSum = rightSum;
                crossEnd = j;
            }
        }
        // combines
        return new mSA(crossStart, crossEnd, crossLeftSum + crossRightSum);
    }

    public static void main(String[] args){
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        mSA ret = MaximumSubarray.findMaxSubarray(nums, 0, nums.length-1);
        System.out.println(ret.sum);

        int[] nums2  = new int[]{1};
        mSA ret2 = MaximumSubarray.findMaxSubarray(nums2, 0, nums2.length-1);
        System.out.println(ret2.sum);
    }	
}
