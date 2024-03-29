# [Gold V] 도미노 찾기 - 1553 

[문제 링크](https://www.acmicpc.net/problem/1553) 

### 성능 요약

메모리: 21148 KB, 시간: 260 ms

### 분류

백트래킹(backtracking), 구현(implementation)

### 문제 설명

<p style="text-align: center;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/upload/201004/do.png" style="height:300px; width:300px"></p>

<p>도미노의 크기는 1×2이고, 크기가 1×1인 칸으로 나누어져 있다. 칸은 수를 나타내며, 위와 같이 총 28가지가 있다.</p>

<p>크기가 8×7인 격자가 있고, 격자의 각 칸에는 정수가 하나씩 들어있다. 위의 도미노를 이용해 문제의 격자와 같은 상태를 만드는 방법의 수를 구해보자.</p>

<p>격자의 칸에 적힌 수는 도미노의 칸이 의미하는 수와 같아야 한다. 도미노는 회전할 수 있으며, 같은 도미노를 여러 번 사용하면 안된다.</p>

### 입력 

 <p>총 8개의 줄에 격자의 상태가 주어진다. 격자에는 0부터 6까지의 수만 존재한다.</p>

### 출력 

 <p>첫째 줄에 경우의 수를 출력한다.</p>


---

### 핵심 로직

DFS로 전체 케이스를 다 체크해본다.
하지만 28!(?) 경우의 수가 나오므로 2억번이 넘으므로 2초가 넘음
-> 백트레킹을 사용해 필요없는 경우는 빨리 끝낸다.

1. dfs로 모든 경우를 다 해본다.
2. 재귀로 백트레킹함

### 주의할 점 
1. 처음에 stack으로 dfs하려다 실패. 백트래킹 시 재귀가 더 수월함
2. 다음 재귀함수 돌릴 파라미터 잘 생각
   -> 잘못 생각해서 시간 많이 걸림
