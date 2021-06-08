import java.util.PriorityQueue;

/**
 * Builds tree from father code
 * @author Nguyen Hoang Nam Anh
 */
public class TreeBuilder {

    public static int[][] buildTree(int[] fatherCode){

    Tree tree = new Tree(fatherCode.length+1);
    return tree.buildTree(fatherCode).getMatrix();

    }
}
