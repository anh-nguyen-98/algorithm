import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class SortClient {
    private static int[] randomIntArr (int maxLen, int maxVal) {
        Random rand = new Random();
        int n = rand.nextInt(maxLen);
        int[] arr = new int[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = rand.nextInt(maxVal);
        }
        return arr;
    }

    private static List<Integer> createListFromFile (String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            List<Integer> lst = new ArrayList<>();
            while (scanner.hasNextInt()) {
                lst.add(scanner.nextInt());
            }
            return lst;
        } catch (FileNotFoundException e) {
            throw new Error ("File not found");
        }
    } 

    public static void main (String[] args) {
        int[] test = randomIntArr(20, 100);
        System.out.println ("input: " + Arrays.toString(test));
        System.out.println ("output: " + Arrays.toString(MergeSort.sort(test)));
        String fileName = "QuickSort.txt";
        List<Integer> lst = createListFromFile(fileName);
        System.out.println("Number of comparisons in QuickSort: ");
        System.out.println("- First element is chosen as pivot: " + QuickSort.count(lst, "first"));
        lst.clear();
        lst = createListFromFile(fileName);

        System.out.println("- Final element is chosen as pivot: " + QuickSort.count(lst, "final"));
        lst.clear();



    }
}
