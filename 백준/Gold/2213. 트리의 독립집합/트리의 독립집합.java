import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int nodeSize; //노드 개수
	static int[] nodes; //노드 가중치들
	static List<Integer>[] edges; //간선
	static boolean[] visited; //방문체크
	static List<Integer> selectNodes = new ArrayList<>(); //선택된 노드들

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		nodeSize = Integer.parseInt(br.readLine());
		nodes = new int[nodeSize + 1];
		memo = new int[nodeSize + 1][2];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < nodeSize + 1; i++) {
			nodes[i] = Integer.parseInt(st.nextToken());
		}
		edges = new ArrayList[nodeSize + 1];
		for (int i = 0; i < nodeSize + 1; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < nodeSize - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[b].add(a);
		}
		visited = new boolean[nodeSize + 1];
		sb.append(solution(1)).append("\n");
		visited = new boolean[nodeSize + 1];
		findNode(1, 1);
		
		Collections.sort(selectNodes);
		for (int node : selectNodes) {
			sb.append(node).append(" ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int[][] memo;

	/**
	 * 그전에 선택을 했을 경우 : 현재는 선택하지 못한다. 
	 * 그전에 선택을 안했을 경우 : 선택을 해도 안해도 된다
	 */
	public static int solution(int node) {
		visited[node] = true;
		memo[node][1] = nodes[node];
		for (int next : edges[node]) {
			if (!visited[next]) {
				solution(next);
				memo[node][0] += Math.max(memo[next][0], memo[next][1]);
				memo[node][1] += memo[next][0];
			}
		}
		return Math.max(memo[node][0], memo[node][1]);
	}

	public static void findNode(int node, int pre) {
		if (memo[node][1] > memo[node][0] && !visited[pre]) {
			visited[node] = true;
			selectNodes.add(node);
		}
		for (int next : edges[node]) {
			if (next == pre) continue;
			if (!visited[next]) {
				findNode(next, node);
			}
		}
	}
}