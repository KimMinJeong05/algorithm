def solution(x):
    xsum = sum(list(map(int, str(x))))
    if x%xsum == 0:
        return True
    return False