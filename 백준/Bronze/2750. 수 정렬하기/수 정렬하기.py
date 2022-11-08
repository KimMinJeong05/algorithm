
# 입력
n = int(input())
arr=[]
for i in range(n):
    arr.append(int(input()))

# 정렬
for i in range(n):
    min_idx=arr.index(min(arr[i:]))
    arr[i], arr[min_idx] = arr[min_idx], arr[i]

# 출력
for a in arr:
    print(a)