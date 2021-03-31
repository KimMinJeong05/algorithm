//13305번 - 주유소
//그리디
#include<iostream>
#include<vector>
using namespace std;

int main() {
	int city_num;//정류장 개수
	cin >> city_num;
	vector<long> km(city_num - 1);//길이
	vector<long> oil(city_num);//정류장 기름 비용
	for (int i = 0; i < city_num - 1; i++) cin >> km[i];
	for (int i = 0; i < city_num; i++) cin >> oil[i];

	//첫번째 정류장은 다음 정류장까지 꼭 사용해야함
	long curoil = oil[0];
	long result = curoil*km[0];
    //마지막 정류장의 비용을 사용할 수 없으므로 i< city_num-1로 한다.
	for (int i = 1; i < city_num-1; i++) {//두번째 정류장부터 갱신
		if (curoil > oil[i]) curoil = oil[i];
		result += curoil*km[i];
	}
	cout << result;
}
