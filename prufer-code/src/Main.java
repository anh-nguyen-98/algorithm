import java.util.Arrays;

/**
 * @author Nguyen Hoang Nam Anh
 */
public class Main {

    public static void main (String[] args){
        int[] fatherCode = {6, 0, 2, 6, 2, 9, 9, 2};
        System.out.println("Father code: ");
        System.out.println(Arrays.toString(fatherCode));

        int[][] ret = TreeBuilder.buildTree(fatherCode);
        System.out.println("\nTree: ");
        System.out.println(Arrays.deepToString(ret));

        System.out.println();
        System.out.println();

        int[] fatherCode2 = {};
        System.out.println("Father code 2: ");
        System.out.println(Arrays.toString(fatherCode2));

        int[][] ret2 = TreeBuilder.buildTree(fatherCode2);
        System.out.println("\nTree 2: ");
        System.out.println(Arrays.deepToString(ret2));

        System.out.println();
        System.out.println();

        int[] fatherCode3 = {1, 2, 3, 4};
        System.out.println("Father code 3: ");
        System.out.println(Arrays.toString(fatherCode3));
        int[][] ret3 = TreeBuilder.buildTree(fatherCode3);
        System.out.println("\nTree 3: ");
        System.out.println(Arrays.deepToString(ret3));
    }
}
