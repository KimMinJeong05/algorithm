//백준 11723번
//"add", "remove", "check", "togle"은 data도 입력받고, "all", "empty"는 data를 입력 받지 않는다.
//위의 점 때문에 조금 헤맸다.


#include<iostream>
#include<string>
#include<vector>
using namespace std;

// 방법 1
// 직접 값을 넣지 않고 배열의 인덱스 값을 입력 받은 값으로 하고,
// 배열 인덱스의 해당 값이 true이면 값이 있는 거고, false이면 값이 없는 것이다.
// 입출력 향상을 안 하면 시간 초과가 된다.
// cout 대신 puts를 사용한다.
int main() {
	ios_base::sync_with_stdio(0);// 입출력 속도 향상
	cin.tie();

	bool arr[21] = { false }; // 인덱스 1~20을 사용한다. 인덱스 0 은 사용하지 않는다.
	int n;
	string com; int data;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> com;
		if (com == "add") {
			cin >> data;
			arr[data] = true;
		}
		else if (com == "remove") {
			cin >> data;
			arr[data] = false;
		}
		else if (com == "check") {
			cin >> data; 
			arr[data] ? puts("1") : puts("0");
		}
		else if (com == "toggle") {
			cin >> data;
			arr[data]=!arr[data];
		}
		else if (com == "all") {
			for (int i = 1; i <= 20; i++) {
				arr[i] = true;
			}
		}
		else if (com == "empty") {
			for (int i = 1; i <= 20; i++) {
				arr[i] = false;
			}
		}
	}
}

//방법2 -> 시간초과
//vector를 사용해 push_back과 erase, clear를 사용해 풀었다.
/*
vector<int> v;

bool check(int data) {
	for (int i = 0; i < v.size(); i++) {
		if (v.at(i) == data) return true;
	}
	return false;
}
void add(int data) {
	if (!check(data)) { // data가 v에 없으면
		v.push_back(data);
	}
}
bool remove(int data) {
	for (int i = 0; i < v.size(); i++) {
		if (v.at(i) == data) {
			v.erase(v.begin() + i, v.begin() + i + 1); //i번째 인덱스 값을 지운다.
			return true;
		}
	}
	return false;
}
void toggle(int data) {
	if (!remove(data)) { // remove함수를 통해 data가 있으면 지우고, 없으면 이 함수에서 push한다.
		v.push_back(data);
	}
}
void empty() {
	v.clear(); // v에 있는 값 다 지우기
}
void all() {
	v.clear();
	for (int i = 1; i <= 20; i++) {
		v.push_back(i);
	}
}

int main() {
	int n;
	string com; int data;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> com;
		if (com == "add") {
			cin >> data;
			add(data);
		}
		else if (com == "remove") {
			cin >> data;
			remove(data);
		}
		else if (com == "check") {
			cin >> data;
			cout<<int(check(data))<<"\n";
		}
		else if (com == "toggle") {
			cin >> data;
			toggle(data);
		}
		else if (com == "all") {
			all();
		}
		else if (com == "empty") {
			empty();
		}
	}
}
*/
