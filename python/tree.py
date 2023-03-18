class BinaryTreeNode:
    def __init__(self, value):
        self.value = value
        self.left: BinaryTreeNode = None
        self.right: BinaryTreeNode = None

    def to_string(self):
        return str(self.value)

class BinaryTree:
    def __init__(self, root:BinaryTreeNode):
        self.root = root
    
    def to_string(self):
        return self.to_string_helper(self.root)
    def to_string_helper(self, node: BinaryTreeNode):
        if node == None:
            return ""
        return (self.to_string_helper(node.left) + ' ' + node.to_string() + ' ' + self.to_string_helper(node.right)).strip()



    '''
    Input: Binary tree
    Output: List of node list at each depth.
    '''
    def list_of_depths(self):
        result = []
        self.visit_node_at_depth(self.root, 0, result=result)
        return result
    
    def visit_node_at_depth(self, node: BinaryTreeNode, d: int, result: list):
        if node == None:
            return
        if d >= len(result):
            result.append([])
        result[d].append(node) 
        self.visit_node_at_depth(node.left, d+1, result)
        self.visit_node_at_depth(node.right, d+1, result=result)
    
    def list_of_depths_BFS(self):
        result = []
        if self.root:
            children = [self.root]
            while children:
                result.append(children)
                parents = children
                children = []
                for p in parents:
                    if p.left:
                        children.append(p.left)
                    if p.right:
                        children.append(p.right)
        return result
                
    def is_balanced(self):

        return self.get_height(self.root) != -1
    
    def get_height(self, node: BinaryTreeNode):
        if not node:
            return 0
        height_left_subtree = self.get_height(node.left)
        height_right_subtree = self.get_height(node.right)
        if height_left_subtree == -1 or height_right_subtree == -1:
            return -1
        if abs(height_left_subtree - height_right_subtree) > 1:
            return -1
        return max(height_right_subtree, height_left_subtree) + 1

root = BinaryTreeNode(6)
tree = BinaryTree(root=root)

root.left = BinaryTreeNode(3)
# root.right = BinaryTreeNode(10)

root.left.left = BinaryTreeNode(1)
root.left.right = BinaryTreeNode(4)

# root.right.right = BinaryTreeNode(15)

tree2 = BinaryTree(None)

print(tree2.to_string())

result = tree2.list_of_depths()
for d in result:
    d_str = ''
    for node in d:
        d_str += node.to_string() + ' '
    print (d_str)

print(tree.get_height(root))
