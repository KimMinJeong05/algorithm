import java.util.Scanner;

public class Main {
	
	static int N;
	static int M;
	static int[] answer;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		// input
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		answer = new int[M];
		
		seq(0,1);
		System.out.println(sb.toString());
	}
	
    // 조합
	static void seq(int cur, int start) {
		if(cur==M) {
			for(int data: answer) {
				sb.append(data);
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<=N;i++) {
			answer[cur]=i;
			seq(cur+1, i+1);
		}
	}
}
