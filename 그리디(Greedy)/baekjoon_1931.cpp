//1931번 - 회의실 배정
//그리디
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	int size;
	cin >> size;
	vector <pair<int, int>> v(size);
	//주의! 시작시간을 second에, 끝나는 시간을 first에 저장
	// -> 끝나는 시간 기준으로 정렬하기 위해
	for (int i = 0; i < size; i++) cin >> v[i].second >> v[i].first;
	sort(v.begin(), v.end()); // 끝나는 시간을 기준으로 정렬

	// 현재 회의 끝난 시간, 회의 count 개수
	int cur_end=v[0].first, count = 1;
	// 현재 회의 끝난 시간보다 같거나 큰 회의시작시간을 찾는다.
	for (int i = 1; i < size; i++) {
		if (v[i].second < cur_end) continue;
		count++;
		cur_end = v[i].first;
	}
	cout << count;
}
