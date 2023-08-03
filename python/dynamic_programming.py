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
def is_valid_neighbor(n, i, j):
    return i >= 0 and i < n and j >= 0 and j < n
def cherryPickup(grid):
    n = len(grid)
    current_dp = [[0 for c2 in range(n)]  for c1 in range(n)]
    previous_dp = [[0 for c2 in range(n)] for c1 in range(n)]

    current_dp[0][0] = grid[0][0]
    previous_dp = current_dp
    for t in range(1, 2*n - 1):
        current_dp = [[0 for c2 in range(n)]  for c1 in range(n)]
        min_col_range = max(0, t - (n-1))
        max_col_range = min(t, n-1)
        for c1 in range(min_col_range, max_col_range +1):
            for c2 in range(min_col_range, max_col_range +1):
                r1 = t - c1
                r2  = t - c2
                if grid[r1][c1] == -1 or grid[r2][c2] == -1:
                    current_dp[c1][c2] = -1
                else:
                    c1_step = [0, 0, 1, 1]
                    c2_step = [0, 1, 0, 1]
                    max_cherry_in_previous_step = -1
                    for i in range(4):
                        previous_c1 = c1 - c1_step[i]
                        previous_r1 = (t - 1) - previous_c1
                        previous_c2 = c2 - c2_step[i]
                        previous_r2 = (t - 1) - previous_c2
                        if is_valid_neighbor(n, previous_r1, previous_c1) and is_valid_neighbor(n, previous_r2, previous_c2):
                            max_cherry_in_previous_step = max(previous_dp[previous_c1][previous_c2], max_cherry_in_previous_step)
                        
                    if max_cherry_in_previous_step == -1:
                        current_dp[c1][c2] = -1
                    else:
                        if r1 == r2 and c1 == c2:
                            current_dp[c1][c2] = max_cherry_in_previous_step + grid[r1][c1]
                        else:
                            current_dp[c1][c2] = max_cherry_in_previous_step + grid[r1][c1] + grid[r2][c2]
        previous_dp = current_dp
        # current_dp = [[0 for c2 in range(n)]  for c1 in range(n)]
    return previous_dp[n-1][n-1]

'''
Given an array of non-negative integers and a target sum, find the subset that sum to target sum.
Similar to knapsack 0-1 (choose or not choose item)
'''
def canSum(nums: list, target_sum: int):
    # create table
    can_sum = [[False for _ in range(target_sum +1)] for _ in range(len(nums) + 1)]

    # mark True for column sum = 0
    for _ in range(len(nums) + 1):
        can_sum[_][0] = True
    
    # calculate canSum from list size 1 -> n
    for nums_size in range(1, len(nums) + 1):
        for sum_value in range(1, target_sum + 1):
            # can_sum[i][j] = ?
            num = nums[nums_size -1]
            choose_num = False
            not_choose_num = False
            if num <= sum_value:
                choose_num = can_sum[nums_size -1][sum_value - num]
            not_choose_num = can_sum[nums_size -1][sum_value]
            can_sum[nums_size][sum_value] = choose_num or not_choose_num
    
    # print subset
    subset = []
    if can_sum[len(nums)][target_sum]:
        sum_value = target_sum
        nums_size = len(nums)
        while sum_value > 0:
            # choose nums[i] or skip
            num = nums[nums_size -1]
            if can_sum[nums_size -1][sum_value - num]:
                subset.insert(0, num)
                sum_value -= num
                nums_size -= 1
            else:
                nums_size -= 1
            
        
    print (subset)

    # # print can_sum
    # repr = '\t'
    # for i in range(target_sum +1):
    #     repr += f'{i}\t'
    # print (repr)
    # for i in range(len(nums) + 1):
    #     repr = f'{nums[i-1]}\t' if i > 0 else ' \t'
    #     for j in range(target_sum +1):
    #         repr += f'{can_sum[i][j]}\t'
    #     print (repr)

    return False

nums = [1, 2, 3]
target_sum = 2
canSum(nums, target_sum)
