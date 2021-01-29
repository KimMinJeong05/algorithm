// 11052번 - 카드 구매하기
// 다이나믹 프로그래밍(Bottom-Up)
// 카드 개수가 작은 것부터 최대인 것을 계산한다.

// 1개 카드의 최대
// 2개 카드의 최대
// ...
// n개 카드의 최대
// 를 순차적으로 구한다.

// index를 0부터 시작하면 계산이 이상해진다.
// 문제: n=4, 1 5 6 7
//	0 0 0 0 -> dp[0] = 1; -> 카드1개
//	1 1 1 0 -> dp[1] = v[0] = 1 -> 카드 2개여야하는데 2개 값이 들어온다.
//	1 1 0 1 -> dp[1] = dp[0]+v[1] = 6 -> 카드 2개여야하는데 3개 값이 들어온다.

// index를 0부터 시작하면 계산이 이상해진다.
//	1 1 0 1 -> dp[1] = v[1] = 1 -> 카드1개
//	2 2 1 1 -> dp[2] = dp[1]+v[1] = 2 -> 카드 2개
//	2 2 0 2 -> dp[2] = v[2] = 5 -> 카드 2개
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	int n = 0;
	cin >> n;
	vector<int> v(n+1,0); //카드 값을 저장
	vector<int> dp(n+1,0); // 각각 카드 개수의 최대를 dp에 저장
	for (int i = 1; i <= n; i++) {
		cin >> v[i];
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= i; j++) {
			dp[i] = max(dp[i], dp[i - j] + v[j]);
		}
	}
	cout << dp[n];
}
