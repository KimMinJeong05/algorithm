import java.util.Scanner;

/*
 * 1 <= N, M <= 100,000 이기 때문에
 * 최악의 상황에 O(N*M)으로 1초를 넘긴다
 * 
 * 핵심 로직
 * 구간 합이므로 합을 내야하는 것이 뭉쳐져 있다.
 * 4~7합 = 1~7합 - 1~3합 이므로
 * 배열의 값을 받을 때 인덱스1부터 각 인덱스까지의 합을 같이 구한다(여기서 인덱스틑 1부터 시작)
 */

public class Main {
	
	static int N, M;
	static int[] nums; // 각 인덱스까지의 누적 합
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		// input
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		nums = new int[N+1];
		for(int k=1; k<=N;k++) nums[k] = nums[k-1]+sc.nextInt();
		
		// case
		for (int k = 0; k < M; k++) {
			sb.append(cal(sc.nextInt(), sc.nextInt()));
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	// 구간합 계산
	static int cal(int i, int j) {
		return nums[j]-nums[i-1]; // i, j도 포함
	}
}
