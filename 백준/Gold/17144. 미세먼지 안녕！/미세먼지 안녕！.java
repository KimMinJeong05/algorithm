import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * [풀이]
 * 1. T 만큼 아래는 반복
 * 2. 미세먼지 확산 -> for문을 돌면서 미세먼지를 4방으로 확산
 * 	  -> 이때 공기청정기가 있는곳은 확산되지 않음
 * 3. 공기청정기 작동 -> 회전
 * 
 * 모두 for문으로 했다.
 * [결과]
 */

public class Main {
	
	static int R, C, T;
	static int[][] room; // 방
	static int[] airR; // 에어컨 r좌표
	static int[] dr= {0,0,1,-1};
	static int[] dc= {1,-1,0,0};

	public static void main(String[] args) throws IOException {
		input();
		
		for(int i=0; i<T; i++) {
			extendDust();
			airFresh();
		}
		
		System.out.println(totalDust());
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());
		
		room=new int[R][C];
		airR=new int[2];
		int airIdx=0;
		for(int i=0; i<R; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<C; j++) {
				room[i][j]=Integer.parseInt(st.nextToken());
				if(room[i][j]==-1) airR[airIdx++]=i;
			}
		}
	}
	
	static void extendDust() {
		int temp[][]=new int[R][C];
		
		int sd; // 퍼진 먼지
		int nr, nc;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(room[i][j]==-1) {
					temp[i][j]=-1;
					continue;
				}
				
				sd=0;
				for(int d=0; d<dr.length; d++) {
					nr=i+dr[d]; nc=j+dc[d];
					if(nr<0 || nr>=R || nc<0 || nc>=C || room[nr][nc]==-1) continue;
					temp[nr][nc]+=(room[i][j]/5);
					sd+=(room[i][j]/5);
				}
				temp[i][j]+=room[i][j]-sd;
			}
		}
		
		room=temp;
	}
	static void airFresh() {
		// 위쪽 공기청정기
		for(int i=airR[0]-2; i>=0; i--) room[i+1][0]=room[i][0];
		for(int i=1; i<C; i++) room[0][i-1]=room[0][i];
		for(int i=1; i<=airR[0]; i++) room[i-1][C-1]=room[i][C-1];
		for(int i=C-2; i>=1; i--) room[airR[0]][i+1]=room[airR[0]][i];
		room[airR[0]][1]=0;
		
		// 아래 공기청정기
		for(int i=airR[1]+2; i<R; i++) room[i-1][0]=room[i][0];
		for(int i=1; i<C; i++) room[R-1][i-1]=room[R-1][i];
		for(int i=R-2; i>=airR[1]; i--) room[i+1][C-1]=room[i][C-1];
		for(int i=C-2; i>=1; i--) room[airR[1]][i+1]=room[airR[1]][i];
		room[airR[1]][1]=0;
	}
	static int totalDust() {
		int sum=2;

		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				sum+=room[i][j];
			}
		}
		
		return sum;
	}
}