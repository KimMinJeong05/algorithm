//백준 1806번 - 부분합
//브루트포스
//두개의 포인터를 만들어 시작부분과 끝부분의 뒤를 가리키도록 한다.
//start포인터가 end포인터보다 커지면 정답이 없다는 것이다.(모두를 더해도 s보다 작다는 것이다)

//1. sum이 s보다 같거나 크면 len과 비교해 현재 길이가 작으면 len을 갱신해준다.
//   이후 다른 case를 찾기위해 start포인터를 1 증가한다.
//   이때 원래 start포인터의 값을 빼주어야한다.
//2. sum이 s보다 작으면, 더 뒤의 값을 더해주기 위해 end를 더하고
//   end를 1 증가한다. (end의 앞의 값까지 더해주기 때문이다)


#include<stdio.h>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	vector<int> v;
	int n, s, temp;
	scanf("%d %d", &n, &s);
	for (int i = 0; i < n; i++) {
		scanf("%d", &temp);
		v.push_back(temp);
	}

	int start=0, end=0;
	int sum = 0;
	int len = n+1;
	while (start<=end && end<=n) {
		if (sum >= s) {
			if (len > end - start) len = end - start;
			sum -= v[start++];
		}
		else {
			if (end >= n) break;
			sum += v[end++];
		}
	}
	
	if (len == n + 1) printf("0");
	else printf("%d", len);
}
