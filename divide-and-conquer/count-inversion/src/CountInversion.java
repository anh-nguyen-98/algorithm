import java.util.Arrays;

public class CountInversion {
    public static int sortAndCount(int[] arr){
        if (arr.length == 1){ return 0;}

        int[] lHalf = Arrays.copyOfRange(arr, 0, arr.length/2);
        int[] rHalf = Arrays.copyOfRange(arr, arr.length/2, arr.length);

        int countLInv = sortAndCount(lHalf);
        int countRInv = sortAndCount(rHalf);
        int countSplitInv = mergeAndCount(arr, lHalf, rHalf);

        return countLInv + countRInv + countSplitInv;
    }

    private static int mergeAndCount(int[] arr, int[] lHalf, int[] rHalf){
        int i = 0; // pointer to lHalf index
        int j = 0; // pointer to rHalf index
        int countSplitInv = 0;

        for (int k = 0; k < arr.length; k++){
            if (j >= rHalf.length ||
                    i < lHalf.length && lHalf[i] < rHalf[j]){
                arr[k] = lHalf[i];
                i++;
            } else{
                arr[k] = rHalf[j];
                j++;
                countSplitInv = countSplitInv + (lHalf.length - i);
            }
        }
        return countSplitInv;
    }
}
