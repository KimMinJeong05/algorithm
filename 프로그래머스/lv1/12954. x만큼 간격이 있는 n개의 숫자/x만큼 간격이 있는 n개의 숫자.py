def solution(x, n):
    if x == 0:
        answer = [0]*n
    else:
        answer = [i for i in range(x,x*(n+1),x)]
    return answer