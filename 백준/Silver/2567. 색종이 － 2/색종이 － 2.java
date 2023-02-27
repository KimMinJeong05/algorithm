import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*
 * 풀이
 * 현재 위치에서 옆에 흰색인 개수만큼 total에 더한다
 */

public class Main {
	
	static int N;
	static int[][] black;
	static int[][] map; // 흰 스카프
	static int total; // 최종 둘레
	static int[] dx= {0,0,1,-1};
	static int[] dy= {1,-1,0,0,};
	static boolean[][] checked=new boolean[101][101];
	
	public static void main(String[] args) throws IOException{
		input();
		
		blackPut(); // 검은 스카프 배치
				
		// bfs를 실행하면서 겹친 부분의 둘레를 빼준다.
		for(int i=0; i<101; i++) {
			for(int j=0; j<101; j++){
				
				if(map[i][j]!=0 && !checked[i][j]) {
					int cnt=0;
					for(int c=0; c<4; c++) {
						int ax= i+dx[c], ay=j+dy[c];
						if(map[ax][ay]==0) {
							cnt++;
						}
					}
					if(cnt>0) {
						total+=cnt;
						bfs(i,j);
					}
				}
				
			}
		}
		
		System.out.println(total);
	}
	
	// 입력 부분
	public static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		
		map=new int[102][102];
		black=new int[N][];
		for(int i=0; i<N; i++) {
			black[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
	}
	
	// 검은 색 스카프 배치
	public static void blackPut() {
		int cx, cy;
		for(int i=0; i<N; i++) {
			cx=black[i][0]; cy=black[i][1];
			map[cx][cy]+=1;
			map[cx+10][cy]-=1;
			map[cx][cy+10]-=1;
			map[cx+10][cy+10]+=1;
		}
		
		for(int i=0; i<102; i++) {
			for(int j=1; j<102; j++){
				map[i][j]+=map[i][j-1];
			}
		}
		for(int i=0; i<102; i++) {
			for(int j=1; j<102; j++){
				map[j][i]+=map[j-1][i];
			}
		}
	}
	
	// 겹친 부분의 사각형 둘레를 구하고, 계산
	public static void bfs(int x, int y) {
		Queue<int[]> queue=new ArrayDeque<>();
		
		queue.add(new int[] {x,y});
		checked[x][y]=true;
		
		int[] cur; 
		int nx, ny, cnt;
		int ax, ay; // 주변 요소들 확인
		while(!queue.isEmpty()) {
			cur=queue.poll();
			
			for(int i=0; i<4; i++) {
				nx=cur[0]+dx[i]; ny=cur[1]+dy[i];
				cnt=0;
				
				if(nx<0||nx>100||ny<0||ny>100||checked[nx][ny]||map[nx][ny]==0) continue;
				
				for(int j=0; j<4; j++) {
					ax= nx+dx[j]; ay=ny+dy[j];
					if(ax<0||ax>100||ay<0||ay>100||map[ax][ay]==0) {
						cnt++;
					}
				}
				
				checked[nx][ny]=true;
				if(cnt>0) {
					total+=cnt;
					queue.add(new int[] {nx,ny});
				}
			}
		}
	}
}