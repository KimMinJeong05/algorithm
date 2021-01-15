//백준 1987번 - 알파벳
//모든 경우 중 가장 큰 값을 출력하는 것이므로 브루트포스 방법으로 푼다.
//칸을 넘어갈 때, 값을 vector에 저장하는 법 보단 
//알파벳의 개수가 정해져 있으므로 bool배열로 0~26(A~Z)인덱스에 상태를 나타내는 법이 더 시간 효율이 좋다.

#include<iostream>
#include<string>
#include<vector>
using namespace std;

int r, c;
int result = 0; //최대 값
int nextX[4] = { -1,0,1,0 }; //좌, 하, 우, 상(X좌표)
int nextY[4] = { 0,-1,0,1 };//좌, 하, 우, 상(Y좌표)
string *arr; //입력 받은 보드 값

void search(bool *checkArr,int x, int y, int sum) { //최대 값을 찾는 재귀함수
	int curx, cury, curData;
	bool flag = true; //더이상 갈 수 있는 곳이 없으면 true
	for (int i = 0; i < 4; i++) {
		curx = x + nextX[i]; cury = y + nextY[i];
		if (curx < r && cury < c && curx >= 0 && cury >= 0) {
			curData = int(arr[curx][cury] - 'A');
			if (!checkArr[curData]) {//현재 가야한 칸(다음 칸)의 알파벳이 기존에 없을 경우
				checkArr[curData] = true; //다음 칸의 알파벳 추가
				search(checkArr,curx, cury, sum + 1); //재귀함수
				checkArr[curData] = false; //다른 경우를 위해 추가한 알파벳 지움
				flag = false;
			}
		}
	}
	if (flag) {// 다음에 갈 곳이 없을 경우 return
		if (result < sum) result = sum;
		return;
	}
}

int main() {
	ios_base::sync_with_stdio(0);// 입출력 속도 향상
	cin.tie();
	cin >> r >> c;
	arr = new string[r];
	bool checkArr[26] = { false };
	for (int i = 0; i < r; i++) {
		cin >> arr[i];
	}

	checkArr[int(arr[0][0]-'A')]=true; //첫번째 칸의 알파벳 추가
	search(checkArr, 0, 0, 1);
	cout << result;
}
