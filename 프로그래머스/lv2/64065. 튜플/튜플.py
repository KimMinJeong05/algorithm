def solution(s):
    # 입력 문자열을 set을 원소로 가지는 리스트로 변환
    s_arr=[]
    for i in list(s[2:-2].split('},{')):
        s_arr.append(list(map(int, i.split(','))))
    
    # 원소가 작은 튜플 순으로 정렬
    s_arr.sort(key=lambda x:len(x))
    
    answer=[]
    for t in s_arr:
        answer.append((set(t)-set(answer)).pop())
    return answer