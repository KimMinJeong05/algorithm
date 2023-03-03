import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * [조건]
 * 아기상어와 크기가 같으면, 지나갈 수 있음
 * 아기상어보다 물고기의 크기가 크면, 지나갈수 없음
 * 아기상어보다 물고기의 크기가 작으면, 잡아먹고 지나갈수있음
 * 
 * 아기상어 4방탐색
 * 먹을 수 있는 물고기 있으면 -> 거리가 가장 가까운 물고기 먹으러(거리가 같으면 가장 위쪽+왼족에 있는 물고기) + 최소 칸을 지나서
 * 자신의 크기만큼 물고기를 먹으면 1증가
 * 물고기가 없어질때까지 실행
 * [풀이]
 * 아기상어 4방탐색 시 가까운 곳 + 위쪽 + 왼쪽 먼저 가야하므로 상,좌,우,하 순으로 BFS를 한다.
 * 
 * 주의할 점.
 * BFS 시 push할때 물고기 먹으면 안됨. 같은 시간내에 간 것을 위치 기준으로 다시 정렬 해야함
 * -> Queue가 아닌 PriorityQueue 사용
 * [결과]
 */

public class Main {
	
	static class Fish implements Comparable<Fish>{
		public int r, c, size;

		public Fish(int r, int c, int size) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
		}
		@Override
		public int compareTo(Fish o) {
			return (Math.abs(this.r-shark.r)+Math.abs(this.c-shark.c))-(Math.abs(o.r-shark.r)+Math.abs(o.c-shark.c));
		}
	}
	
	static int N;
	static int[][] sea;
	static int fishCnt, time, sizeEatCnt;
	static Fish shark;
	static int[] dr= {-1,0,0,1}; // 상 좌 우 하
	static int[] dc= {0,-1,1,0};

	public static void main(String[] args) throws IOException {
		input();
		
		int curTime=0, eatCnt=0;
		while(eatCnt<fishCnt) {
			curTime=eatFish();
			if(curTime==-1) break;
			eatCnt++;
			time+=curTime;
		}
		
		System.out.println(time);
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		sea=new int[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				sea[i][j]=Integer.parseInt(st.nextToken());
				if(sea[i][j]==9) {
					shark=new Fish(i,j, 2);
					sea[i][j]=0;
				}
				else if(sea[i][j]!=0) fishCnt++;
			}
		}
	}
	
	private static int eatFish() {
		PriorityQueue<int[]> queue=new PriorityQueue<int[]>((o1,o2)->{
			if(o1[2]==o2[2]) {
				if(o1[0]==o2[0]) return o1[1]-o2[1];
				return o1[0]-o2[0];
			}
			return o1[2]-o2[2];
		}) ;
		boolean[][] visited=new boolean[N][N];
		
		queue.add(new int[]{shark.r, shark.c, 0});
		visited[shark.r][shark.c]=true;
		
		int[] cur;
		int nr, nc;
		while(!queue.isEmpty()) {
			cur=queue.poll();
			
			// 물고기 잡아먹음
			if(sea[cur[0]][cur[1]]!=0 && sea[cur[0]][cur[1]]<shark.size) {
				sea[cur[0]][cur[1]]=0;
				sizeEatCnt++;
				shark.r=cur[0]; shark.c=cur[1];
				if(sizeEatCnt==shark.size) {
					shark.size++;
					sizeEatCnt=0;
				}
				return cur[2];
			}
			
			for(int i=0; i<dr.length; i++) {
				nr=cur[0]+dr[i]; nc=cur[1]+dc[i];
				if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc]) continue;
				
				visited[nr][nc]=true;
				
				if(sea[nr][nc]>shark.size) continue;
				
				queue.add(new int[] {nr,nc,cur[2]+1});
			}
		}
		return -1;
	}
}