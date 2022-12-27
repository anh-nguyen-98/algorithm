import java.util.HashMap;
import java.util.Map;


public class PrefixSum {
    
    public static void main(String args[]) {

        int[] nums = {1,-1,0};
        int k = 0;

        System.out.println(subarraySum(k, nums));
    }

    public static int subarraySum(int k, int[] nums) {
        int[] prefixSum = new int[nums.length];

        Map<Integer, Integer> sumFrequencies = new HashMap<Integer, Integer>();
        prefixSum[0] = nums[0];
        sumFrequencies.put(nums[0], 1);
        int count = 0;
        if (prefixSum[0] == k) {
            count = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i-1] + nums[i]; 

            if (prefixSum[i] == k) {
                count += 1;
            }
            int prefixSumJ = prefixSum[i] - k;
            if (sumFrequencies.containsKey(prefixSumJ)) {
                count += sumFrequencies.get(prefixSumJ);

            }
            
            if (!sumFrequencies.containsKey(prefixSum[i])) {
                sumFrequencies.put(prefixSum[i], 1);
            } else {
                sumFrequencies.put(prefixSum[i], sumFrequencies.get(prefixSum[i]) +1);
            }

          
           
        }

        return count;
    }
}
