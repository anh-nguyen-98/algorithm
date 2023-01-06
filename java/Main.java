import java.util.ArrayList;
import java.util.List;


public class Main {
    public static int pushtoback(List<Integer> nums) {
        // Write your code here
        
        List<Integer> LIS =  new ArrayList<>(nums.size());
        int maxLen = 0;
        for (int i = 0; i < nums.size(); i++) {
            int maxPrefixLen = 0;
            for (int j = 0; j < i; j++) {
                if (nums.get(j) < nums.get(i)) {
                    maxPrefixLen = Math.max(maxPrefixLen, LIS.get(j));
                }
            }
            LIS.add(i, 1 + maxPrefixLen);
            maxLen = Math.max(maxLen, LIS.get(i));
        }
        
        return nums.size() - maxLen;
    }
    public static int trickortreat(List<Integer> nums) {
        // Write your code here
            if (nums.size() == 1) {
                return nums.get(0);
            }
            if (nums.size() == 2) {
                return Math.max(nums.get(0), nums.get(1));
            }
            int optimal1 = nums.get(1);
            int optimal2 = nums.get(0);
            
            for (int i = 2; i < nums.size(); i++) {
                int temp = Math.max(nums.get(i) + optimal2, optimal1);
                optimal2 = optimal1;
                optimal1 = temp;
            }
            
            return optimal1;
        }
    
    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(4);
        nums.add(2);
        nums.add(5);
        nums.add(3);

        System.out.println(pushtoback(nums));

        List<Integer> candies = new ArrayList<>();
        candies.add(1);
        candies.add(1);

        candies.add(3);
        candies.add(2);
        candies.add(1);
        candies.add(4);

        System.out.println(trickortreat(candies));

        



    }

}
