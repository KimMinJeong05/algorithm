import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
 * N<=200,000 이므로 조합으로 완전탐색하는 O(n) 방법은 사용할 수 없음
 * 
 * [구현 로직]
 * 1. 크기가 큰 공을 먼저 계산하고 빼주면 작은 공에게 영향이 가지 않음 -> 어짜피 크기가 커서 사로잡을 수 없으니
 * 	  => 우선순위큐를 역순으로 사용하자
 * 2. 처음 입력받을 때 색깔 별 총 합을 먼저 계산하자
 * 3. 그 후 우선순위 큐에서 poll하면서 색깔 별 총 합에서 해당 값을 뺄셈해주자
 * 4. 2,3번처럼 하면 전체 총 합에서 자신의 색깔 총합을 빼면 됨
 * 5. 이때 같은 크기의 공이 있을 수 있음. 자신과 같은 공은 사로잡을 수 없
 * 5-1. 같은 크기의 공이 있다면, 같은 크기 공들은 다 poll 한 후 그 값들을 색깔 별 총합에서 뺌
 * 5-2. 그 후 색깔 별 총합을 다 더한 후 자신의 색깔 합을 빼면, 자신이 사로잡을 수 있는 공의 합이 됨.
 */

// 
class Node implements Comparable<Node>{
	int idx;
	int size;
	int color;
	Node(int idx,int color, int size){
		this.idx=idx;
		this.size = size;
		this.color = color;
	}
	
    // 내림차순
	@Override
	public int compareTo(Node o) {
		if(this.size<o.size) return 1;
		else if(this.size>o.size) return -1;
		return 0;
	}
}

public class Main {
	
	static int n;
	static PriorityQueue<Node> queue = new PriorityQueue<Node>();
	static int[] colors; // 색깔별 공의 크기 총
	static int[] sum; // 해당 인덱스 공의 사로잡은  크기의 합 

	public static void main(String[] args) throws IOException{
		input();
		solve();
		print();
	}

	static void input() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp;

		n = Integer.parseInt(in.readLine());
		colors=new int[n+1];
		sum=new int[n+1];
		
		for(int i=1; i<=n; i++) {
			temp = in.readLine().split(" ");
			queue.add(new Node(i,Integer.parseInt(temp[0]),Integer.parseInt(temp[1])));
			colors[Integer.parseInt(temp[0])]+=Integer.parseInt(temp[1]);
		}
		in.close();
	}
	
	static void solve() {
		List<Node> sameCur; // 현재 큐에서 가장 큰 값들의 list 
		while(!queue.isEmpty()) {
			sameCur=new ArrayList<Node>();
			sameCur.add(queue.poll());
			
			// 현재 공(가장 크기가 큰 공)과 같은 크기인 공을 list에 담는다.
			while(!queue.isEmpty()&&sameCur.get(0).size==queue.peek().size) {
				sameCur.add(queue.poll());
			}
			
			// 자신의 값과 자신과 같은 값은 사로잡을 수 없기 때문에 -> 가장 크기가 큰 공들의 크기를 각각의 색깔 합에서 빼준다. 
			for(Node node:sameCur) {
				colors[node.color] -= node.size;
			}
			
			// 공들의 전체 합에서 자신과 같은 색깔 합을 빼준다.
			int total = Arrays.stream(colors).sum();
			for(Node node:sameCur) {
				sum[node.idx] = total - colors[node.color];
			}
		}
	}
	
	static void print() throws IOException {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=1; i<=n; i++) {
			out.write(sum[i]+"\n");
		}
		out.flush();
		out.close();
	}
}