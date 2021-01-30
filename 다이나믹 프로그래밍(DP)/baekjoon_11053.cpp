// 11053번 - 가장 긴 증가하는 부분 수열
// 배열을 지나가면서 이전 요소에서 자신보다 작은 것이 몇 개인지 count한다.
// 숫자의 순서도 중요하므로 작은 숫자를 모두 count하는 것이 아니라,
// 그 작은 숫자가 이전에 count한 숫자(dp vector)를 찾아야한다.
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	int n=0;
	cin >> n;
	vector<int>v(n);
	vector<int>dp(n, 1); //이전에 자신보다 작은 숫자를 count한 것
	for (int i = 0; i < n; i++) cin >> v[i];

	int cur;
	for (int i = 0; i < n; i++) {
		cur = v[i];
		for (int j = 0; j < i; j++) {
			if (cur > v[j]) dp[i] = max(dp[i], dp[j] + 1);//이전 숫자의 count에 자신도 더해야하므로 +1
		}
	}
	
	cout << *max_element(dp.begin(), dp.end());
}
