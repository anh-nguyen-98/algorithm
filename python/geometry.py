import sys
"""
Given an array of points, find the minimum area of a rectangle formed from the the points.
If no rectangle can be formed, output 0.
"""
def minAreaRect(points:list):
    min_area = sys.maxsize
    y_pairs = {}
    groups = {}
    for [x, y] in points:
        groups.setdefault(x, []).append(y)
    for x in sorted(groups.keys()):
        group = sorted(groups[x])
        for i in range(len(group) -1):
            for j in range(i+1, len(group)):
                y1, y2 = group[i], group[j]
                if (y1, y2) in y_pairs:
                    x1 = y_pairs[(y1, y2)]
                    area = (y2 - y1) * (x - x1)
                    min_area = min(min_area, area)
                y_pairs[(y1, y2)] = x
    return min_area if min_area != sys.maxsize else 0

points = [[3,2],[0,0],[3,3],[3,4],[4,4],[2,1],[4,3],[1,0],[4,1],[0,2]]
print(minAreaRect(points)) # 2