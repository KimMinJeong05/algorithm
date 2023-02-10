import java.util.Scanner;

/*
 * 비트마스킹으로 부분집합 만들기
 */

public class Main {
	
	static int N;
	static int[] arrS;
	static int[] arrB;
	static long min = Long.MAX_VALUE;

	public static void main(String[] args) {
		// input
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arrS = new int[N];
		arrB = new int[N];
		for(int i=0;i<N;i++) {
			arrS[i]=sc.nextInt();			
			arrB[i]=sc.nextInt();			
		}
		
		// 부분집합
		subset();
		
		System.out.println(min);
	}
	
	// 부분집합
	static void subset() {
		int sumS=1;
		int sumB=0;
		//부분 집합의 수 = 2^n
		for(int i=1; i<(1<<N);i++) {
			sumS=1;
			sumB=0;
			for(int j=0; j<N; j++) {
				if((i&(1<<j)) != 0) {
					sumS*=arrS[j];
					sumB+=arrB[j];
				}
			}
			min = (min>Math.abs(sumS-sumB)?Math.abs(sumS-sumB):min);
		}
	}
}