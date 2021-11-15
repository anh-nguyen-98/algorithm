# naive method

# whether contains pattern (O(n))
def includes (pattern, text) :
  if not pattern: 
    return False
  i = 0
  for j in range(len(text)) :
    if text[j] == pattern[i]: 
      i += 1
      if i == len(pattern):
        return True
    else:
      i = 0
    
  return False

# find all occurrences (O(mn))
def find (pattern, text):
  result = []
  if not pattern: 
    return result
  for j in range(len(text) - len(pattern)+1):
    i = 0
    while (i < len(pattern) and text[j] == pattern[i]):
      i += 1
      j += 1
    if i != 0 and i == len(pattern):
      result.append(j - i)
  return result

def kmp(pattern, text, lsp):
  result = []
  if not pattern: 
    return result
  i = 0
  j = 0
  while (i < len(text)):
    if text[i] == pattern[j]:
      i +=1
      j +=1
    if j == len(pattern):
      result.append(i - j)
      j = lsp[j-1]
    elif text[i] != pattern[j]:
      if j == 0:
        i +=1
      else:
        j = lsp[j -1]
  return result

text =  "AABAACAADAABAABA"
pattern =  "AABA"

lsp = [0, 1, 0, 1]

print (kmp(pattern=pattern, text=text, lsp=lsp))

      

        

      
    

# print(find("AABA", "AABAACAADAABAABA"))