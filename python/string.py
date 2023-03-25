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

