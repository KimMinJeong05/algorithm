//16953번 A->B
//a->b로 접근하는 것보다 b->a로 접근하는 것이 더 편리하다.
//b가 짝수면 2로 나눌 수 있고, 홀수 중 일의 자리가 1이면 일의 자리의 1을 삭제할 수 있다.
//b가 홀수인데 일의 자리가 1이 아니면 a로 갈 수 없다.

#include<iostream>
using namespace std;

long long result;
long long a, b;
int main() {
	cin >> a >> b;
	result = 1;

	while (true) {
		if (b == a) { // b->a로 도달했을 경우(성공)
			cout << result;
			return 0;
		}
		else if (b < a) { // b가 a보다 작아진 경우(실패)
			cout << "-1";
			return 0;
		}
		if (b % 2 == 0) { // b가 2의 배수일 경우 2로 곱하기 전으로 돌아갈 수 있음
			result++;
			b /= 2;
		}
		else if(b % 10 == 1) { // b가 홀수에 일의 자리가 1일 경우 숫자의 오른쪽에 1을 추가하기 전으로 돌아갈 수 있음
			result++;
			b /= 10;
		}
		else { // b가 홀수인데 일의 자리가 1이 아닐 경우(실패)
			cout << "-1";
			return 0;
		}
	}
}
