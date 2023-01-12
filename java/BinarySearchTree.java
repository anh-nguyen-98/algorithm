public class BinarySearchTree{
    private class TreeNode{
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value){
            this.value = value;
            this.left = null;
            this.right = null;
        }
        public int get(){
            return this.value;
        }
    }

    private TreeNode root;
    public BinarySearchTree(){
        this.root = null;
    }

    /**
     * Adds key to tree
     */
    public void add (int value){
        this.root = add(value, this.root);
    }

    private TreeNode add (int value, TreeNode currentNode){
        if (currentNode == null){
            TreeNode node = new TreeNode (value);
            return node;
        }
        if (currentNode.value < value){
            currentNode.right = add(value, currentNode.right);
        }
        if (currentNode.value > value){
            currentNode.left = add(value, currentNode.left);
        }
        return currentNode;
    }

    public boolean contains (int value){
        return contains(value, this.root);
    }
    private boolean contains (int value, TreeNode currentNode){
        if (currentNode == null){
            return false;
        }
        if (currentNode.value == value){
            return true;
        }

        if (currentNode.value > value){
            return contains(value, currentNode.left);
        }
        return contains (value, currentNode.right);
    }


    public void remove (int value){
        this.root = remove(value, this.root);
        return;
    
    }
    private TreeNode remove(int value, TreeNode node){
        if (node.value == value){
            return removeHelper(node);
        }
        if (node.value < value){
            node.right = remove(value, node.right);
        }
        node.left = remove(value, node.left);
        return node;
    }

    private TreeNode removeHelper(TreeNode node){
        if (node.left == null && node.right == null){
            return null;

        }
        if (node.left != null && node.right == null){
            return node.left;
        }
        if (node.left == null && node.right != null){
            return node.right;
        }
        return min(node.right);
    }


    public int min(){
        if (this.root == null){
            throw new IllegalArgumentException("Empty tree");
        }
        int min = min(this.root).value;
        return min;
    }

    private TreeNode min(TreeNode currentNode){
        if (currentNode.left == null){
            return currentNode;
        }
        return min(currentNode.left);
    }

    /**
     * in-order traversal
     */
    public String toString(){
        String repString = "[";
        repString += toString(this.root);
        repString = repString.substring(0, repString.length()-2) + "]";
        return repString;
    }

    private String toString(TreeNode currentNode){
        if (currentNode == null){
            return "";
        }
        return toString(currentNode.left) + currentNode.value + ", "
        + toString(currentNode.right);

    }
}