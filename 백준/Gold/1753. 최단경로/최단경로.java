import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * [풀이]
 * 시작 정점에서 다른 모든 정점으로의 최단경로 -> 다익스트라 알고리즘 사용 + 모든 정점에 방문하거나/더이상 다른 정점으로 갈 수 없을 때까지 해야함
 * 서로다른 두 정점 사이에 여러 개의 간선이 존재 -> 같은 경로면 가장 작은 값으로 한다.
 * 
 * 시간 초과->가장 작은 값을 찾는 것 때문인 것 같아 PriorityQueue 사용
 */

public class Main {

	static public class Edge{
		public int to, weight;

		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		@Override
		public boolean equals(Object obj) {
			return this.to==((Edge)obj).to;
		}
		@Override
		public String toString() {
			return "Edge [to=" + to + ", weight=" + weight + "]";
		}
	}
	
	static int V,E, start;
	static List[] edges; // 간선 정보
	static boolean[] visited; // 방문 여부
	static int[] distance; // 시작 정점에서부터의 각각의 최소 거리
	static final int INF=Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		input();
		
		// 다익스트라 알고리즘
		dijkstra();
		
		// 출력
		StringBuilder sb=new StringBuilder();
		String enter="\n";
		for(int i=1; i<=V; i++) {
			sb.append(distance[i]==INF?"INF":distance[i]);
			sb.append(enter);
		}
		System.out.println(sb);
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		start=Integer.parseInt(br.readLine());
		
		edges=new List[V+1];
		visited=new boolean[V+1];
		distance=new int[V+1];
		for(int i=1; i<=V; i++) {
			edges[i]=new ArrayList<Edge>();
			distance[i]=INF;
		}
		
		int from, idx; Edge newEdge;
		for(int i=0; i<E; i++) {
			st=new StringTokenizer(br.readLine()," ");
			edges[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
//			from=Integer.parseInt(st.nextToken());
//			newEdge=new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
//			if(edges[from].contains(newEdge)) {
//				idx=edges[from].indexOf(newEdge);
//				if(((Edge)edges[from].get(idx)).weight > newEdge.weight) ((Edge)edges[from].get(idx)).weight=newEdge.weight;
//			}else {
//				edges[from].add(newEdge);
//			}
		}
	}

	static void dijkstra() {
		PriorityQueue<int[]> disQueue=new PriorityQueue<int[]>((o1, o2)->{
			return o1[1]-o2[1];
		}); // V, weight
		
		distance[start]=0;
		disQueue.add(new int[] {start,0});
		
		for(int v=1; v<=V; v++) {
			// 방문하지 않은 것 중 가장 작은 값 찾기
			if(disQueue.isEmpty()) break;
			int[] min=null;
			while(!disQueue.isEmpty()) {
				min=disQueue.poll();
				if(!visited[min[0]]) break;
			}
			if(min==null) break;
			else if(visited[min[0]]) break;
			
			visited[min[0]]=true;
			
			// 현재 정점 중 갈 수 있는 정점들 최소값 갱신
			Edge next;
			for(int i=0; i<edges[min[0]].size(); i++) {
				next=(Edge) edges[min[0]].get(i);
				if(!visited[next.to] && distance[next.to]>min[1]+next.weight) {
					distance[next.to]=min[1]+next.weight;
					disQueue.add(new int[] {next.to, distance[next.to]});
				}
			}
		}
	}
}