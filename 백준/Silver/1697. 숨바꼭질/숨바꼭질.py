# BFS
# 가장 빠른 시간을 찾아야하므로 DFS보단 BFS로 푸는 것이 유리하다.
# 1초 후에 갈 수 있는 모든 좌표가 인접한 노드라 본다.
# 가장 짧은 거리를 구하는 것이므로 k에 먼저 도착하는 경우가 정답니다.
# visited에 True, False가 아닌 지나간 depth를 저장하면 k에 제일 먼저 도착한 depth가 정답니다.
from collections import deque

n, k = map(int, input().split())
visited = [0 for _ in range(100001)]

queue = deque()
queue.append(n)
while queue:
    cur = queue.popleft()
    if cur == k:
        print(visited[cur])
        break
    if cur-1 >= 0 and not visited[cur-1]:
        queue.append(cur-1)
        visited[cur-1] = visited[cur] + 1
    if cur+1 <= 100000 and not visited[cur+1]:
        queue.append(cur+1)
        visited[cur+1] = visited[cur] + 1
    if cur*2 <= 100000 and not visited[cur*2]:
        queue.append(cur*2)
        visited[cur*2] = visited[cur]+1
