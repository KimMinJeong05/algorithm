//백준 2309(일곱 난쟁이)
//브루트포스
//키의 합이 100인 7명을 찾는 것보다
//2명의 키의 합이 (총 합-100)인 것을 찾는 것이 더 간편하다.

//9명중 2명을 뽑을 수 있는 모든 경우의 수를 살펴본다.
#include<stdio.h>
#include<algorithm>
using namespace std;

int main() {
	int height[9], sum=0;
	for (int i = 0; i < 9; i++) {
		scanf_s("%d", &height[i]);
		sum += height[i];
	}
	sort(height, height + 9); //오름차순으로 출력해야하므로 sort해준다.
	for (int i = 0; i < 9; i++) {
		for (int j = i+1; j < 9; j++) {
			if (height[i] + height[j] == sum - 100) {//7난쟁이가 아닌 인덱스를 찾는다.
				for (int k = 0; k < 9; k++) {
					if (k == i || k == j) continue;;
					printf("%d\n", height[k]);
				}
				return 0;
			}
		}
	}
}
