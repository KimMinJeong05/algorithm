//2231번 분해합
//브루트포스
#include<iostream>
using namespace std;

int main() {
	int num,n=1,cur;
	cin >> num;
	while (n<=1000000) {
    	//n과 각 자리의 숫자를 더해 cur에 저장
		cur = n+ (n % 10) + (n/10)%10 + (n / 100) % 10 + (n / 1000) % 10 + (n / 10000) % 10
			+ (n / 100000) % 10 + (n / 1000000) % 10;
		if (cur == num) {
			cout << n;
			return 0;
		}
		n++;
	}
	cout << 0;
}
