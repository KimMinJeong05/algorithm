import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * [풀이]
 * 며칠이 지나면 다 익게되는지 알고싶으므로 -> 4방 bfs
 * 토마토가 없으면 bfs에서 제외
 * [결과]
 */

public class Main {

	static int M,N; // 가로 칸, 세로 칸 수
	static int[][] box;
	static List<int[]> doneTmt; // 익은 토마토 좌표
	static int totalTmt; // 안익은 토마토 개수
	
	public static void main(String[] args) throws IOException {
		input();
	
		System.out.println(bfs());
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		
		box=new int[N][M];
		doneTmt=new ArrayList<int[]>();
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				box[i][j]=Integer.parseInt(st.nextToken());
				if(box[i][j]==1) doneTmt.add(new int[] {i,j});
				else if(box[i][j]==0) totalTmt++;
			}
		}
	}
	
	private static int bfs() {
		int[] dr= {0,0,1,-1}; // 4방 탐색
		int[] dc= {1,-1,0,0};
		Queue<int[]> queue=new ArrayDeque<int[]>(); // 좌표, 시간
		
		// 원래 익은 토마토 다 queue에 넣어 초기화하기
		for(int[] t:doneTmt) {
			queue.add(new int[] {t[0],t[1],0});
			box[t[0]][t[1]]=-1; // 방문체크는 원래 배열에 -1으로 대신하기
		}
		
		int count=0, nr, nc; // 익게된 토마토 개수
		int[] cur=null;
		while(!queue.isEmpty()) {
			cur=queue.poll();
			
			for(int i=0; i<dr.length; i++) {
				nr=cur[0]+dr[i]; nc=cur[1]+dc[i];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M || box[nr][nc]!=0) continue;
				
				queue.add(new int[] {nr,nc,cur[2]+1});
				box[nr][nc]=-1;
				if(++count==totalTmt) return cur[2]+1;
			}
		}
		
		if(cur==null || count!=totalTmt) return -1;
		return cur[2];
	}
}