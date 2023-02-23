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
 * 	  -> 알파벳 set을 만든 후 사용하면 remove한다.
 *    -> 알파벳 set에 없으면 방문하지 않는다. 
*/

public class Main {
	
	static int R,C;
	static char[][] map;
	static Set<Character> alphabet; // 전체 알파벳 집합 
	static int[] dr= {1,-1,0,0};
	static int[] dc= {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		input();
		
		alphabet.remove(map[0][0]);
		System.out.println(dfs(0,0,1));
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens=new StringTokenizer(br.readLine(), " ");
		
		R=Integer.parseInt(tokens.nextToken());
		C=Integer.parseInt(tokens.nextToken());
		
		alphabet=new HashSet<Character>();
		map=new char[R][];
		for(int i=0; i<R; i++) {
			map[i]=br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				alphabet.add(map[i][j]);
			}
		}
	}
	// 최대 방문 횟수 구함 
	private static int dfs(int cr, int cc, int cnt) {
		int nr,nc, maxCnt=cnt;
		
		for(int i=0; i<4; i++) {
			nr=cr+dr[i]; nc=cc+dc[i];
			if(nr<0 || nr>=R || nc<0 || nc>=C || !alphabet.contains(map[nr][nc])) continue;

			alphabet.remove(map[nr][nc]);
			maxCnt=Math.max(maxCnt,dfs(nr,nc, cnt+1));
			alphabet.add(map[nr][nc]);
		}
		return maxCnt;
	}
	
}