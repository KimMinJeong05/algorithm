import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/* 0: 이동하지 않음. 1: 상, 2: 우, 3: 하, 4: 좌
 * 
 * [핵심 로직]
 * 1. BC들의 범위를 측정하고 배터리 값을 저장
 * 	-> 같은 구역 여러 BC들이 있으면 내림차순으로 정렬
 * 	-> 최대값을 구해야하기때문에 큰값을 우선적으로 고른다(그리디)
 * 2. 1초 단위로 A,B 움직임
 * 3. BC가 없는 부분이 있으면
 * 	3-1. BC가 있는 구역의 값만 합
 * 4. 가장 큰 BC가 겹친 부분
 * 	4-1. 둘다 BC가 하나면 1/2한다.
 * 	4-2. 하나가 BC가 하나면 그것을 선택하고 다른 BC는 2번째로 큰 값을 뽑는다. 
 * 	4-3. BC가 2개 이상이면, 큰 순으로 2개를 뽑는다.
 * 5. 둘다 다른 영역에 있음
 * 	5-1. 둘의 값 모두 합
 */

class Solution
{
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	static int m,b;
	static int[] A;
	static int[] B;
	static int[][] bc;
	static int[] dx= {0,0,1,0,-1}; // {_,이동하지 않음, 상, 우, 하, 좌}
	static int[] dy= {0,-1,0,1,0};
	static List<int[]>[][] map;
	static int sum=0;
	
	public static void main(String args[]) throws Exception
	{
		int T;
		T=Integer.parseInt(in.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			input();
			
			// BC 공간 초기화
			setBC();
			
			
			System.out.printf("#%s %s%n",test_case, move(0, 1, 1, 10, 10, 0));
		}
	}

	public static void input() throws IOException {
		String[] mb=in.readLine().split(" ");
		m=Integer.parseInt(mb[0]);
		b=Integer.parseInt(mb[1]);
		
		A=Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		B=Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		bc=new int[b][];
		for (int i = 0; i < b; i++) {
			bc[i]=Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		map=new List[11][11];
	}
	
	private static void setBC() {
		int[] cur;
		for(int i=0; i<b; i++) {
			cur=bc[i];
			for(int r=cur[0]-cur[2]; r<=cur[0]+cur[2];r++) {
				if(r<1 || r>10) continue;
				for(int c=cur[1]-cur[2]; c<=cur[1]+cur[2];c++) {
					if(c<1 || c>10) continue;
					if(Math.abs(cur[0]-r)+Math.abs(cur[1]-c)>cur[2]) continue;
					
					if(map[r][c]==null) map[r][c]=new ArrayList<int[]>();
					map[r][c].add(new int[] {i,cur[3]}); // BC id, BC 충전량
				}
			}
		}
		
		// 같은 구역에 BC가 여러개 있으면 내림차순 순으로 정렬
		for(int i=1; i<11; i++) {
			for(int j=1; j<11; j++) {
				if(map[i][j]!=null) map[i][j].sort((o1,o2)->{
					return o2[1]-o1[1];
				});
			}
		}
	}
	
	private static int move(int sec, int ax, int ay, int bx, int by, int sum) {
		List<int[]> curA = map[ax][ay];
		List<int[]> curB = map[bx][by];
		int curSum=0;
		
		if(curA==null || curB==null) { // 둘 중 하나가 BC 영역에 없으면
			if(curA==null && curB==null) curSum = 0;
			else curSum = (curA==null?curB.get(0)[1]:curA.get(0)[1]);
		}
		else if(curA.get(0)[0]==curB.get(0)[0]) { // 같은 BC 영역에 있음
			if(curA.size()==1 && curB.size()==1) { // 둘다 BC가 하나.
				curSum = curA.get(0)[1];
			}else if(curA.size()==1 || curB.size()==1){ // 둘 중 하나가 BC여러개 
				curSum = (curA.size()==1?curA.get(0)[1]+curB.get(1)[1]:curB.get(0)[1]+curA.get(1)[1]);
			}else {// 둘다 BC가 여러개
				curSum = (curA.get(1)[1]>curB.get(1)[1]?curA.get(1)[1]+curB.get(0)[1]:curA.get(0)[1]+curB.get(1)[1]);
			}
		}
		else { // 둘이 각각 다른 곳에 있음
			curSum = curA.get(0)[1]+curB.get(0)[1];
		}
		
		if(sec==m) return sum+curSum;
		return move(sec+1, ax+dx[A[sec]], ay+dy[A[sec]], bx+dx[B[sec]], by+dy[B[sec]], sum+curSum);
	}
}