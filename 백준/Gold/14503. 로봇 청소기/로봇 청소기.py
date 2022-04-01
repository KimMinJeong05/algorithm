# 구현
# 문제를 그대로 구현하는 전형적인 구현 문제이다.

# direction으로 북서남동을 정해 왼쪽 회전을 정의한다.

# 1. 왼쪽으로 회전한다.
# 2. 앞에 있는 공간이 0이면 이동 / 0이 아니면 1번으로
# 왼쪽을 4번 다 회전했는데 이동을 못했다면 뒤로 후진한다. -> 뒤로 후진은 (d+2)%2 이다.(북<->남, 서<->동)
# 뒤로 후진시 벽(1)이거나 모든 구역이 청소가 되면 멈춘다.

# 새롭게 알게된 함수
# 2차원 리스트에서 특정 값이 있는지 확인
# any('원하는 값' in a for a in arr)

N, M = map(int, input().split())
r, c, d = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]
if d == 1 or d == 3:  # 문제 입력에서의 서쪽과 동쪽이 반대라 교정해준다.
    d = abs(4-d)

direction = [[-1, 0], [0, -1], [1, 0], [0, 1]]  # 북 서 남 동
dir_count = 0  # 회전 수
result = 0  # 청소 수
if arr[r][c] == 0:  # 처음 좌표 청소
    result += 1
    arr[r][c] = 2
    while any(0 in a for a in arr):  # 모든 지역이 청소될때까지
        while dir_count < 4:  # 회전을 4번 할때까지
            d = (d+1) % 4
            if arr[r+direction[d][0]][c+direction[d][1]] == 0:  # 청소가능 지역
                result += 1
                dir_count = 0
                r = r+direction[d][0]
                c = c+direction[d][1]
                arr[r][c] = 2
                break
            else:  # 청소 불가능 지역
                dir_count += 1
        if dir_count == 4:  # 청소 지역을 찾지 못했을 경우
            if arr[r+direction[(d+2) % 4][0]][c+direction[(d+2) % 4][1]] == 1:  # 뒤가 벽일 경우
                break
            else:  # 뒤로 이동가능일 경우
                dir_count = 0
                r = r+direction[(d+2) % 4][0]
                c = c+direction[(d+2) % 4][1]
print(result)
