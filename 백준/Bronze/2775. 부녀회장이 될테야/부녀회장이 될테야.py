# 구현
# 1 <= k,n <=14 이므로 최대를 계산해도 0층을 제외하면 14*15 = 210개밖에 되지 않기 때문에 전체를 계산한다.
# for문에서 a: 층, b: 호 이다.
# a층의 b호의 값이 (a-1)층의 1~b호의 값의 합이다. 이것은 (a-1)층의 b호의 값과 a층의 (b-1)호의 합과 같다.
# -> 이유는 a층의 (b-1)호의 값이 (a-1)층의 1~(b-1)호의 값의 합과 같기 때문이다.

# 주의
# 2차원 배열을 만들시 [[0]*15]*15와 같이 배열을 선언한다면,
# 얕은 복사가 일어나 for문 계산에서 값을 계산해 배열에 넣을때 다른 원소들도 같이 변경되는 현상이 나타날 수 있다.
# 따라서 for문을 이용해서 다른 방법으로 배열을 선언해야한다.

apt = [[0]*15 for _ in range(15)]
for i in range(15):
    apt[0][i] = i
caseNum = int(input())

for a in range(1,15):
    for b in range(1,15):
        apt[a][b] = apt[a-1][b] + apt[a][b-1]

for case in range(caseNum):
    k = int(input())
    n = int(input())
    print(apt[k][n])
