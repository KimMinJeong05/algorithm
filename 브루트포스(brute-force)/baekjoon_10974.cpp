//10974번 모든 순열
//브루트포스
#include<iostream>
#include<string>
using namespace std;
int n;//받아오는 숫자
void search(string s, bool check[9], int cur) {
	if (cur == n) {//s에 들어간 숫자가 n개이면 출력하고 끝내기
		cout << s << "\n";
		return;
	}
	for (int i = 1; i <= n; i++) {
		if (!check[i]) {//아직 숫자가 들어가지 않았다면 s에 추가
			check[i] = true;
			search(s + to_string(i)+ " ", check,cur+1);
			check[i] = false;//다음 순서의 순열을 위해 false로 바꾸기
		}
	}
}
int main() {
	cin >> n;
	int arr[9] = { 0,1,2,3,4,5,6,7,8};
	bool check[9] = { false, };
	search("", check,0);
}
