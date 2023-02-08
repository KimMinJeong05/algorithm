import java.util.Scanner;

public class Main {
	
	static int N;
	static int M;
	static int[] arr;
	static boolean[] used;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		// nCm 순열
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[M];
		used = new boolean[N+1];
		
		dfs(0);
		System.out.println(sb.toString());
	}
	
	static void dfs(int nowIdx) {
		if(nowIdx>=M) {
			for(int data : arr) {
				sb.append(data);
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if (used[i]) continue;
			arr[nowIdx] = i;
			used[i]=true;
			
			dfs(nowIdx+1);
			
			arr[nowIdx] = 0;
			used[i]=false;
		}
	}
}
