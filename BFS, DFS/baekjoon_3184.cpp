//백준 3184번 - 양
//BFS -> queue와 방문check를 이용한다.
#include<iostream>
#include<vector>
#include<queue>
#include<string>
using namespace std;

int r, c;
vector<string> arr;//미로 저장
bool **check;//방문 check
int nextx[] = { 0,1,0,-1 };
int nexty[] = { 1,0,-1,0 };
int curv = 0, curo = 0;//현재구역의 늑대수, 현재구역의 양수
void BFS(int x, int y) {
	check[x][y] = true;
	queue<pair<int, int>> q;
	q.push({ x,y });
	if (arr[x][y] == 'o') curo++;
	else if (arr[x][y] == 'v') curv++;

	int curx, cury, nx, ny;
	while (!q.empty()) {//현재 구역의 요소를 다 돌때까지 whlie
		nx = q.front().first; ny = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			curx = nx + nextx[i]; cury = ny + nexty[i];
			if (curx >= 0 && cury >= 0 && curx < r&&cury < c && !check[curx][cury]
				&& arr[curx][cury] != '#') {//한번도 가지 않고 울타리가 아니면 현재 구역에 있는 것
				check[curx][cury] = true;
				if (arr[curx][cury] == 'o') curo++;
				else if (arr[curx][cury] == 'v') curv++;
				q.push({ curx,cury });//해당 구역의 요소를 queue에 push
			}
		}
	}
}

int main() {
	cin >> r >> c;
	string temp;
	check = new bool*[r];
	for (int i = 0; i < r; i++) {
		check[i] = new bool[c];
		cin >> temp;
		arr.push_back(temp);
		for (int j = 0; j < c; j++) {
			check[i][j] = false;
		}
	}

	int resultv = 0, resulto = 0;
	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			if (!check[i][j] && arr[i][j] != '#') {
				curv = 0; curo = 0;
				BFS(i, j);
				if (curv >= curo) resultv += curv;
				else resulto += curo;
			}
		}
	}

	cout << resulto << " " << resultv;
}
