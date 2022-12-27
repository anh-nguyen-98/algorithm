# This is a sample Python script.
from divide_conquer import quick_sort

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.


# def print_hi(name):
#     # Use a breakpoint in the code line below to debug your script.
#     print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.
#
from dp import *


def main():
    # points = [(2, 3), (12, 30), (40, 50), (5, 1), (12, 10), (3, 4)]
    #
    # points = []
    # quick_sort(points, -1, -1)
    # print (points)
    # prices = [1, 3, 4, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 24, 25]
    # max_value(20, prices)

    # for i in range (1, 26):
    #     max_value_greedy(i, prices)
    # max_value_greedy(4, prices)
    v = [5, 30, 50, 40]
    w = [1, 3, 2, 2]

    # print(knapsack(w, v, 5))

    points = [-1.2,-1.0,-0.2,0.1,0.2,0.4,1.6,1.7,1.9,2.0]
    distance(points=points)
    # for i in range(1, len(points)):
    #     print (f'[{points[i]}, {points[i-1]}]: {round(abs(points[i] - points[i-1]),2)}')



def distance(points):
    dist = [[0 for j in range(len(points))] for i in range(len(points))]
    header = "    "
    for i in range(len(points)):
        header += f'{points[i]:.2f}\t'
    print (header)
    print ()
    for i in range(len(points) -1):
        for j in range (i+1, len(points)):
            d = abs(points[i] - points[j])
            dist[i][j] = d
            dist[j][i] = d
    
    for i in range(len(points)):
        row = f'{points[i]}: '
        for j in range(len(points)):
            row +=  f'{dist[i][j]:.2f}\t'
        print (row)

    
# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    main()
# See PyCharm help at https://www.jetbrains.com/help/pycharm/
