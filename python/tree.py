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

    def common_ancestor(self, root: BinaryTreeNode, p: BinaryTreeNode, q: BinaryTreeNode):
        '''
        Result: [node (LCA/p/q/None), isAncestor]
        '''
        class Result:
            def __init__(self, node=None, is_ancestor=False) -> None:
                self.node: BinaryTreeNode = node
                self.is_ancestor: bool = is_ancestor
        
        def common_ancestor_helper(root: BinaryTreeNode, p: BinaryTreeNode, q: BinaryTreeNode):
            if root is None:
                return Result()

            if root.value == p.value or root.value == q.value:
                result_x: Result = common_ancestor_helper(root.left, p, q)
                if result_x.node is not None:
                    return Result(root, True)
                result_y: Result = common_ancestor_helper(root.right, p, q)
                if result_y.node is not None:
                    return Result(root, True)
                return Result(root, False)
            result_x: Result = common_ancestor_helper(root.left, p, q)
            if result_x.is_ancestor:
                return result_x
            result_y: Result = common_ancestor_helper(root.right, p, q)
            if result_y.is_ancestor:
                return result_y
            if result_x.node is not None and result_y.node is not None:
                return Result(root, True)
            if result_x.node is None and result_y.node is None:
                return Result()
            node = result_x.node if result_x.node is not None else result_y.node
            return Result(node, False)
        
        result: Result = common_ancestor_helper(root, p, q)
        return result.node
        
        

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
                
            if root.value == p or root.value == q:
                # search for the other in tree
                other = q if root.value == p else p
                is_found, LCA = self.findNode(root, [other])
                if is_found[0]:
                    return [True, True], root
                return [True, False], None
            
            else:
                # search 2 values in left branch
                is_found, LCA = self.findNode(root.left, unfound_nodes)
                is_p_found = is_found[0]
                is_q_found = is_found[1]

                if is_p_found and is_q_found:
                    return is_found, LCA
                if not is_p_found and not is_q_found:
                    return self.findNode(root.right, unfound_nodes)
                
                missing = q if is_p_found else p
                is_found, LCA = self.findNode(root.right, [missing])
                if is_found[0]:
                    return [True, True], root
                return [True, False], None              
            
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

# print (tree.lowestCommonAncestor(5, 1))
# root.right.right = BinaryTreeNode(15)
print (tree.common_ancestor(root, BinaryTreeNode(5), BinaryTreeNode(1)))
