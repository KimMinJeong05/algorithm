//백준 9095
//브루트 포스
//모든 경우를 다 for문으로 만든 N중 for문이다.
//cur의 숫자 범위가 적어서 가능하다.
#include<stdio.h>
using namespace std;

int main() {
	int n, cur;
	int sum, count;
	scanf_s("%d", &n);
	for (int i = 0; i < n; i++) {
		count = 0;
		scanf_s("%d", &cur);
		for (int l1 = 1; l1 <=3; l1++) {
			if (l1 == cur) count++;
			for (int l2 = 1; l2 <= 3; l2++) {
				if (l1+l2 == cur) count++;
				for (int l3 = 1; l3 <= 3; l3++) {
					if (l1 + l2 + l3 == cur) count++;
					for (int l4 = 1; l4 <= 3; l4++) {
						if (l1 + l2 + l3 +l4 == cur) count++;
						for (int l5= 1; l5 <= 3; l5++) {
							if (l1 + l2 + l3 + l4 +l5== cur) count++;
							for (int l6 = 1; l6 <= 3; l6++) {
								if (l1 + l2 + l3 + l4 + l5 +l6 == cur) count++;
								for (int l7 = 1; l7 <= 3; l7++) {
									if (l1 + l2 + l3 + l4 + l5 + l6 +l7 == cur) count++;
									for (int l8 = 1; l8 <= 3; l8++) {
										if (l1 + l2 + l3 + l4 + l5 + l6 + l7 +l8 == cur) count++;
										for (int l9 = 1; l9<= 3; l9++) {
											if (l1 + l2 + l3 + l4 + l5 + l6 + l7 + l8 + l9 == cur) count++;
											for (int l10 = 1; l10 <= 3; l10++) {
												if (l1 + l2 + l3 + l4 + l5 + l6 + l7 + l8 + l9 + l10 == cur) count++; //다 더했을 때 값이 나오면 count를 1 증가
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		printf("%d\n", count);
	}
}


//추가 풀이
//factorial을 활용해 풀어봤지만 메모리 초과가 나옴
/*int factorial(int start, int end, int result) {
	if (end == start) return result;
	result *= end;
	factorial(start, end - 1, result);
}

int main() {
	int n, cur;
	int sum, count;
	scanf_s("%d", &n);
	for (int i = 0; i < n; i++) {
		count = 0;
		scanf_s("%d", &cur);
		for (int l3 = cur / 3; l3 >= 0; l3--) {
			sum = cur-(3*l3);
			for (int l2 = 0; sum >= l2*2; l2++) {
				sum-=(2 * l2);

				count += (factorial(sum, l3+l2+ sum, 1)/ (factorial(0, l3, 1)*factorial(0, l2, 1)));
			}
		}
		printf("%d\n", count);
	}
}*/
