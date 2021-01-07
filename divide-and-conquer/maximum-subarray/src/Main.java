
public class Main {
    public static void main(String[] args){
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        mSA ret = MaximumSubarray.findMaxSubarray(nums, 0, nums.length-1);
        System.out.println(ret.sum);

        int[] nums2  = new int[]{1};
        mSA ret2 = MaximumSubarray.findMaxSubarray(nums2, 0, nums2.length-1);
        System.out.println(ret2.sum);
    }
}
