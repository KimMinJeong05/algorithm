//2805번 - 나무 자르기
//이진 탐색
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	int tree_num, want_len; //나무 수, M
	cin >> tree_num >> want_len;
	vector<int> tree_len(tree_num); //나무 길이
	for (int i = 0; i < tree_num; i++) cin >> tree_len[i];

	sort(tree_len.begin(), tree_len.end()); //이진 탐색을 위해 정렬

	long low = 0, high = tree_len[tree_num-1], mid; //H의 범위의 최소값, 최대값, 현재 임의의 값
	long curM, result=0; //H길이로 자른 후 갖게 되는 나무의 길이 합, result: 가능한 H의 최대값
	while (low<=high) {
		mid = (low + high) / 2;
		curM = 0;
		for (int i = 0; i < tree_num; i++) {
			if (tree_len[i] - mid > 0) curM += tree_len[i] - mid;
		}
		if (curM > want_len) {
			low = mid + 1; //curM이 작아져야 하므로 반대인 mid가 커져야 한다. 
			if (result < mid) result = mid;
		}
		else if (curM < want_len) high = mid - 1; //curM이 커져야 하므로 반대인 mid가 작아져야 한다. 
		else { //curM이 M인 want_len과 같아졌을 경우 멈춤
			result = mid;
			break;
		}
	}
	cout << result;
}
