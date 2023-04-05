import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	
	static int n,m,g,r;
	static int[][] map;
	static Point[] canPower;
	static int canSize;
	static int MAX_VALUE=50*50+1;
	static int[][][] allCount;
	static int flower;
	
	static int[] dr= {0,1,0,-1};
	static int[] dc= {1,0,-1,0};
	
	public static void main(String[] args) throws IOException{
		input();
		
		combination(0,0,new int[g+r]);
		
		System.out.println(flower);
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		g=Integer.parseInt(st.nextToken());
		r=Integer.parseInt(st.nextToken());
		
		canPower=new Point[10];
		map=new int[n][m];
		int cur;
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<m; j++) {
				cur=Integer.parseInt(st.nextToken());
				if(cur==0) map[i][j]=MAX_VALUE;
				else if(cur==1) map[i][j]=0;
				else {
					canPower[canSize++]=new Point(i, j, 1);
					map[i][j]=0;
				}
			}
		}
		
		allCount=new int[canSize][n][m];
	}
	
	private static void dfs(int[] gs, int[] rs) {
		int[][][] curMap=new int[2][n][m];
		Queue<Point> queue=new LinkedList<Point>();
		
		Point cur;
		for(int gidx:gs) { //0
			cur=canPower[gidx];
			cur.type='g';
			queue.add(cur);
			curMap[0][cur.r][cur.c]=cur.time;
		}
		for(int ridx:rs) { //1
			cur=canPower[ridx];
			cur.type='r';
			queue.add(cur);
			curMap[1][cur.r][cur.c]=cur.time;
		}
		
		int nr, nc, nt;
		int flowerCnt=0;
		while(!queue.isEmpty()) {
			cur=queue.poll();
			if(curMap[0][cur.r][cur.c]==MAX_VALUE || curMap[1][cur.r][cur.c]==MAX_VALUE) continue;
			
			for(int i=0; i<dr.length; i++) {
				nr=cur.r+dr[i]; nc=cur.c+dc[i]; nt=cur.time+1;
				if(nr<0||nr>=n||nc<0||nc>=m || map[nr][nc]==MAX_VALUE) continue;
				if(curMap[0][nr][nc]==nt && cur.type=='r') {
					flowerCnt++;
					curMap[0][nr][nc]=MAX_VALUE;
					continue;
				}else if(curMap[1][nr][nc]==nt && cur.type=='g') {
					flowerCnt++;
					curMap[1][nr][nc]=MAX_VALUE;
					continue;
				}else if(curMap[1][nr][nc]!=0 || curMap[0][nr][nc]!=0) continue;
				
				queue.add(new Point(nr,nc,nt,cur.type));
				if(cur.type=='g') curMap[0][nr][nc]=cur.time+1;
				else if(cur.type=='r') curMap[1][nr][nc]=cur.time+1;
			}
		}
		
		flower=Math.max(flowerCnt, flower);
	}
	private static void combination(int cur, int cnt, int[] combi) {
		if(cnt==g+r) {
			combinationType(0,0, combi, 0);
			return;
		}
		
		for(int i=cur; i<canSize; i++) {
			combi[cnt]=i;
			combination(i+1, cnt+1, combi);
		}
	}
	private static void combinationType(int cur, int cnt, int[] idxs, int perm) {
		if(cnt==g) {
			int[] gs=new int[g]; int gIdx=0;
			int[] rs=new int[r]; int rIdx=0;
			
			int p=0;
			for(int idx:idxs) {
				if((perm & (1<<p))!=0) gs[gIdx++]=idx;
				else rs[rIdx++]=idx;
				p++;
			}
			dfs(gs,rs);
			return;
		}
		
		for(int i=cur; i<g+r; i++) {
			combinationType(i+1, cnt+1,idxs, perm|(1<<i));
		}
	}
	
	static class Point{
		int r,c, time;
		char type;

		public Point(int r, int c, int time) {
			this.r=r;
			this.c=c;
			this.time=time;
		}
		public Point(int r, int c, int time, char type) {
			this.r=r;
			this.c=c;
			this.time=time;
			this.type=type;
		}
	}
}