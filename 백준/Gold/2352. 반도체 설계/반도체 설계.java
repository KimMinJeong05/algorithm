import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 완전 탐색으로 풀면 시간 초과(N<=40000)
 * 
 * LIS 중 시간복잡도가 O(nlong)인것을 사용
 * 1. select한 요소를 저장할 리스트(result) 선언
 * 2. 첫번째 port를 result에 추가
 * 3. port의 두번째부터 마지막까지 아래 4,5번 반복
 * 4. result의 마지막 요소보다 현재 port값이 크면 result 뒤에 추가
 * 5. result의 마지막 요소보다 현재 port값이 작으면
 * 	  -> result를 마지막 요소부터 첫번재 요소까지 탐색하면서 현재 port보다 큰 값중 가장 작은 값을 찾음
 *    -> 찾은 result값과 현재 port 값을 교환.
 * 6. result의 크기가 최대 port 개수
 */

public class Main {
	
	static int n;
	static int[] ports;
	static List<Integer> result;
	
	public static void main(String[] args) throws IOException {
		input();
		
		LIS();
		
		System.out.println(result.size());
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		
		result=new ArrayList<>();
		ports=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
	private static void LIS() {
		result.add(ports[0]);
		
		for(int i=1; i<n; i++) {
			// 새로운 값이 이전 값들보다 크면 추가
			if(result.get(result.size()-1)<ports[i]) {
				result.add(ports[i]);
				continue;
			}
			
			// 새로운 값이 이전 값들 보다 하나라도 작거나 같은 값이면 자신보다 큰 값중 가장 작은 값과 교환
			// 제일 작은 값이면 0번째와 교환
			int r;
			for(r=result.size()-1; r>=0; r--) {
				if(result.get(r)<ports[i]) break;
			}
			result.set(r+1, ports[i]);
		}
	}
}