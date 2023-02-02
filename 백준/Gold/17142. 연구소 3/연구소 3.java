
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;

//1. 바이러스의 모든 조합을 해본다(브루트포스)
//1-1. BFS로 바이러스를 퍼뜨린다. 이때 한 주기마다 초를 더해준다.
//     코드에서 바이러스가 새로 퍼진 경우 1(벽)으로 표
//1-1-1. 처음부터 바이러스가 다 퍼져있으면 0
//1-1-2. 첫 활성 바이러스 시작 0
//2.1번을 하면서 최소값을 찾는다.

// 생각하지 못했던 것.
// 1. 처음에 빈칸이 있는 상황
// 2. 재귀함수를 많이 해 시간 초과가 난 점.


public class Main {
	public static int minSec;
	public static int[][] dirct= {{1,0},{0,1},{-1,0},{0,-1}};
	public static int n;
	public static int m;
	public static List<int[]> viruses = new ArrayList<>(); // 바이러스 전체+time
	public static int[] viruses_idx;
	public static int[][] arr;
	public static int emptyBlock = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		minSec=n*n+1;
		viruses_idx=new int[m];
		
		arr = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == 2) {
					viruses.add(new int[] {i,j,0});
				}else if(arr[i][j]==0) {
					emptyBlock++;
				}
			}
		}
		
		if(emptyBlock==0) {
			minSec=0;
		}else {
			combination(0,0);
			if(minSec==n*n+1) {
				minSec=-1;
			}
		}
		System.out.println(minSec);
	}
	
	// 바이러스 조
	public static void combination(int curCount, int curIdx) {
		if(curCount==m) {
			bfs();
			return;
		}else if(curIdx>=viruses.size()) {
			return;
		}
		
		
		combination(curCount, curIdx+1);
		viruses_idx[curCount] = curIdx;
		combination(curCount+1, curIdx+1);
		
	}
	
	// 바이러스 퍼뜨림 
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		int[][] newArr = copy(arr);
		
		// 뽑힌 수대로 바이러스 리스트에 접근해서 Q에 삽입
		for (int i = 0; i < m; i++) {
			q.add(viruses.get(viruses_idx[i]));
		}
		
		int time = -1;		
		int count = 0;		// 바이러스 확산 숫
		
		while(!q.isEmpty()) {
			int[] v = q.poll();
			
			time = v[2];
			for (int k = 0; k < 4; k++) {
				int nx = v[0] + dirct[k][0];
				int ny = v[1] + dirct[k][1];
				
				// 범위 벗어
				if(!(0 <= nx && nx < n && 0 <= ny && ny < n && newArr[nx][ny]!=1 )) continue;
				
				
				// 빈칸이어서 확산
				if(newArr[nx][ny] == 0) {
					newArr[nx][ny] = 1;
					count++;
					q.add(new int[] {nx, ny,time+1});
				}
				
				// 비활성 바이러스 
				if(newArr[nx][ny] == 2) {
					newArr[nx][ny] = 1;
					q.add(new int[] {nx, ny,time+1});
				}
				
			}
			
			// 시간 중간에 중단 
			if(count == emptyBlock) {
				time++; // 이번에 다 채워진 것이므로 +1 해야함
				break;
			}
		};
		
		
		if(count != emptyBlock) return;	// 다 못채우면 그냥 끝내기
		
		minSec = Math.min(minSec, time);
	}
	
	// 2차원 배열 copy
	public static int[][] copy(int[][] arr) {
		int[][] newArr = new int[n][n];
		for(int i = 0; i < n; i++) {
			newArr[i] = arr[i].clone();
		}
		return newArr;
	}
}
