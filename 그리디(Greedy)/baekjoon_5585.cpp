//5585번 - 거스름돈
//그리디
#include<iostream>
using namespace std;

int main() {
  // 낸 돈, 남은 거스름돈, 동전의 개수
	int money, returnMoney, count=0;
	cin >> money;
  
	returnMoney = 1000 - money;
	count += (returnMoney / 500);
	returnMoney %= 500;
	count += (returnMoney / 100);
	returnMoney %= 100;
	count += (returnMoney / 50);
	returnMoney %= 50;
	count += (returnMoney / 10);
	returnMoney %= 10;
	count += (returnMoney / 5);
	returnMoney %= 5;
	count += returnMoney;
  
	cout << count;
}
