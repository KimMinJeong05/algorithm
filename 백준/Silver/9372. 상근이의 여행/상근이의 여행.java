import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * [풀이]
 * 무방향 그래프 
 * 최소 경로로 모든 정점 지나가기 -> 가중치가 없으므로 BFS 
 */

class Main
{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int T, N, M, cnt=0;
	static List[] airplanes;
	
	public static void main(String args[]) throws Exception
	{
		T=Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			input();
			
			boolean[] checked=new boolean[N+1];
			System.out.println(bfs());
		}
	}

	public static void input() throws IOException {
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken()); M=Integer.parseInt(st.nextToken());
		airplanes=new List[N+1];
		for(int i=1; i<=N; i++) airplanes[i]=new ArrayList<Integer>();
		
		int from, to;
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine()," ");
			from=Integer.parseInt(st.nextToken()); to=Integer.parseInt(st.nextToken());
			airplanes[from].add(to);
			airplanes[to].add(from);
		}
	}
	
	static int bfs() {
		Queue<Integer> queue=new ArrayDeque<>();
		boolean[] checked=new boolean[N+1];
		
		queue.add(1);
		checked[1]=true;
		
		int cur=0;
		int next, cnt=0;
		while(!queue.isEmpty()) {
			cur=queue.poll();

			for(int i=0; i<airplanes[cur].size(); i++) {
				next=(int)airplanes[cur].get(i);
				if(checked[next]) continue;
				
				cnt++;
				checked[next]=true;
				queue.add(next);
			}
		}
		
		return cnt;
	}
}