//1744번 - 수 묶기
//그리디
// - 는 - 끼리 묶거나 그냥 혼자 두어야 함
// 최대한 큰 값끼리 묶는다.

// 1. 현재 index의 값이 양수:
//    if: 다음 index의 값이 1
//         1과 곱하면 그 값이 그대로 나오기 때문에, 1은 곱하지말고 따로 더해줘야한다. (이것을 고려하지 못해서 헤맸다..)
//         현재 값과 1을 더해주므로 index를 2개 올려준다.
//    else if: 다음 index의 값이 0보다 큰 수
//         1이 아닌 양수끼리는 곱하는 것이 더 크기때문에 두 수를 곱해준다.
//         현재 값과 다음 값이 곱해졌으므로 index를 2개 올려준다.
//    else: 다음 index의 값이 음수  
//         양수와 음수를 곱하는 건 더욱더 마이너스가 되는 거기 때문에 현재 양수값을 곱해주지 않고 더해준다.
//         현재 값만 더해줬으므로 index를 1개 올려준다.

// 2. 현재 index의 값이 0:
//    if: 뒤에 남은 마이너스의 값이 짝수개이면
//         마이너스끼리 곱하면 양수가 되므로, 현재 값과 다음 값을 곱해준다.
//         현재 값과 다음 값이 곱해졌으므로 index를 2개 올려준다.
//    else: 뒤에 남은 마이너스의 값이 홀수개이면
//         마이너스가 홀수개일때 마이너스가 가장 작은 것이 더해지는 것이 좋으므로 현재 값을 곱해주지 않고 더해준다.
//         현재 값만 더해줬으므로 index를 1개 올려준다.
//         이 else문은 처음에 한번 걸리고 다음엔 계속 마이너스가 짝수개여서 걸리지 않을 것이다.

// 3. 현재 index의 값이 음수:
//    if: 뒤에 남은 마이너스의 값이 짝수개이면
//         마이너스끼리 곱하면 양수가 되므로, 현재 값과 다음 값을 곱해준다.
//         현재 값과 다음 값이 곱해졌으므로 index를 2개 올려준다.
//    else: 뒤에 남은 마이너스의 값이 홀수개이면
//         마이너스가 홀수개일때 마이너스가 가장 작은 것이 더해지는 것이 좋으므로 현재 값을 곱해주지 않고 더해준다.
//         현재 값만 더해줬으므로 index를 1개 올려준다.
//         이 else문은 처음에 한번 걸리고 다음엔 계속 마이너스가 짝수개여서 걸리지 않을 것이다.

#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	int size;
	cin >> size;
	vector<int> v(size);
	for (int i = 0; i < size; i++) cin >> v[i];
	sort(v.rbegin(), v.rend()); //내림차순 정렬

	int idx = 0, temp1, temp2; 
	int result = 0;
	while (idx < size) {
		temp1 = v[idx];
		if (idx == size - 1) {
			result += temp1;
			break;
		}
		temp2 = v[idx + 1];

		if (temp1 > 0) { //1. 현재가 양수
			if (temp2 == 1) {// 1이면 곱하지않고 더하는 것이 더 크다
				result += temp1 + temp2; //곱하지않고 더하기
				idx += 2;
			}
			else if (temp2 > 0) { 
				result += temp1*temp2;
				idx += 2;
			}
			else { // temp1 <=0
				result += temp1;
				idx += 1;
			}
		}
		else if (temp1 == 0) { //2. 현재가 0
			if ((size - idx - 1) % 2 == 0) { // 마이너스가 짝수개(묶으면 양수됨), 자기자신인 0도 빼야해서 -index-1을 한다.
				result += temp1;
				idx += 1;
			}
			else { // 마이너스가 홀수개 -> 0을 곱해서 없애줌(그럼 뒤의 마이너스가 묶으면 곱하면 양수)
				result += temp1*temp2; 
				idx += 2;
			}
		}
		else { //3. 현재가 음수
			if ((size - idx) % 2 == 0) { // 마이너스가 짝수개(묶으면 양수됨)
				result += temp1*temp2; 
				idx += 2;
			}
			else {
				result += temp1;
				idx += 1;
			}
		}
	}

	cout << result;
}
