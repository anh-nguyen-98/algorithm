public class TreeNodeClient {
    public static void main(String args[]) {
        
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(11);

        // root.inOrderTraversal(root);
        // root.preOrderTraversal(root);

        // System.out.println(root.getMinDepth(root));

        int[] arr = {1, 2, 3, 4};

        TreeNode root1 = TreeNode.buildSearchTree(arr);
        
        root1.preOrderTraversal(root1);
    }




}
