import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 핵심 로직
 * 1. 누적합
 * 2. 한 값이 여러번 누적되지 않도록 조절
 * 	  sums[i][j] = sums[i-1][j]+sums[i][j-1]-sums[i-1][j-1] + ([i][j]의 원래 값)
 * 3. 정답 값은 (0,0)~(x2,y2) - (0,0)~(x1-1,y2) - (0,0)~(x2,y1-1) + (0,0)~(x1-1,y1-1)
 */

public class Main {
	
	static int N, M;
	static int[][] sums; // 각 인덱스까지의 누적 합
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		sums = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			tokens = new StringTokenizer(in.readLine(), " ");
			int j=1;
			while(tokens.hasMoreTokens()) {
				
				sums[i][j] = sums[i-1][j]+sums[i][j-1]-sums[i-1][j-1]+Integer.parseInt(tokens.nextToken());
				j++;
			}
		}
		
		// case
		for (int k = 0; k < M; k++) {
			tokens = new StringTokenizer(in.readLine()," ");
			sb.append(cal(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	// 구간합 계산
	static int cal(int x1, int y1, int x2, int y2) {
		return sums[x2][y2] - sums[x1-1][y2] - sums[x2][y1-1] + sums[x1-1][y1-1];
	}
}
