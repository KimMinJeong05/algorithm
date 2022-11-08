def solution(s):
    answer = True
    stack=[]
    for s_one in s:
        if s_one == '(':
            stack.append('(')
        else:
            if len(stack) <= 0:
                return False
            else:
                stack.pop()
    if len(stack) == 0:
        return True
    return False