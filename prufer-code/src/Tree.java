import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Represents Tree object as 2-d matrix.
 * @author Nguyen Hoang Nam Anh
 */
public class Tree {
    private int[][] matrix;
    private int numEdges;

    Tree(){};

    Tree(int numEdges){
        this.numEdges = numEdges;
        this.matrix = new int[2][numEdges];
    };


    public Tree buildTree(int[] fatherCode){
        Tree tree = new Tree(fatherCode.length +1);
        tree.matrix[1][fatherCode.length] = 0;


        for (int i = 0; i < fatherCode.length; i++){
            tree.matrix[1][i] = fatherCode[i];
        }

        Queue<Integer> sons = new PriorityQueue<>();
        for (int leaf = 1; leaf <= tree.numEdges; leaf++){
            if (!isMissing(tree.matrix[1], 0, leaf)) {
                continue;
            }
            sons.add(leaf);
        }

        return buildTreeHelper(tree, sons, 0);

    }

    private Tree buildTreeHelper(Tree tree, Queue<Integer> sons, int next){
        if (next >= tree.numEdges){
            return tree;
        }
        int smallest_leaf = sons.remove();
        tree.matrix[0][next] = smallest_leaf;
        int father = tree.matrix[1][next];
        if (isMissing(tree.matrix[1], next+1 , father)){
            sons.add(father);
        }
        return buildTreeHelper(tree, sons,next+1);
    }


    private boolean isMissing(int[] fatherCode, int from, int son){
        for (int i = from; i < fatherCode.length; i++){
            if (son == fatherCode[i]){
                return false;
            }
        }
        return true;

    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getNum_edges() {
        return numEdges;
    }

}
