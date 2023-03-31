import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * [풀이]
 * dfs로 둘레 찾기
 * 다음 노드가 0이면 queue에 넣고 1이면 0으로 바꿔주고 queue에 넣지않음 
 */

public class Main {
	
	static int n,m;
	static int[][] map;
	static int total;
	
	public static void main(String[] args) throws IOException{
		int[] dr= {0,1,-1,0};
		int[] dc= {1,0,0,-1};
		
		input();
		
		int time=0, cnt=0;
		while(total>0) {
			time++;
			cnt=cheeze(dr, dc);
		}
		
		System.out.println(time);
		System.out.println(cnt);
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		map=new int[n][m];
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) total++;
			}
		}
	}
	
	private static int cheeze(int[] dr, int[] dc) {
		int cnt=0;
		boolean[][] visited=new boolean[n][m];
		int[][] temp=new int[n][];
		for(int i=0; i<n; i++) temp[i]=Arrays.copyOf(map[i], m);
		
		Queue<Point> queue=new ArrayDeque<Point>();
		queue.add(new Point(0,0));
		visited[0][0]=true;
		
		Point cur;
		int nr, nc;
		while(!queue.isEmpty()) {
			cur=queue.poll();
			
			for(int i=0; i<dr.length; i++) {
				nr=cur.r+dr[i]; nc=cur.c+dc[i];
				if(nr<0||nr>=n||nc<0||nc>=m||visited[nr][nc]) continue;
				
				if(map[nr][nc]==1) {
					temp[nr][nc]=0;
					cnt++;
				}else {
					queue.add(new Point(nr,nc));
				}
				visited[nr][nc]=true;
			}
		}
		
		total-=cnt;
		map=temp;
		
		return cnt;
	}
	
	static class Point{
		int r,c;
		public Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}
}