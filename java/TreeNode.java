/**
 * Normal binary tree (not binary search tree)
 */
public class TreeNode {
    private int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    /**
     * In-order traversal
     */
    public void inOrderTraversal(TreeNode node) {
        if (node != null) {
            // print left node
            inOrderTraversal(node.left);
            // print current node
            System.out.println(node.val);
            // print right node
            inOrderTraversal(node.right);
        }
       
    }

    public void preOrderTraversal(TreeNode node) {
        if (node != null) {
            System.out.println(node.val);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }
    public void postOrderTraversal(TreeNode node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.println(node.val);
        }
    }

    public int getMinDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null) {
            return 1 + getMinDepth(node.right);
        }
        if (node.right == null) {
            return 1 + getMinDepth(node.left);
        }

        return 1 + Math.min(getMinDepth(node.left), getMinDepth(node.right));
    }

    public static TreeNode buildSearchTree(int[] array) {
        if (array.length == 0) {
            return null;
        }
        return buildSearchTreeHelper(array, 0, array.length -1);
    }

    private static TreeNode buildSearchTreeHelper(int[] array, int lo, int hi) {
        if (lo > hi) {
            return null;
        }

        int mid = (hi + lo) / 2;
        TreeNode node = new TreeNode(array[mid]);
        node.left = buildSearchTreeHelper(array, lo, mid -1);
        node.right = buildSearchTreeHelper(array, mid+1, hi);
        return node;
    }
}


