//백준 14501번
//모든 경우를 다 찾아 비교하는 브루트 포스 방법으로 풀었다.
//재귀함수로 풀었다.

#include<stdio.h>
#include<vector>
using namespace std;

int n;
int result = 0; // 결과(최대 비용 값)
vector<pair<int, int>> v; // <상담일,비용>

void search(int start, int sum) { // start: 인덱스, sum: 지금까지 번 비용
	if (start >= n) { // 현재 인덱스가 범위를 넘어간 경우
		if (result < sum) result = sum;
		return;
	}

	search(start + 1, sum); // 현재 인덱스를 건너뛰는 재귀함수
	if (start + v[start].first > n) { // 이번 인덱스를 더한 날짜가 범위를 넘갈 경우
		if (result < sum) result = sum;
		return;
	}
	sum += v[start].second; // 현재 인덱스 비용 추가
	start += v[start].first; // 현재 인덱스의 날짜를 더한 날짜
	search(start, sum);// 현재 인덱스 비용을 더한 후 다음 가능한 날짜로 가는 재귀함수
}

//visual studio에서는 scanf대신 scanf_s를 사용한다.
int main() {
	scanf("%d", &n);
	int temp1, temp2;
	for (int i = 0; i < n; i++) {
		scanf("%d %d", &temp1, &temp2);
		v.push_back(make_pair(temp1, temp2));
	}

	for (int i = 0; i < n; i++) { //시작 인덱스를 다 돌아본다.
		search(i, 0);
	}
	printf("%d", result);
}
