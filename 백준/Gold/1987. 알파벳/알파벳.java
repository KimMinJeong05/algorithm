
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * [핵심 로직]
 * 1. 최대 갈 수 있는 칸을 알아야하는데 가는 경로에 따라 갈수있는 알파벳이 다르므로 dfs를 한다. 
 * 2. 방문한 알파벳이면 방문하지 않는다.
 * 	  -> checked 배열을 만들어서 'A':0 ... 'Z':25로 체크한다.
 *    -> checked이 false 방문하지 않는다. 
*/

public class Main {
	
	static int R,C;
	static char[][] map;
	static boolean[] checked; // 'A' .. 'Z'
	static int[] dr= {1,-1,0,0};
	static int[] dc= {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		input();
		
		checked[(int)(map[0][0]-'A')]=true;
		System.out.println(dfs(0,0,1));
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens=new StringTokenizer(br.readLine(), " ");
		
		R=Integer.parseInt(tokens.nextToken());
		C=Integer.parseInt(tokens.nextToken());
		
		checked=new boolean[26];
		map=new char[R][];
		for(int i=0; i<R; i++) {
			map[i]=br.readLine().toCharArray();
		}
	}
	// 최대 방문 횟수 구함 
	private static int dfs(int cr, int cc, int cnt) {
		int nr,nc, maxCnt=cnt;
		
		for(int i=0; i<4; i++) {
			nr=cr+dr[i]; nc=cc+dc[i];
			if(nr<0 || nr>=R || nc<0 || nc>=C || checked[(int)(map[nr][nc]-'A')]) continue;

			checked[(int)(map[nr][nc]-'A')]=true;
			maxCnt=Math.max(maxCnt,dfs(nr,nc, cnt+1));
			checked[(int)(map[nr][nc]-'A')]=false;
		}
		return maxCnt;
	}
	
}