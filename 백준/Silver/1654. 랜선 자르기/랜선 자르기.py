import sys
# 입력
k,n=map(int,input().split())
lan=[]
for i in range(k):
    lan.append(int(sys.stdin.readline()))
lan.sort(reverse=True)

# 이진 탐색
start=1
end=lan[0]

while start<=end:
    mid=(start+end)//2
    count=0
    for idx in range(k):
        count+=(lan[idx]//mid)
    
    if count<n: 
        end=mid-1
    else:
        start=mid+1

print(end) # 최대값이므로 end 출력