# DFS
# 구역이 몇 개로 나눠져 있는지 구하는 문제이다.
# 2차원 리스트인 graph에 사각형이 그려진 부분은 1, 빈 부분은 0 채운다.
# 그 후 빈 부분을 DFS를 사용해 빈 부분이 이어진 부분을 찾고 하나의 영역으로 count한다.
# BFS로 구현해도 상관 없다.

# RecursionError로 python에서 정한 재귀함수 호출 횟수를 넘겨 에러 발생
# 아래 코드로 python 재귀함수 호출 횟수를 늘려준다.
import sys
sys.setrecursionlimit(10**6)

m, n, k = map(int, input().split())
graph = list([0]*n for _ in range(m))
dir = [[1, 0], [0, 1], [-1, 0], [0, -1]]
area = []
# 직사각형 k를 그린다.
for _ in range(k):
    sy, sx, ey, ex = map(int, input().split())
    for curx in range(sx, ex):
        for cury in range(sy, ey):
            graph[curx][cury] = 1


def dfs(curx, cury, areacount):
    graph[curx][cury] = 1
    areacount += 1
    for curdir in dir:
        nextx = curx+curdir[0]
        nexty = cury+curdir[1]
        # 안 가본 graph만 간다.
        if 0 <= nextx and nextx < m and 0 <= nexty and nexty < n and graph[nextx][nexty] == 0:
            # 영역 넓이를 return 하므로 최종 합 영역을 구하기 위해 이전 영역을 더해야한다.
            # 따라서 areacount를 갱신한 후 갱신된 areacount로 영역 넓이를 넓혀간다.
            areacount = dfs(nextx, nexty, areacount)
    # 최종 영역 넓이 반환(영역이 없으면 0일 것이다. but 그럴 일없음. 최소 1 이상)
    return areacount


for x in range(m):
    for y in range(n):
        if graph[x][y] == 0:
            area.append(dfs(x, y, 0))

print(len(area))
for result in sorted(area):
    print(result, end=' ')
