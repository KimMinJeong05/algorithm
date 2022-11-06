def solution(n):
    answer = list(map(int,str(n))) # 자연수 n을 문자로 바꾼 후 int인 list로 만듦
    answer = answer[::-1]
    return answer