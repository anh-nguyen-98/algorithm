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

def get_smallest_non_zero_digit(digit_count):
    for i in range(1, 10):
        if digit_count[i] > 0:
            return i
    return 0

def get_smallest_special_number(s):
    # levels = [
    #     {"z": ("zero", 0), "w": ("two", 2), "u": ("four", 4), "x": ("six", 6), "g": ("eight", 8)},
    #     {"h": ("three", 3), "f": ("five", 5), "s": ("seven", 7)},
    #     {"o": ("one", 1), "i": ("nine", 9)}
    # ]
    digits_with_unique_char = {'z': 0, 'w': 2, 'u': 4, 'x': 6, 'g': 8}
    

    # char_count 
    char_count = [0 for i in range(26)]
    for char in s:
        char_count[ord(char) - 97] += 1

    # digit count
    digit_count = [0 for i in range(11)]

    # count occurences of digits with unique character
    for char in digits_with_unique_char:
        char_index = ord(char) - 97
        num = digits_with_unique_char[char]
        digit_count[num] = char_count[char_index]
    
    # count occurrences of other digits
    # t[h]ree, eig[h]t
    digit_count[3] = char_count[ord('h') - 97] - digit_count[8]
    # [f]ive, [f]our
    digit_count[5] = char_count[ord('f') - 97] - digit_count[4]
    # [s]even, [s]ix
    digit_count[7] = char_count[ord('s') - 97] - digit_count[6]
    # [o]ne, zer[o], tw[o], f[o]ur
    digit_count[1] = char_count[ord('o') - 97] - (digit_count[0] + digit_count[2] + digit_count[4])
    # n[i]ne, f[i]ve, e[i]ght, s[i]x
    digit_count[9] = char_count[ord('i') - 97] - (digit_count[5] + digit_count[8] + digit_count[6])

    smallest_special_number = ''
    # get smallest non-zero

    smallest_non_zero_digit = get_smallest_non_zero_digit(digit_count)
    if smallest_non_zero_digit == 0:
        smallest_special_number = '0'
    else:
        smallest_special_number = str(smallest_non_zero_digit)
        digit_count[smallest_non_zero_digit] -= 1
        digit_list = []
        for num, num_count in enumerate(digit_count):
            digit_list.extend([str(num)] * num_count)
        smallest_special_number += ''.join(digit_list)            
    return smallest_special_number

shuffled_string = 'ttnrwoooeeefurh'

print (get_smallest_special_number(shuffled_string))