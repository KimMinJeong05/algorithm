import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/*
 * [핵심 로직]
 * 4개 정점이 한 줄로 이어져야한다. 이때 한번 방문한 정점을 지나면 안된다.
 * dfs로 depth가 4가 될때까지 해본다.
 * 
 * 1. 모든 정점이 시작 정점이 될 수 있으므로 dfs를 모든 점에서 시작해본다
 * 2. 시작 정점에서 depth가 4 이상되면 친구관계가 존재하는 것이다.
 * 	  -> 4이상 되면 끝내고, 4가 안나오면 4가나오거나 모든 정점에서 시작해보고 없으면 0이다.
 */

public class Main {
	
	static int N, M;
	static List<Integer>[] friends;
	static boolean[] checked;

	public static void main(String[] args) throws IOException {
		input();
		
		int answer=0;
		for(int i=0; i<N; i++) { // 각 정점에서 bfs를 시작해본다.
			checked=new boolean[N];
			answer=dfs(i,0);
			if(answer==1) break;
		}
		
		System.out.println(answer);
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp=br.readLine().split(" ");
		N=Integer.parseInt(temp[0]);
		M=Integer.parseInt(temp[1]);	
		
		friends=new List[N];
		for(int i=0; i<N; i++) friends[i]=new ArrayList<Integer>();
		for(int i=0; i<M; i++) {
			temp=br.readLine().split(" ");
			friends[Integer.parseInt(temp[0])].add(Integer.parseInt(temp[1]));
			friends[Integer.parseInt(temp[1])].add(Integer.parseInt(temp[0]));
		}
	}
	// start 정점에서 depth4까지 나오는지 dfs로 찾기
	private static int dfs(int curIdx, int curCnt) {
		if(curCnt>=4) return 1;
		
		int next;
		for(int i=0; i<friends[curIdx].size(); i++) {
			next=friends[curIdx].get(i);
			if(checked[next]) continue;
			
			checked[curIdx]=true;
			if(dfs(next, curCnt+1)==1) return 1;
			checked[curIdx]=false;
		}
		return 0;
	}
}