# DFS, BFS
# DFS는 리스트로, BFS는 collections 모듈의 deque를 사용
# DFS는 깊이 우선 탐색으로 그래프에 이어진 깊은 부분을 우선적으로 탐색
# BFS는 너비 우선 탐색으로 특정 노드에 가까운 노드를 우선적으로 탐색

# DFS, BFS 중 같은 우선순위일 경우 숫자가 작은 순서대로 탐색해야하므로 graph 2차원 리스트를 각각 리스트 별로 정렬해야함

from collections import deque
n, m, start = map(int, input().split())
graph = [[] for _ in range(n+1)]
visited = [False]*(n+1)
result_dfs = []
result_bfs = []

for _ in range(m):
    s, e = map(int, input().split())
    graph[s].append(e)
    graph[e].append(s)
# graph 2차원 리스트를 각 작은 리스트 별로 정렬. 같은 리스트에 있으면 같은 우선수위이므로 작은 숫자 먼저 탐색하기 위해
for i in range(n+1):
    graph[i] = sorted(graph[i])


def dfs(cur):
    visited[cur] = True
    # result_dfs.append(cur)
    print(cur, end=' ')
    for next in graph[cur]:
        if not visited[next]:
            dfs(next)


def bfs(cur):
    queue = deque()
    queue.append(cur)
    visited[cur] = True
    while queue:
        cur = queue.popleft()
        # result_dfs.append(cur)
        print(cur, end=' ')
        for next in graph[cur]:
            if not visited[next]:
                queue.append(next)
                visited[next] = True


dfs(start)
print()
visited = [False]*(n+1)
bfs(start)
