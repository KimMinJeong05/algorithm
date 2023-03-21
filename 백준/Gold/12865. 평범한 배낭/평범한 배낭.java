import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * [풀이]	
 * knapsack 문제 -> dp문제 
 * 무게 중 가장 큰 값을 유지하며 가방을 하나씩 처리
 * 현재 가방과 다른 가방으로 한 무게가 나올 수 있으면 최근 다른 가방의 최대값과 현재 가방의 가치를 더해줌 
 * 
 * [틀린 풀이]
 * dfs하면서 백트레킹 -> 예외 상황 발생 
 */
public class Main {
	
	static int n,k;
	static int[][] bag;
	static int[][] dpBag;
	
	public static void main(String[] args) throws IOException {
		input();
		
		dp();
		
		System.out.println(dpBag[n][k]);
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		n=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		
		bag=new int[n+1][2];
		for(int i=1; i<=n; i++) {
			st=new StringTokenizer(br.readLine()," ");
			bag[i][0]=Integer.parseInt(st.nextToken());
			bag[i][1]=Integer.parseInt(st.nextToken());
		}
		
		dpBag=new int[n+1][k+1];
	}
	private static void dp() {
		int cw,cv;
		for(int i=1; i<=n; i++) {
			cw=bag[i][0]; cv=bag[i][1];
			for(int j=1; j<=k; j++) {
				if(j==cw) dpBag[i][j]=Math.max(dpBag[i-1][j], cv);
				else dpBag[i][j]=Math.max(dpBag[i-1][j], dpBag[i][j-1]);
				
				if(j-cw>=0) dpBag[i][j]=Math.max(dpBag[i][j], dpBag[i-1][j-cw]+cv);
			}
		}
	}
}