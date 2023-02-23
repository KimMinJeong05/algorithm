import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * [핵심 로직]
 * 정점이 작은 순으로 방문해야하므로 인접 행렬로 DFS, BFS 구현
 * 최대 boolean 배열 크기: 1000만 -> 10MB 정도
 * 
 * 1. DFS
 * 1-1. stack 사용.
 * 1-2. stack은 늦게 들어간 순으로 pop되므로 큰 순서대로 push
 * 2. BFS
 * 2-1. queue 사용
 * 2-2. queue은 먼저 들어간 순으로 pop되므로 작은 순서대로 push
*/

public class Main {
	
	static int N, M, V;
	static boolean[][] graph; 
	static StringBuilder sb=new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		input();
		
		dfs(V);
		sb.append("\n");
		bfs(V);

		System.out.println(sb);
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens=new StringTokenizer(br.readLine()," ");
		
		N=Integer.parseInt(tokens.nextToken());
		M=Integer.parseInt(tokens.nextToken());
		V=Integer.parseInt(tokens.nextToken());
		
		graph=new boolean[N+1][N+1];
		int x, y;
		for(int i=0; i<M; i++) {
			tokens=new StringTokenizer(br.readLine()," ");
			x=Integer.parseInt(tokens.nextToken()); y=Integer.parseInt(tokens.nextToken());
			graph[x][y]=graph[y][x]=true;
		}
	}
	
	private static void dfs(int start) {
		Stack<Integer> stack = new Stack<>();
		boolean[] checked=new boolean[N+1];
		
		stack.add(start);
		
		int cur=0;
		while(!stack.isEmpty()) {
			cur=stack.pop();
			if(checked[cur]) continue;
			
			checked[cur]=true;
			sb.append(cur+" ");
			
			// stack은 늦게 들어간 순으로 pop되므로 큰 순서대로 push
			for(int i=N; i>=1; i--) { 
				if(graph[cur][i]&&!checked[i]) {
					stack.add(i);
				}
			}
		}
	}
	private static void bfs(int start) {
		Queue<Integer> queue=new ArrayDeque<>();
		boolean[] checked=new boolean[N+1];
		
		queue.add(start);
		checked[start]=true;
		
		int cur=0;
		while(!queue.isEmpty()) {
			cur=queue.poll();
			sb.append(cur+" ");
			
			// queue은 먼저 들어간 순으로 pop되므로 작은 순서대로 push
			for(int i=1; i<=N; i++) {
				if(graph[cur][i]&&!checked[i]) {
					queue.add(i);
					checked[i]=true;
				}
			}
		}
	}
}