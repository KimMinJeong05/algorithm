//4796번 - 캠핑
//그리디
#include<iostream>
using namespace std;

int main() {
	// 사용 가능한 일, 연속하는 일, 휴가, 결과, case 수
	int l, p, v, result,count=0;
	while (true) {
		cin >> l >> p >> v;
		if (l == 0 && p == 0 && v == 0) break;
		result = 0;
		count++;

		result += (v / p)*l; //휴가를 연속하는 일로 나누어 사용 가능한 일을 모두 구한다
		v %= p; //남은 휴가일
		if (v <= l) result += v; //남은 휴가일이 사용가능한 일보다 많으면 남은 휴가일을 더한다
		else result += l; // 남은 휴가일보다 사용 가능한 일이 많으면 남은 휴가일을 더한다.

		cout <<"Case "<< count<<": "<<result << "\n";
	}
}
