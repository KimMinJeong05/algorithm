# 입력
array=list(map(str, input()))

# 삽입 정렬, reverse
def insert_sort(arr):
    for i in range(1,len(arr)):
        for j in range(i,0,-1):
            if arr[j]<arr[j-1]:
                arr[j],arr[j-1]=arr[j-1],arr[j]
            else:
                break
    return arr

sort_array=insert_sort(array)

# 출력
for i in range(len(sort_array)-1,-1,-1): 
    print(sort_array[i],end='')