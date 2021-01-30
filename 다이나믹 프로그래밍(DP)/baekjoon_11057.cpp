// 11057번 - 오르막 수
// 다이나믹 프로그래밍(bottom-up)

// 1 -> 1 1 1 1 1 1 1 1 1 1 = 10
// 2 -> 1 2 3 4 5 6 7 8 9 10 = 55
// 3 -> 1 3 6 10 15 21 28 36 45 55 = 220
// => 이전 배열에서 인덱스 1에서 인덱스k까지 더한 값이 현재 배열의 인덱스k의 값이다.
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	int n = 0;
	cin >> n;

	vector<vector<int>> v(n + 1, vector<int>(11, 0));
	for (int i = 1; i <= 10; i++) v[1][i] = 1;
	for (int i = 2; i <= n; i++) {
		for (int j = 1; j <= 10; j++) {
			for (int k = 1; k <= j; k++) {
				v[i][j] += v[i - 1][k];
				v[i][j] %= 10007;
			}
		}
	}
	long result = 0;
	for (int i = 1; i <= 10; i++) result += v[n][i];
	cout << result % 10007;
}
