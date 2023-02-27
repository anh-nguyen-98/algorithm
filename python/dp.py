"""
Rod cutting problem.
Find maximum value a rod can be sold.
Input: prices, rod length.
Output: maximum value
"""
import math


def max_value(rod_length, prices):
    best_sell = [0] * (rod_length +1)
    for n in range(1, rod_length +1):
        # find best_sell for rod length n
        for i in range(1, n+1):
            val = prices[i] + best_sell[n-i]
            best_sell[n] = max(best_sell[n], val)
        print (f'{n}: {best_sell[n]}' )

    return best_sell[rod_length]


def max_value_greedy(rod_length, prices):
    average = {}
    for i in range(len(prices)):
        average[i] = prices[i]/ (i+1)
    t = rod_length
    average = dict(sorted(average.items(), key=lambda x: x[1], reverse=True))
    # print(average)
    max_value = 0
    while rod_length > 0:
        for length, price in average.items() :
            if (length +1) <= rod_length:
                max_pieces = math.floor(rod_length / (length +1))
                max_value += max_pieces * prices[length]
                rod_length %= length +1
                break
    print (f'{t}: {max_value}')

"""
Bottom up fashion
w: array of weights of n items
v: array of values of n items
W: weight constraint
"""
def knapsack(weights, v, W):
    
    n = len (weights)
    # initialize B[1...n][w_1...w_n], where B[k][w] is the maximum value of items such that total weight<= w
    B = [[0 for j in range(W+1)] for i in range(n)]
    repr = "k = 1: "
    for w in range (W+1):
        if weights[0] <= w:
            B[0][w] = v[0]
        repr += f'{B[0][w]} '
    print(repr)

    for k in range (1, n):
        repr = f'k = {k+1}: '
        for w in range(W +1):
            if weights[k] > w:
                B[k][w] = B[k-1][w]
            else:
                B[k][w] = max(B[k-1][w], v[k] + B[k-1][w - weights[k]])
            repr += f'{B[k][w]} & '

        print (repr)
        print()

    
    c = W
    result = []
    for k in range (n-1, -1, -1):
        if B[k][c] == B[k -1][c - weights[k]] + v[k]:
            result.append(k)
            c = c - weights[k]
        

    print (result)     
    return B[n-1][W] 



weights = [2, 3, 5, 6, 7, 9]
v = [7, 11, 18, 22, 25, 28]
W = 18

# knapsack(weights=weights, v=v, W=W)

def max_subarray (nums):
    gl = {
        'max': nums[0],
        'start': 0,
        'end': 0
    }
    curr = {
        'max': nums[0],
        'start': 0,
        'end': 0
    }

    for i in range(1, len(nums)):
        if nums[i] <= nums[i] + curr['max']:
            curr['max'] = nums[i] + curr['max']
            curr['end'] = i
        else:
            curr['max'] = nums[i]
            curr['start'] = i
            curr['end'] = i
        
        if curr['max'] > gl['max']:
            gl['max'] = curr['max']
            gl['start'] = curr['start']
            gl['end'] = curr['end']
    
    print (gl['max'])
    print(gl['start'])
    print(gl['end'])

nums = [5, 4, -1, 7, 8]

# max_subarray(nums=nums)


"""
Given an array of pots of coins, 2 players A and B, each player takes turn
to pick up gold from the front or the end. Find the best number of coins
A can pick up, given that B tries to minimize the number of coins that A can get.

Input: array of pots of coins A
Output: the most number of coins A can pick up
"""

def bestStrategy(coins):
    n = len(coins)
    result = [[0] * n for i in range(n)]
    for i in range(n-1, -1, -1):
        for j in range(i, n):
            if i == j:
                result[i][j] = coins[i]
            
            elif j - i == 1:
                result[i][j] = max(coins[i], coins[j])
            
            else:
                result[i][j]  = max(coins[i] + min(result[i+2][j], result[i+1][j-1]), 
                                    coins[j] + min(result[i+1][j-1], result[i][j-2]))
    
    print (result[0][n-1])


# bestStrategy([4, 6, 2, 3])
# bestStrategy([6, 1, 4, 9, 8, 5])

'''
Cherry pickup
https://leetcode.com/problems/cherry-pickup/
'''
def get_neighbor_max_cherry(max_cherry, i, j):
    n = len(max_cherry)
    if i < 0 or j < 0 or i >= n or j >= n:
        return 0
    return max_cherry[i][j]

def cherryPickup():
    ret = 0
    # grid: nxn grid of int 0, 1, -1
    # grid = [
    #     [0, 1, -1],
    #     [1, 0, -1],
    #     [1, 1, 1]
    # ]
    # grid = [
    #     [1, 1, -1],
    #     [1, -1, 1],
    #     [-1, 1, 1]
    # ]
    grid = [[1,1,1,1,0,0,0],[0,0,0,1,0,0,0],[0,0,0,1,0,0,1],[1,0,0,1,0,0,0],[0,0,0,1,0,0,0],[0,0,0,1,0,0,0],[0,0,0,1,1,1,1]]
    n = len(grid)
    max_cherry = [[0 for j in range (n)] for i in range(n)]
    # print (max_cherry)

    # collect from (0, 0) to (n-1)(n-1)
    for i in range (n-1, -1, -1):
        for j in range(n-1, -1, -1):
            if grid[i][j] == -1:
                max_cherry[i][j] = -1
            else:
                neighbor_max_cherry = max(get_neighbor_max_cherry(max_cherry, i, j + 1), # right
                                    get_neighbor_max_cherry(max_cherry, i+1, j)) # down
                if neighbor_max_cherry == -1:
                    max_cherry[i][j] = -1
                else:
                    max_cherry[i][j] = grid[i][j] + neighbor_max_cherry
            # print (max_cherry[i][j])

    # check number of maximum cherries collected 

    print (max_cherry[0][0])
    # for i in range(n):
    #     row = ''
    #     for j in range(n):
    #         row += f'{max_cherry[i][j]} '
    #     print (row)

    if max_cherry[0][0] == -1:
        ret = 0

    first_round  = max_cherry[0][0]
    # set 0 to chosen cell

    if max_cherry[0][0] > 0:
        i = 0
        j = 0
        while not (i >= n or j >= n):
            tmp = grid[i][j]
            grid[i][j] = 0
            neighbor_max_cherry = max_cherry[i][j] - tmp
            # check right neighbor
            if neighbor_max_cherry == get_neighbor_max_cherry(max_cherry, i, j+1):
                j = j + 1
            # check down neighbor
            elif neighbor_max_cherry == get_neighbor_max_cherry(max_cherry, i+1, j):
                i = i + 1
    
    # for i in range(n):
    #     row = ''
    #     for j in range(n):
    #         row += f'{grid[i][j]} '
    #     print (row)

    # collect from (n-1)(n-1) to (0, 0)
    for i in range (n):
        for j in range(n):
            if grid[i][j] == -1:
                max_cherry[i][j] = -1
            else:
                neighbor_max_cherry = max(get_neighbor_max_cherry(max_cherry, i, j - 1), # left
                                    get_neighbor_max_cherry(max_cherry, i-1, j)) # up
                if neighbor_max_cherry == -1:
                    max_cherry[i][j] = -1
                else:
                    max_cherry[i][j] = grid[i][j] + neighbor_max_cherry
    


    print (first_round + max_cherry[n-1][n-1])
    


cherryPickup()


 