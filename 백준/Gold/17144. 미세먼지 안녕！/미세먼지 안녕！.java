import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * [조건]
 * 미세먼지: 4방향으로 퍼짐. 각각 /5(정수만) 
 * 공기청정기: 위쪽-반시계방향, 아래쪽-시계방 
 * 바람이 불면 미세먼지가 한칸씩 바람 방향으로 밀림 
 * 
 * [핵심 로직]
 * 1. 1초마다 미세먼지, 공기청정기 일을 한다.
 * 2. 미세먼지는 동시에 퍼지는 것이므로 퍼지는 것이 실시간으로 반영되면 안된다.
 * 		-> 새로운 배열을 만들어 퍼지는 것을 새로운 배열에만 더하고 원래 배열에는 변경하지 않는 방법 사용
 * 3. 공기청정기는 각 2개의 사각형으로만 회전하는 것이므로 인덱스를 사용해 회전했다.
 * 		-> 회전하는 정보를 배열에 넣어 사용하는 생각도 했으나 이렇게 하면 배열에 빈 공간이 많아지게 돼 불필요할 것 같았다.
 */

public class Main {
	
	static int R,C,T;
	static int map[][];
	static int[] dr= {0,1,0,-1}; // 우, 하, 좌, 상 
	static int[] dc= {1,0,-1,0};
	static int[] airCon; // 에어컨 x좌표 
	
	public static void main(String[] args) throws IOException{
		input();

		for(int i=0; i<T; i++) {
			spreadDust();
			onAirCon();
		}
		
		System.out.println(countDust());
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[] temp=br.readLine().split(" ");
		R=Integer.parseInt(temp[0]);
		C=Integer.parseInt(temp[1]);
		T=Integer.parseInt(temp[2]);
		
		airCon=new int[2]; int idx=0;
		map=new int[R][];
		for(int i=0; i<R; i++) {
			map[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if(map[i][0]==-1) airCon[idx++]=i;
		}
	}
	// 미세먼지 확산 
	private static void spreadDust() {
		int[][] temp=new int[R][C];
		
		int spreadTotal, nr, nc;
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				if(map[r][c]>0) {
					spreadTotal=0;
					for(int d=0; d<dr.length; d++) {
						nr=r+dr[d]; nc=c+dc[d];
						if(nr<0 || nr>=R || nc<0 || nc>=C || map[nr][nc]==-1) continue;
						
						temp[nr][nc]+=map[r][c]/5;
						spreadTotal+=map[r][c]/5;
					}
					temp[r][c]+=map[r][c]-spreadTotal;
				}
				else if(map[r][c]==-1) temp[r][c]=-1;
			}
		}
		map=temp;
	}
	// 공기청정기 작동 
	private static void onAirCon() {
		// 위쪽 공기청정기
		for(int cr=airCon[0]-1; cr>0; cr--) {
			map[cr][0]=map[cr-1][0];
		}
		for(int cc=0; cc<C-1; cc++) {
			map[0][cc]=map[0][cc+1];
		}
		for(int cr=1; cr<=airCon[0]; cr++) {
			map[cr-1][C-1]=map[cr][C-1];
		}
		for(int cc=C-1; cc>1; cc--) {
			map[airCon[0]][cc]=map[airCon[0]][cc-1];
		}
		map[airCon[0]][1]=0;
		
		// 아래쪽 공기청정기
		for(int cr=airCon[1]+2; cr<R; cr++) {
			map[cr-1][0]=map[cr][0];
		}
		for(int cc=0; cc<C-1; cc++) {
			map[R-1][cc]=map[R-1][cc+1];
		}
		for(int cr=R-1; cr>airCon[1]; cr--) {
			map[cr][C-1]=map[cr-1][C-1];
		}
		for(int cc=C-1; cc>1; cc--) {
			map[airCon[1]][cc]=map[airCon[1]][cc-1];
		}
		map[airCon[1]][1]=0;
	}
	// 마지막 공간에 남아있는 먼지 count 
	private static int countDust() {
		int sum=2; // 에어컨 
		for(int i=0; i<R; i++) {
			sum+=Arrays.stream(map[i]).sum();
		}
		return sum;
	}
}