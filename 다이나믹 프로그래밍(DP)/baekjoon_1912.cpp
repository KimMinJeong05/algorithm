// 1912번 - 연속합
// 다이나믹 프로그래밍
// 문제: 10, -4, 3, 1, 5, 6, -35, 12, 21, -1
// 10 -> 10
// 10 -4 -> 6
// 10 -4 3 -> 9
// 10 -4 3 1 -> 10
// 10 -4 3 1 5 -> 15
// 10 -4 3 1 5 6 -> 21
// 10 -4 3 1 5 6 -35 -> -14
// 12 -> 원래 값인 12가	12-14=-2보다 크므로 12로 정함
// 12 21
// 12 21 -1

#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	int n=0;
	cin >> n;
	vector<long long>v(n);
	vector<long long>dp(n);
	for (int i =0 ; i < n; i++) cin >> v[i];

	dp[0] = v[0];
	for (int i = 1; i < n; i++) {
		dp[i] = max(dp[i - 1] + v[i], v[i]);
	}
	cout << *max_element(dp.begin(), dp.end());
}
