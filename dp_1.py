import imp


import sys
# fibonaci (recursive)
from ast import Sub
import math


def fib(n):
    if n <= 1:
        return n

    return fib(n-1) + fib(n-2)


# print(fib(5))

# fibonaci (dynamic programming)


def fib2(n):
    dp = [0] * (n + 1)
    dp[0] = 0
    dp[1] = 1
    for i in range(2, n+1):
        dp[i] = dp[i-1] + dp[i-2]

    return dp[n]


# print(fib2(5))

# count number of ways to create sum of n given a set of numbers

# cutting rod
# recursive


def r_old(p, n):
    if n == 0:
        return 0

    r_max = 0
    for i in range(1, n+1):
        r_max = max(r_max, p[i-1] + r_old(p, n - i))

    return r_max


p = [1, 5, 8, 9]

# print(r_old(p, 4))

# dp: top-down (1 <= i <= n) = > n trong r tu n-1 -> 0


def r(p, n):
    dp = [-1] * (n +1)
    s = [-1] * (n +1)  
    dp[0] = 0
    s[0] = 0
    r_max = r_aux(p, n, dp, s)
    return [dp, s]

def r_aux(p, n, dp, s):
    if dp[n] >= 0:
        return dp[n]
    dp[n] = 0
    for i in range(1, n+1):
        if (p[i-1] + r_aux(p, n-i, dp, s) > dp[n]):
          dp[n] = p[i-1] + r_aux(p, n - i, dp, s)
          s[n] = i

    return dp[n]

def print_solution (p, n):
  solution = r(p, n)
  print ('Optimal revenue', solution[0])
  print('Order to cut the rod:')
  while n > 0:
    print (solution[1][n])
    n -= solution[1][n]

def r_with_cost (p, n, c):
    dp = [-1] * (n +1)
    dp[0] = 0
    r_max = r_aux(p, n, c, dp)
    return r_max

def r_with_cost_aux(p, n, c, dp):
    if dp[n] >= 0:
        return dp[n]
    dp[n] = 0
    for i in range(1, n+1):
        first_cut = lambda p, i : p[i-1] - c if i < n else p[i-1]
        if (first_cut + r_aux(p, n-i, dp, c) > dp[n]):
          dp[n] = p[i-1] + r_aux(p, n - i, dp, c)
          

    return dp[n]
p = [1, 5, 8, 9]

# print(r(p, 4))
# print_solution(p, 4)
	
def print_lcs (a, d, i, j):
	if i < 0 or j < 0:
		return ""
	
	if d[i][j] == 1:
		return print_lcs(a, d, i-1, j-1) + a[i]
	
	if d[i][j] == -1:
		return print_lcs(a, d, i-1, j)
	
	return print_lcs(a, d, i, j-1)


def lcs_length(a, b):
    m = len(a)
    n = len(b)
    c = []
    d = []
    for i in range (0, n):
        c.append([])
        d.append([])
        for j in range (0, m):
            c[i].append(0)
            d[i].append(0)

    print (c)

    for i in range (1, n):
        for j in range (1, m):
            i_prev = max (i-1 , 0)
            j_prev = max (j-1, 0)
            if a[j] == b[i]:
                c[i][j] = 1 + c[i_prev][j_prev]
                d[i][j] = 1
            elif c[i_prev][j] > c[i][j_prev]:
                c[i][j] = c[i_prev][j]
                d[i][j] = -1
            else:
                c[i][j] = c[i][j_prev]

    print (c)
    print(print_lcs(a, d, n-1, m-1))


# find longest palidrome substring - naive implementation
def lps_naive (s):

    lps = ""
    for i in range (0, len(s)):
        for j in range (i, len(s)):
            substring = s[i: j+1]
            if isPalindrome(substring) and len(substring) > len(lps):
                lps = substring
    return lps

def isPalindrome (s):
    n = len(s)
    if n == 0: return False
    mid =  math.ceil (n / 2)
    for i in range (0, mid):
        if s[i] != s[n - 1 - i]:
            return False         
    
    return True

# print (lps_naive("babad"))



# find longest palindrome substring - dp implementation
def longestPalindrome (s):
    if s == "" : return s
    
    n = len(s)
    c = []   
    for i in range (0, 2):
        c.append([])
        for j in range (0, n):
            c[i].append(False)

    start = 0
    max_len = 1           
    for i in range (n -1, -1, -1):
        for j in range (i, n):
            if (s[i] == s[j]) and (j - i < 2 or c[(i+1) % 2][j-1]):
                c[i%2][j] = True
                if j - i + 1 > max_len:
                    start = i
                    max_len = j - i + 1
            else:
                c[i%2][j] = False

    return s[start: start + max_len]
    

print(longestPalindrome("aacabdkacaa"))

'''
finds maximum subarray using kadane's algorithm (dynamic programming)
corner case: all neg array
'''
def max_sum_subarray(array):
    current_sum = array[0]
    best_sum = current_sum
    for i in range(1, len(array)):
        current_sum = max(array[i], current_sum + array[i])
        best_sum = max(best_sum, current_sum)
    return best_sum


# lcs_length("ABC", "ABCD") 

    



