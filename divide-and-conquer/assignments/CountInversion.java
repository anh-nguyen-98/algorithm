package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CountInversion {
    public static int countInv (List<Integer> nums){
        // base case:
        if (nums.size() == 1){
            return 0;
        }

        // recursive call 1: left half
        List<Integer> leftNums = nums.subList( 0, nums.size()/2 );
        int leftInv = countInv(leftNums);

        // recursive call 2: right half
        List<Integer> rightNums = nums.subList( nums.size()/2, nums.size());
        int rightInv = countInv(rightNums);

        // combine: merge & count split inv
        int splitInv = countSplitInv(nums, leftNums, rightNums);

        return leftInv + rightInv + splitInv;
    }

    private static int countSplitInv(List<Integer> nums, List<Integer> leftNums, List<Integer> rightNums){
        // implement merge & count
        int i = 0;
        int j = 0;
        int splitInv = 0;

        for (int k = 0; k < nums.size(); k++){
            if (i >= leftNums.size() ||
                    j < rightNums.size() && rightNums.get(j) < leftNums.get(i)){
                nums.set(j, rightNums.get(j));
                splitInv += leftNums.size() - i;
                j++;
            }
            else{
                nums.set(k, leftNums.get(i));
                i++;
            }
        }
        return splitInv;
    }
    public static void main (String[] args) throws FileNotFoundException {
        File file = new File("D:\\algorithm\\algorithm\\divide-and-conquer\\assignments\\IntegerArray.txt");
        Scanner scanner = new Scanner(file);

        List<Integer> nums = new ArrayList<>();
        while (scanner.hasNextLine()){
            nums.add(Integer.parseInt(scanner.nextLine()));
        }
        System.out.println(countInv(nums));
    }
}
