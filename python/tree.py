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
    
    def lowestCommonAncestor(self, p, q):
        _, LCA = self.findNode(root, [p, q])
        return LCA.value

    def findNode(self, root: BinaryTreeNode, unfound_nodes: list):
        unfound_nodes_count = len(unfound_nodes)
        if unfound_nodes_count == 2:
            p = unfound_nodes[0]
            q = unfound_nodes[1]
            if root is None:
                return [False, False], None
            if root.value == p:
                # search for q in tree
                is_found, LCA = self.findNode(root, [q])
                if is_found[0]:
                    return [True, True], root
                return [True, False], None
            
            elif root.value == q:
                is_found, LCA = self.findNode(root, [p])
                if is_found[0]:
                    return [True, True], root
                return [False, True], None
            else:
                # search 2 values in left branch
                is_found, LCA = self.findNode(root.left, unfound_nodes)
                is_p_found = is_found[0]
                is_q_found = is_found[1]

                if is_p_found and is_q_found:
                    return is_found, LCA
                if is_p_found and not is_q_found:
                    # search q in right branch
                    is_found, LCA = self.findNode(root.right, [q])
                    if is_found[0]:
                        return [True, True], root
                    return [True, False], None
                if not is_p_found and is_q_found:
                    is_found, LCA = self.findNode(root.right, [p])
                    if is_found[0]:
                        return [True, True], root
                    return [False, True], None
                return self.findNode(root.right, unfound_nodes)
            
        unfound_node = unfound_nodes[0]
        if root is None:
            return [False], None
        if root.value == unfound_node:
            return [True], None
        # search node in the left branch
        is_found, LCA = self.findNode(root.left, unfound_nodes)
        if is_found[0]:
            return is_found, LCA
        return self.findNode(root.right, unfound_nodes)

root = BinaryTreeNode(3)
tree = BinaryTree(root=root)

root.left = BinaryTreeNode(5)
root.right = BinaryTreeNode(1)

root.left.left = BinaryTreeNode(6)
root.left.right = BinaryTreeNode(2)

root.left.right.left = BinaryTreeNode(7)
root.left.right.right = BinaryTreeNode(4)

root.right.left = BinaryTreeNode(0)
root.right.right = BinaryTreeNode(8)

print (tree.to_string())

print (tree.lowestCommonAncestor(5, 1))
# root.right.right = BinaryTreeNode(15)
