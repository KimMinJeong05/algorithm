import sys
# 입력
N=int(input())
cards=list(map(int, sys.stdin.readline().split()))
M=int(input())
find_cards=list(map(int, sys.stdin.readline().split()))
cards.sort()

# 이진 탐색
def binary_search(arr, target, start, end):
    mid=(start+end)//2
    if start>end:
        return 0
    if arr[mid]==target:
        # 같은 숫자 있는지 확인
        return arr[start:end+1].count(target)
    elif arr[mid]>target:
        return binary_search(cards,fc,start,mid-1)
    else:
        return binary_search(cards,fc,mid+1,end)

dic = {}
for fc in find_cards:
    if fc not in dic:
        dic[fc] = binary_search(cards,fc,0,len(cards)-1)

print(' '.join(str(dic[x]) if x in dic else '0' for x in find_cards ))
        