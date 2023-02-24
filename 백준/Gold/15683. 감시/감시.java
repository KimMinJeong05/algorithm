import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * [핵심 로직]
 * 방향 인덱스
 * 1번 카메라: {0}, {1}, {2}, {3}
 * 2번 카메라: {0,2}, {1,3}
 * 3번 카메라: {0,1}, {1,2}, {2,3}, {3,0}
 * 4번 카메라: {1,2,3}, {0,2,3}, {0,1,3}, {0,1,2}
 * 5번 카메라: {0,1,2,3}
 * 
 * 1. 1 ≤ N, M ≤ 8 이므로 모든 조합에 대해서 완전 탐색을 해도 된다.
 * 2. 각 카메라가 가능한 모든 방향을 미리 정해둔다. (cameraDir[카메라 번호][가능한 회전][각 회전에서 가능한 방향])
 * 3. 각 조합에 따라 카메라를 돌려가며 카메라가 감시할 수 있는 공간을 count한다.
 */

public class Main {
	
	static int N,M;
	static int[][] map;
	static List<int[]> cameras; // 카메라 정보 {x,y}
	static int[] dr= {-1,0,1,0}; // 상, 우, 하, 좌
	static int[] dc= {0,1,0,-1};
	static int[][][] cameraDir= {{},{{0}, {1}, {2}, {3}},{{0,2}, {1,3}},{{0,1}, {1,2}, {2,3}, {3,0}},{{1,2,3}, {0,2,3}, {0,1,3}, {0,1,2}},{{0,1,2,3}}}; // 각 카메라의 가능한 방향 인덱스
	static int initEmpty=0; // 처음 빈 공간의 개수
	static int minAnswer; // 현재 최소 사각지대 개수
	
	public static void main(String[] args) throws IOException{
		input();
		
		// 카메라가 존재한다면, 카메라의 조합을 만든다.
		if(cameras.size()!=0) { 
			combi(0, cameras.get(0)[0], cameras.get(0)[1], copyMap(map),0);
		}
		
		System.out.println(minAnswer);
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[] temp=br.readLine().split(" ");
		N=Integer.parseInt(temp[0]);
		M=Integer.parseInt(temp[1]);
		
		cameras=new ArrayList<int[]>();
		map=new int[N][];
		for(int i=0; i<N ;i++) {
			map[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int j=0; j<M; j++) {
				if(map[i][j]!=0 &&map[i][j]!=6) cameras.add(new int[]{i,j});
				if(map[i][j]==0) initEmpty++;
			}
		}
		minAnswer=initEmpty;
	}
	// 카메라 조합을 계산한다.
	private static void combi(int curc, int cx, int cy, int[][] curMap, int cnt) {
		if(curc==cameras.size()) return;
		
		for(int i=0; i<cameraDir[map[cx][cy]].length; i++) {
			int[][] temp = copyMap(curMap); // 카메라가 회전할 때마다 변경되지 않은 map을 사용해야하므로 깊은 복사를 한다.
			int tempCnt=cctv(map[cx][cy], cx, cy, i, temp); // 현재 카메라 회전으로 감시하고 count와 map을 갱신한다.

			
			// 마지막 카메라였을 경우 최종 계산
			if(curc+1==cameras.size()) {
				minAnswer=Math.min(minAnswer, initEmpty-(tempCnt+cnt));
				continue;
			}
			// 다음 카메라의 조합
			combi(curc+1, cameras.get(curc+1)[0], cameras.get(curc+1)[1], temp, tempCnt+cnt);
		}
	}
	// 특정 카메라의 특정 회전방향으로 cctv 감시한 결과
	private static int cctv(int cameraNum, int x, int y, int dir, int[][] curMap) {
		int nx, ny,curCnt=0;
		for(int cdir: cameraDir[cameraNum][dir]) {
			nx=x; ny=y;
			while(true) {
				nx+=dr[cdir]; ny+=dc[cdir];
				if(nx<0 || nx>=N || ny<0 || ny>=M) break;
				if(curMap[nx][ny]==6) break;
				
				if(curMap[nx][ny]==0) {
					curMap[nx][ny]=-1;
					curCnt++;
				}
			}
//			System.out.println(nx+" "+ny);
//			for(int i=0;i<N;i++) System.out.println(Arrays.toString(curMap[i]));
//			System.out.println("---");
		}
		return curCnt;
	}
	
	// 2차원 배열 깊은 복사
	private static int[][] copyMap(int[][] origin) {
		int[][] temp=new int[N][];
		for(int i=0; i<N; i++) temp[i]=origin[i].clone();
		
		return temp;
	}
}