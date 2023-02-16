import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 치킨 거리 = 가장 가까운 치킨 집까지의 거리 
 * 
 * [핵심 로직]
 * 1. 가장 가까운 거리를 찾아함 -> bfs
 * 2. 각각의 치킨집에서 모든 집의 거리를 구한다.
 * 3. 치킨집 m개에 대한 모든 조합에 대해 거리를 구한 후 min 찾음 
 * 
 * -> 치킨집의 조합을 찾은 후 모든 집에서의 최소 거리를 구하면 치킨집에서 집 거리를 구하는 계산이 여러번 겹치게 됨
 * -> 먼저 치킨집과 모든 집의 거리를 계산하고, 나중에 조합에서 가장 작은 값만 찾으면 연산을 더 적게 할 수 있음.
*/


public class Main {
	
	static int n,m;
	static List<int[]> houses, chickens; // {x,y}
	static int[][] c2h; // 각각의 치킨집에서 모든 집까지의 거리 
	static int[] combiChickens; // 치킨집의 조합 
	static int minLen=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		input();
		chicken2houses();
		combination(0,0);
		System.out.println(minLen);
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens=new StringTokenizer(br.readLine()," ");
		n=Integer.parseInt(tokens.nextToken());
		m=Integer.parseInt(tokens.nextToken());
		
		houses=new ArrayList<int[]>();
		chickens=new ArrayList<int[]>();
		combiChickens=new int[m];
		String cur;
		for(int i=1;i<=n;i++) {
			tokens=new StringTokenizer(br.readLine()," ");
			for(int j=1;j<=n;j++) {
				cur=tokens.nextToken();
				if(cur.equals("1")) houses.add(new int[] {i,j});
				if(cur.equals("2")) chickens.add(new int[] {i,j});
			}
		}
	}
	// 각각의 치킨 집에서 모든 집까지의 거리 계산 
	private static void chicken2houses() {
		c2h=new int[chickens.size()][houses.size()];
		for(int ch=0;ch<chickens.size();ch++) {
			for(int h=0;h<houses.size();h++) {
				c2h[ch][h]=Math.abs(chickens.get(ch)[0]-houses.get(h)[0])+Math.abs(chickens.get(ch)[1]-houses.get(h)[1]);
			}
		}
	}
	// 모든 조합 
	private static void combination(int cnt, int cur) {
		if(cnt==m) {
			minLen=Math.min(minLen,findCityChickenLen());
			return;
		}else if(chickens.size()-cur<m-cnt) {
			// 현재 남은 치킨집을 다 선택해도 최개m개수를 못채우는 상황은 미리 끝냄 
			return;
		}
		
		for(int i=cur;i<chickens.size();i++) {
			combiChickens[cnt]=i;
			combination(cnt+1, i+1);
		}
	}
	// 특정 조합의 치킨 집들의 도시 치킨 거리 계산 
	private static int findCityChickenLen() {
		int[] houseMinLen=new int[houses.size()]; // 각각의 집에서 치킨집과 가장 가까운 거리 
		Arrays.fill(houseMinLen, Integer.MAX_VALUE);
		
		for(int chIdx:combiChickens) {
			for(int i=0;i<houses.size();i++) {
				houseMinLen[i] = Math.min(c2h[chIdx][i], houseMinLen[i]);
			}
		}
		return Arrays.stream(houseMinLen).sum();
	}
}