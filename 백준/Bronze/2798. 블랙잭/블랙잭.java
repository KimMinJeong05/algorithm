import java.util.Scanner;

/*
 * 조합
 */

public class Main {
	
	static int N, M;
	static int[] cards; 
	static int max;

	public static void main(String[] args) {
		// input
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		cards = new int[N];
		for(int i=0; i<N; i++) {
			cards[i] = sc.nextInt();
		}
		combi(0,0,0);
		System.out.println(max);
	}
	
	// 구간합 계산
	static void combi(int cur, int start, int count) {
		if(cur==3) {
			if(count>max && count<=M) max =count;
			return;
		}
		
		for(int i=start; i<N;i++) {
			combi(cur+1,i+1,count+cards[i]);
		}
	}
}
