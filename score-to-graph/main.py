import networkx as nx
import matplotlib.pyplot as plt

"""
Generates graph from score.
@Author: Nguyen Hoang Nam Anh
"""


def to_graph(score):
    if len(score) == 0:
        print("Score length shall be positive")
    else:

        if is_valid_score(score.copy()):
            print("Drawing...")
            nx.draw(build_graph(score), with_labels=True)
            plt.show()
        else:
            print("Invalid score")


def is_valid_score(score):
    if not score:
        return True
    score.sort()

    if score[-1] >= len(score):
        return False

    u = len(score) - 1
    for i in range(score[-1]):
        v = u - i - 1
        score[v] -= 1
        if score[v] < 0:
            return False

    del score[-1]

    return is_valid_score(score)


def build_graph(score):
    g = nx.Graph()
    g.add_nodes_from(range(len(score)))
    build_graph_helper(score, g, len(score) - 1)

    return g


def build_graph_helper(score, g, u):
    if u == -1:
        return
    v = u
    while score[u] > 0:
        v = first_non_zero_index(score, v - 1)
        g.add_edge(u, v)
        score[u] -= 1
        score[v] -= 1

    return build_graph_helper(score, g, u - 1)


def first_non_zero_index(score, from_id):
    for i in range(from_id, -1, -1):
        if score[i] > 0:
            return i

    return -1


if __name__ == '__main__':
    print("Score 1: ")
    score1 = [2, 3, 3, 3, 3, 3]
    print(score1)
    to_graph(score1)

    print("\nScore 2: ")
    score2 = [1, 1, 1, 2, 2, 3, 4, 5, 5]
    print(score2)
    to_graph(score2)
