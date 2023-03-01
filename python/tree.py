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


root = BinaryTreeNode(6)
tree = BinaryTree(root=root)

root.left = BinaryTreeNode(3)
root.right = BinaryTreeNode(10)

root.left.left = BinaryTreeNode(1)
root.left.right = BinaryTreeNode(4)

root.right.right = BinaryTreeNode(15)

tree2 = BinaryTree(None)

print(tree2.to_string())

result = tree2.list_of_depths()
for d in result:
    d_str = ''
    for node in d:
        d_str += node.to_string() + ' '
    print (d_str)

