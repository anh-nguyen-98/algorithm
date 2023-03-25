'''
One Away
'''
def is_one_edit_away(s1, s2):
    # difference between s1 and s2 length = 0
    len_diff = abs(len(s1) - len(s2))
    if len_diff < 2:
        char_diff = 0
        for c1, c2 in zip(s1, s2):
            char_diff += 1 if c1 != c2 else 0
        if char_diff < 2:
            return True

    return False

def get_smallest_special_number(s):
    levels = [
        {"z": ("zero", 0), "w": ("two", 2), "u": ("four", 4), "x": ("six", 6), "g": ("eight", 8)},
        {"h": ("three", 3), "f": ("five", 5), "s": ("seven", 7)},
        {"o": ("one", 1), "i": ("nine", 9)}
    ] 
    count = [0 for i in range(26)]
    for char in s:
        count[ord(char) - 97] += 1

    result = []
    for level in levels:
        for sign in level:
            sign_id = ord(sign) - 97
            if count[sign_id] > 0:
                word = level[sign][0]
                number = level[sign][1]
                occurences = count[sign_id]
                for i in range(occurences):
                    result.append(number)
                for char in word:
                    count[ord(char) - 97] -= occurences

    result = sorted(result)
    smallest_non_zero = -1
    for num in result:
        if num > 0:
            smallest_non_zero = num
            break
    if smallest_non_zero > 0:
        result.remove(smallest_non_zero)
        result.insert(0, smallest_non_zero)
        result =  ''.join(map(str, result))
    else:
        result = '0'
    return result
