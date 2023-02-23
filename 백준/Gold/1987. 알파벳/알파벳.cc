//백준 1987번

#include<iostream>
#include<string>
#include<vector>
using namespace std;

int r, c;
int result = 0;
int nextX[4] = { -1,0,1,0 };
int nextY[4] = { 0,-1,0,1 };
string *arr;

void search(bool *checkArr,int x, int y, int sum) {
	int curx, cury, curData;
	bool flag = true;
	for (int i = 0; i < 4; i++) {
		curx = x + nextX[i]; cury = y + nextY[i];
		if (curx < r && cury < c && curx >= 0 && cury >= 0) {
			curData = int(arr[curx][cury] - 'A');
			if (!checkArr[curData]) {
				checkArr[curData] = true;
				search(checkArr,curx, cury, sum + 1);
				checkArr[curData] = false;
				flag = false;
			}
		}
	}
	if (flag) {
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
	checkArr[int(arr[0][0]-'A')]=true;
	search(checkArr, 0, 0, 1);
	cout << result;
}