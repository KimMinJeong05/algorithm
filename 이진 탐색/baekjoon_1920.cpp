//1920번 - 수 찾기
//이진 탐색
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int Nsize, Msize;
vector<int> n;
void BSearch(int target) {
	int start = 0, end = Nsize-1, mid;

	//start와 end가 같은 것도 해야하는 이유: 배열에 값 하나만 남은 경우도 생각해야한다.
	while (start <= end) { 
		mid = (start + end) / 2;
		if (n[mid] < target) start = mid + 1;
		else if (n[mid] > target) end = mid - 1;
		else { 
			cout << "1\n";
			return;
		}
	}
	cout << "0\n";
}

int main() {
	cin >> Nsize;
	n.resize(Nsize); //N배열 사이즈 정해주기
	for (int i = 0; i < Nsize; i++) cin >> n[i];
	cin >> Msize;
	vector<int> m(Msize); //찾아야 하는 값이 있는 M배열
	for (int i = 0; i < Msize; i++) cin >> m[i];

	sort(n.begin(), n.end());

	for (int i = 0; i < Msize; i++) {
		BSearch(m[i]);
	}
}
