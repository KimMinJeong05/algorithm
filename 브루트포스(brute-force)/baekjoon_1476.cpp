//백준 1476번 - 날짜 계산
/*
E: E + 변수*15
S: S + 변수*28
M: M + 변수*19
E, S, M이 같으면 정답
		  다르면 제일 큰 값보다 작으면 변수값을 1 증가한다.
*/
#include<iostream>
#include<algorithm>
using namespace std;

int main() {
	int e, s, m;
	scanf("%d %d %d", &e, &s, &m); //visual studio에서는 scanf_s를 사용

	int countE = 0, countS = 0, countM = 0; //배수를 늘려갈 count값
	int curE = e, curS = s, curM = m; //현재 E,S,M의 값
	while (true) {
		if (curE == curS && curS == curM) { //정답
			break;
		}
		//제일 큰 값보다 작으면 count값을 1 증가
		int curmax = max({ curE, curS, curM }); 
		if (curmax != curE) {
			countE++;
		}
		if (curmax != curS) {
			countS++;
		}
		if (curmax != curM) {
			countM++;
		}
		curE = e + countE * 15;
		curS = s + countS * 28;
		curM = m + countM * 19;
	}
	printf("%d\n", curE);
}
