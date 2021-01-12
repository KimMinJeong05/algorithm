//백준 10430번 - 나머지
//문제 그대로 풀면 된다.
#include<iostream>
using namespace std;

int main() {
	int A,B, C;
	scanf_s("%d %d %d", &A, &B, &C);
	printf("%d\n", (A + B) % C);
	printf("%d\n", ((A%C) + (B%C)) % C);
	printf("%d\n", (A*B) % C);
	printf("%d\n",((A%C) * (B%C)) % C);
}
