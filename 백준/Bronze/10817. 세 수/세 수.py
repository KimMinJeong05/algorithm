
# 입력
array=list(map(int, input().split()))

# 퀵 정렬()
def quick_sort(arr):
    if len(arr) <=1 :
        return arr
    
    pivot=arr[0] 
    tail=arr[1:] #pivot을 제외한 리스트

    left_side = [x for x in tail if x<=pivot]
    right_side = [x for x in tail if x>pivot]

    return quick_sort(left_side) + [pivot] + quick_sort(right_side)

sort_array=quick_sort(array)

# 출력
print(sort_array[1])