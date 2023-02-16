import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 배열의 값 = 각 행의 합 중 최솟값
* 
* [핵심 로직]
* 1. 모든 순열에 대하여 연산
*/


public class Main {
	
	static int n, m, k;
	static int[][] arr; // 회전할 배열
	static int[][] initArr; // 원래 배열 값들
	static int[][] rotations; // 회전 연산
	static boolean[] checkedRot; // 회전 연산 방문 체크
	static int[] rotateOrder; // 회전 순서
	static int min=Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		input();
		permutation(0);
		System.out.println(min);
	}

	private static void input() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine()," ");
		
		n = Integer.parseInt(tokens.nextToken());
		m = Integer.parseInt(tokens.nextToken());
		k = Integer.parseInt(tokens.nextToken());
		
		arr=new int[n][];
		initArr=new int[n][];
		for(int i=0;i<n;i++) {
			initArr[i]=Arrays.stream(in.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		
		rotations=new int[k][];
		checkedRot=new boolean[k];
		rotateOrder=new int[k];
		for(int i=0;i<k;i++) {
			rotations[i]=Arrays.stream(in.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
	}
	// 순열 
	private static void permutation(int cnt) {
		if(cnt==k) {
			copyArr(); // 배열 초기화
			for(int curR:rotateOrder) {
				rotate(curR);
			}
			arrValue();
			
			return;
		}
		
		for(int i=0;i<k;i++) {
            if(checkedRot[i]) continue;
            
			checkedRot[i]=true;
			rotateOrder[cnt]=i;
			permutation(cnt+1);
			
			checkedRot[i]=false;
		}
	}
	// cur번째에 있는 회전 연산 실행 
	private static void rotate(int cur) {
		int sr=rotations[cur][0]-rotations[cur][2]-1,sc=rotations[cur][1]-rotations[cur][2]-1;
		int er=rotations[cur][0]+rotations[cur][2]-1,ec=rotations[cur][1]+rotations[cur][2]-1;
		
		while(er-sr>0) {
			int temp=arr[sr][sc];
			for(int i=sr;i<er;i++) arr[i][sc]=arr[i+1][sc];
			for(int i=sc;i<ec;i++) arr[er][i]=arr[er][i+1];
			for(int i=er;i>sr;i--) arr[i][ec]=arr[i-1][ec];
			for(int i=ec;i>sc;i--) arr[sr][i]=arr[sr][i-1];
			arr[sr][sc+1]=temp;
			
			sr++; sc++; er--; ec--;
		}
	}
	// 현재 배열의 최소값
	private static void arrValue() {
		for(int[] curCol: arr) {
			min=Math.min(min,Arrays.stream(curCol).sum());
		}
	}
	
	// 2차원 배열 깊은 복사 
	private static void copyArr() {
		for(int i=0;i<n;i++) {
			arr[i]=Arrays.copyOf(initArr[i], m);
		}
	}

}