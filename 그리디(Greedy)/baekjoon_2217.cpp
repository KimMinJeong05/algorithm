//2217번 - 로프
//그리디
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	int num;
	cin >> num;
	vector<int> weight(num);
	for (int i = 0; i < num; i++) cin >> weight[i];
	sort(weight.begin(), weight.end());

	// 결과값, 현재 로프에서 가장 최대 값
	int result = 0, curmax=0;
	for (int i = 0; i < num; i++) {
		//최대는 자기 자신보다 큰 로프를 다 사용하는 거다.
		//오름차순이 되어 있으므로 큰 로프의 개수는 num-i이다.
		curmax = weight[i] * (num - i);
		if (result < curmax) result = curmax;
	}
	cout << result;
}
