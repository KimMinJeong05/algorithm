//2805번 - 나무 자르기
//이진 탐색
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	int tree_num, want_len;
	cin >> tree_num >> want_len;
	vector<int> tree_len(tree_num);
	for (int i = 0; i < tree_num; i++) cin >> tree_len[i];

	sort(tree_len.begin(), tree_len.end());

	long low = 0, high = tree_len[tree_num-1], mid;
	long curM, result=0;
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
		else {
			result = mid;
			break;
		}
	}
	cout << result;
}