import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
 * [핵심 로직]
 * 1. 구역은 BFS로 계산한다.
 * 2. 두사람의 조건이 다르므로 따로 구역을 구한다.
 * 
 * -> 두 사람 각각 BFS로 구역을 계산한다.
 * [주의할 점]
 * 방문 체크를 방문시에 하면 queue에 불필요한 정보들이 더 들어감
 * -> queue에 넣을 때 방문 체크를 한다.
 */

public class Main {
	
	static int N;
	static char[][] image;
	static int[] dr=new int[] {1,0,-1,0}; // 상,우,하,좌
	static int[] dc=new int[] {0,1,0,-1};
	
	public static void main(String[] args) throws IOException{
		input();
		
		// 적록색약이 아닌 사람과 적록색약인 사람의 구역을 따로 구한다.
		System.out.printf("%s %s",searchArea(0), searchArea(1));
	}

	private static void input() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		
		image=new char[N][];
		for(int i=0; i<N; i++) {
			image[i]=br.readLine().toCharArray();
		}
	}
	
	private static int searchArea(int type) {
		boolean[][] visited=new boolean[N][N];
		int count=0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					bfs(type, i, j, visited);
					count++;
				}
			}
		}
		return count;
	}
	private static void bfs(int type, int cr, int cc, boolean[][] visited) {
		Queue<int[]> queue=new ArrayDeque();
		
		queue.add(new int[] {cr,cc});
		visited[cr][cc]=true;
		
		int cur[];
		int nr, nc;
		while(!queue.isEmpty()) {
			cur=queue.poll();
			cr=cur[0]; cc=cur[1];
			
			for(int i=0; i<dr.length; i++) {
				nr=cr+dr[i]; nc=cc+dc[i];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
				if(visited[nr][nc]) continue;
				
				// 색이 같으면, 무조건 같은 구역
				// 색이 다르고 다른 색이 R과 G이고 type==1(색약)이면 같은 구역
				if(image[cr][cc]==image[nr][nc]) {
					queue.add(new int[] {nr,nc});
					visited[nr][nc]=true;
				}
				else if(type==1 && (image[cr][cc]=='R'||image[cr][cc]=='G') && (image[nr][nc]=='R'||image[nr][nc]=='G')) {
					queue.add(new int[] {nr,nc});
					visited[nr][nc]=true;
				}
			}
		}
	}
}