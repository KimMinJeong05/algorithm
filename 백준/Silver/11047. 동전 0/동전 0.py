# 그리드 알고리즘
# 현재 상황에서 지금 가장 좋은 방법으로만 가는 것
# -> 동전 종류 중 값이 가장 큰 동전부터 사용하면 개수가 최소값이 나올것이다.
# -> 동전의 종류(N)가 오름차순으로 입력되므로 N을 reverse해서 
#    for문으로 가장 큰 동전으로 가장 많은 값을 처리하는 방식으로 푼다.

N, K = map(int,input().split()) #N: 동전 종류, K: 합
coin_list = list(int(input()) for _ in range(N))
coin_count = 0

for coin in reversed(coin_list):
    coin_count += (K//coin)
    K = K%coin
print(coin_count)
