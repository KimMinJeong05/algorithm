
# 입력
pnum = int(input()) 
person = [[] for i in range(pnum+1)] # 빈 2차원 배열 선언, index 0은 비워두고 1~pnum index 사용
x, y = map(int, input().split()) # 구해야할 촌수 대상
n = int(input())

for i in range(n):
    p, c = map(int, input().split())
    person[p].append(c)
    person[c].append(p)

# DFS 사용
def dfs(v, visited, count):
    visited[v]=True
    count+=1
    if y in person[v]:
        print(count)
        exit()
    for i in person[v]:
        if not visited[i]:
            dfs(i, visited, count)
    
# main
visit = [False] * (pnum+1)
dfs(x, visit, 0)
print(-1)