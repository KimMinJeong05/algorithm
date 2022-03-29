# 구현
# n=1부터 시작하여 10000보다 작은 수까지 d(n)의 무한 수열을 만들어
# 등장한 수는 등장했는지 체크하는 리스트(check[])에 체크한다.

check=[False]*10001
check[0]=True
for n in range(1,10001):
    dn = n
    while True:
        dn = dn + dn%10+(dn//10)%10+(dn//100)%10+(dn//1000)%10+(dn//10000)%10  # d(n) 계산
        if dn>10000 or check[dn]: # dn이 10000 까지인 수만 고려 & d(n)값이 한번 나왔다면 그 후 d(n)도 나왔던 수. & dn이 10000 까지인 수만 고려 
            break 
        check[dn]=True
# 셀프 넘버 출력
for idx, value in enumerate(check):
    if value is False:
        print(idx)
        