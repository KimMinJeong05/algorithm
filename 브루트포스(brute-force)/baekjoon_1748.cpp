//백준 1748 - 수 이어 쓰기1
//브루트포스
//입력받은 n까지 하나씩 접근한다
//이때 for문을 자리수로 잘라서 sum에 자리수를 더해준다.

#include<stdio.h>
using namespace std;

int main() {
	int n, sum=0, i=1;
	scanf_s("%d", &n);
	for (; i < 10 && i <= n; i++) sum += 1;
	for (; i < 100 && i <= n; i++) sum += 2;
	for (; i < 1000 && i <= n; i++) sum += 3;
	for (; i < 10000 && i <= n; i++) sum += 4;
	for (; i < 100000 && i <= n; i++) sum += 5;
	for (; i < 1000000 && i <= n; i++) sum += 6;
	for (; i < 10000000 && i <= n; i++) sum += 7;
	for (; i < 100000000 && i <= n; i++) sum += 8;
	if (n == 100000000) sum += 9;
	printf("%d\n", sum);
}
