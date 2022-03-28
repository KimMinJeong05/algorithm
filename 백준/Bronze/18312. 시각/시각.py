# 구현
# 하루 시간의 경우의 수가 60*60*24=86,400 가지이므로 10만개가 되지 않으므로 2초안에 채점 가능.
# -> 모든 경우의 수를 하나씩 다 count하는 방법으로 품

# 주의 #
# range(a, b) = a ~ b-1이므로 N시간까지 구할려면 range(0, N+1)해야함.
# K=0 일때, 1시 = 01시이므로 count해야함.(zfill()함수 사용)

N, K = map(int,input().split())
count=0
for h in range(0,N+1):
    for m in range(0,60):
        for s in range(0,60):
            if (str(K) in str(h).zfill(2)) or (str(K) in str(m).zfill(2)) or (str(K) in str(s).zfill(2)):
                count+=1
print(count)
