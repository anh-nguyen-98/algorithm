import math
"""
Find the closest pair of points
Input: array points p[i] = [x_i, y_i]
Output: closest pair of points
"""
def quick_sort(points, key):
    n = len(points)
    if n == 0:
        return points
    start = 0
    end = n -1
    return quick_sort_helper(points=points, key=key, start=start, end=end)

def quick_sort_helper(points, key, start, end):
    # base case
    if start < 0 or end >= len(points) or end < start or start==end:
        return
    # choose pivot
    p = points[end]

    # move pivot to right pos (< pivot & > pivot)
    j = start
    for i in range(start, end):
        if points[i][key] <= p[key]:
            temp = points[j]
            points[j] = points[i]
            points[i] = temp
            j += 1

    # swap points[j] and pivot
    points[end] = points[j]
    points[j] = p

    # quicksort (left pivot, right pivot)
    quick_sort_helper(points, key=key, start=start, end=j - 1)
    quick_sort_helper(points, key=key, start=j + 1, end=end)


def closest_pair(points):
    # base case
    # sort points by x coordinates

    quick_sort(points=points, key=0)

    return closest_pair_helper(points=points, start=0, end=len(points) -1)

def closest_pair_helper(points, start, end):
    # base case
    if start == end:
        return [-1, points[start], points[start]]
    if end - start == 1:
        return [get_distance(points[start], points[end]), points[start],points[end]]

    mid = math.ceil((start + end)/2)
    # find d1 = closest_pair (left_points /2 )
    d1, start1, end1 = closest_pair_helper(points=points, start=start, end=mid)
    # find d2 = closest_pairs (right_points /2)
    d2, start2, end2 = closest_pair_helper(points=points, start=mid+1, end=end)

    # pair = [d2, start2, end2]
    # d = min (d1, d2)
    if d1 == -1 or d2 != -1 and d2 < d1:
        pair = [d2, start2, end2]
    else:
        pair = [d1, start1, end1]

    d = pair[0]
    # A = list points have x between [mid - d, mid]
    left_interval = binary_search(points, points[mid][0] - d, 0)


    # B = list points have x between [mid, mid + d]
    right_interval = binary_search(points, points[mid][0] + d, 0)
    rstrip = points[mid+1:right_interval+1].copy()
    quick_sort(rstrip, 1)
    # for each point in A, check dist between point and 8 points in B, find min (d, dist)
    for i in range(left_interval, mid+1):
        p = points[i]
        upper = binary_search(rstrip, p[1] - d, 1)
        lower = binary_search(rstrip, p[1] + d, 1)

        for j in range(upper, lower+1):
            p_r = rstrip[j]
            dist = get_distance(p, p_r)
            if dist < d:
                pair = [dist, p, p_r]

    return pair

def get_distance(p1, p2):
    return math.sqrt( pow((p1[0] - p2[0]), 2) + pow((p1[1] - p2[1]), 2))

### Rewrite BS with key
def binary_search(A, val, key):
    start = 0
    end = len(A) -1
    return binary_search_helper(A, val, key, start, end)
def binary_search_helper(A, val, key, start, end):
    # base case
    if start > end:
        if start >= 0 and start < len(A):
            return start
        return end

    mid = math.ceil((start + end) /2)
    if val == A[mid][key]:
        return mid
    if val < A[mid][key]:
        return binary_search_helper(A, val, key, start, mid-1)
    if val > A[mid][key]:
        return binary_search_helper(A, val, key, mid+1, end)

def main():
    points = [(2, 3), (12, 30), (40, 50), (5, 1), (12, 10), (3, 4)]
    # quick_sort(points, 0)
    # for p in points:
    #     print(p)
    #
    # nums = [1, 3, 5, 12]
    # print(binary_search(points, 5, 0))
    # print (quick_sort(points, "x"))
    print (closest_pair(points))
