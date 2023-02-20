import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * [핵심 로직]
 * 구현
 * 1. 4방향 모두의 모래 비율을 정해야함.
 * 2. 토네이도 방향으로 회전해야함.
 * 2-1. 전진하는 횟수가 1,1,2,2,3,3,4,4 ... => 진행 수/2 로 계산
 */

public class Main {
	
	static int n;
	static int[][] desert;
	static int[][][] sandsDir={ // 방향에 따른 모래 방향(좌, 하, 우, 상). y기준
		// 1%, 1%, 7%, 7%, 2%, 2%, 10%, 10%, 5%, 알파
		{{1,1},{-1,1},{1,0},{-1,0},{2,0},{-2,0},{1,-1},{-1,-1},{0,-2},{0,-1}},
		{{-1,-1},{-1,1},{0,1},{0,-1},{0,-2},{0,2},{1,-1},{1,1},{2,0},{1,0}},
		{{1,-1},{-1,-1},{1,0},{-1,0},{2,0},{-2,0},{1,1},{-1,1},{0,2},{0,1}},
		{{1,-1},{1,1},{0,1},{0,-1},{0,-2},{0,2},{-1,-1},{-1,1},{-2,0},{-1,0}}
	};
	static double[] sandsRatio= {0.01,0.01,0.07,0.07,0.02,0.02,0.1,0.1,0.05}; // 모래 비율
	static int[][] tornadoDir= {{0,-1},{1,0},{0,1},{-1,0}}; // 토네이도 회전
	static int outSandSum=0; // 모래밭을 넘어가는 모래들의 합

	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		
		tornado(0, 0, n/2, n/2);
		System.out.println(outSandSum);
	}

	private static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		desert=new int[n][];
		for(int i=0;i<n;i++){
			desert[i]=Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
		}
	}
	
	// 토네이도 방향 torDirIdx에서 (count/2)+1만큼 전진 후 방향 바꿈
	private static void tornado(int count, int torDirIdx, int xr, int xc) {
		if(xr==0 && xc==0) return;
		
		int yr=xr, yc=xc;
		// 전진
		for(int i=0;i<(count/2)+1;i++) { 
			yr=xr+tornadoDir[torDirIdx][0];
			yc=xc+tornadoDir[torDirIdx][1];
			if(xr==0 && xc==0) return;
			
			// 모래 날림
			int sr,sc;
			int rmSand=0;
			for(int s=0; s<sandsDir[torDirIdx].length-1;s++) {
				sr=yr+sandsDir[torDirIdx][s][0];
				sc=yc+sandsDir[torDirIdx][s][1];
				if(sr<0 || sr>=n || sc<0 || sc>=n) {
					rmSand+=(int)(desert[yr][yc]*sandsRatio[s]);
					outSandSum+=(int)(desert[yr][yc]*sandsRatio[s]);
					continue;
				}
				rmSand+=(int)(desert[yr][yc]*sandsRatio[s]);
				desert[sr][sc]+=(int)(desert[yr][yc]*sandsRatio[s]);
			}
			// 알파로 모래 날림
			sr=yr+sandsDir[torDirIdx][sandsDir[torDirIdx].length-1][0];
			sc=yc+sandsDir[torDirIdx][sandsDir[torDirIdx].length-1][1];
			if(sr<0 || sr>=n || sc<0 || sc>=n) {
				outSandSum+=desert[yr][yc]-rmSand;
			}else {
				desert[sr][sc]+=desert[yr][yc]-rmSand;
			}
			
			// 다음 전진
			xr=yr;
			xc=yc;
		}
		
		// 토네이도 회전
		tornado(count+1, (torDirIdx+1)%4, yr,yc);
	}
}