from collections import deque
class Node:
    def __init__(self, vertex=0, next=None):
        self.vertex = vertex
        self.next = next


# class NodeList:
#     def __init__(self):
#         self.root = root
#         self.len = 0

# Graph - adjacency list representation
class Graph:
    def __init__(self, num_nodes=0):
        self.num_nodes = num_nodes
        self.adj = [None] * num_nodes

    def add_edge (self, u, v):
        node = Node(vertex=v)
        nodes = self.adj[u]
        node.next = nodes
        self.adj[u] = node  

        # node = Node(vertex=u)
        # nodes = self.adj[v]
        # node.next = nodes
        # self.adj[v] = node

    def print_graph(self):
        for u in range(self.num_nodes):
            print ("Adjancency list of vertex ", u)
            list = str(u)
            node = self.adj[u]
            while (node != None):
                list += " -> " + str(node.vertex)
                node = node.next
            print (list)

    def bfs(self, root):
        to_visit = deque()
        visited = False * self.num_nodes
        to_visit.append(root)

        while len(to_visit) != 0 :
            u = to_visit.popleft()
            print(u)
            visited[u] = True
            neighbor = self.adj[u]
            while neighbor != None:
                if not visited[neighbor.vertex]:
                    to_visit.append(neighbor.vertex)  
                neighbor = neighbor.next
       
    def dfs(self, root):
        if root >= self.num_nodes: return
        visited = [False] * self.num_nodes
        self.dfs_helper(root=root, visited=visited)
        
    def dfs_helper(self, root, visited):
        visited[root] = True
        neighbor = self.adj[root]
        while (neighbor != None):
            if (not visited[neighbor.vertex]):
                print (neighbor.vertex)
                self.dfs_helper(neighbor.vertex, visited)
            
            neighbor = neighbor.next




# graph = Graph(4)

# graph.add_edge(0, 1)
# graph.add_edge(0, 2)
# graph.add_edge(2, 0)
# graph.add_edge(3, 3)
# graph.add_edge(2, 3)
# graph.print_graph()


# graph.bfs(2)

graph = Graph(5)
graph.add_edge(0, 1)
graph.add_edge(1, 2)
graph.add_edge(1, 3)
graph.add_edge(2, 4)
# graph.print_graph()
graph.dfs(0)