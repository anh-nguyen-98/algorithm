def explore(node, graph, visited):
    visited[node] = True
    print (node)
    for child in graph[node]:
        if not visited[child]:
            explore(child, graph, visited)


def traverse_graph(graph):
    num_nodes = len(graph)
    visited = [False] * num_nodes
    for node in range(num_nodes):
        if not visited[node]:
            explore(node, graph, visited)

'''
company_name, isLCA
'''
def search(company, p, q, graph, visited):
    visited[company] = True
    children = graph[company]
    if company == p or company == q:
        for child in children:
            company_name, _ = search(child, p, q, graph, visited)
            if company_name:
                return company, True
        return company, False

    result = [None, False]
    for child in children:
        company_name, is_LCA_found = search(child, p, q, graph, visited)
        if is_LCA_found:
            return company_name, is_LCA_found
        if company_name:
            if not result[0]:
                result[0] = company_name
            else:
                result[0] = company
                result[1] = True
    return result

# C, R, Q = map(int, input().split())
# C: number of companies
# R: number of relationships
# Q: number of questions

with open('data.txt', 'r') as f:
    first_line = f.readline()
    company_count, relationship_count, question_count = map(int, first_line.split())

    graph = [[] for _ in range(company_count +1)]
    for _ in range(relationship_count):
        line = f.readline()
        parent, child = map(int, line.split())
        graph[parent].append(child)
    print (graph)
    for _ in range(3):
        first_question = f.readline()
    p, q = map(int, first_question.split())
    print (f'p: {p}\nq: {q}')

    visited = [False for _ in range(company_count + 1)]
    for company in range(company_count + 1):
        if not visited[company]:
            company_name, is_LCA_found = search(company, p, q, graph, visited)
            if is_LCA_found:
                print ('yes')
    #         if search(company, p, q, graph, visited):
    #             print ('YES')
    # print ('NO')



    


# traverse_graph(graph)
# if __name__ == '__main__':
    # C, R, Q = map(int, input().split())
    # C: number of companies
    # R: number of relationships
    # Q: number of questions
    # graph = [[] for _ in range(C +1)]
    
    # for _ in range(R):
    #     parent, child = map(int, input().split()) # parent, child
    #     graph[parent].append(child)
    # traverse_graph(graph)
    
    # for _ in range(Q):
    #     company_x, company_y = map(int, input().split())
    #     is_same_group(company_x, company_y)