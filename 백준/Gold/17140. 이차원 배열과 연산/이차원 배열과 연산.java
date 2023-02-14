import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 1. 행 길이 >= 열 길이
 * 1-1. 행마다 우선순위 큐를 만들어 정렬
 * 1-2. 행 -> set으로 변경
 * 1-3. Collections.frequency 함수 사용해 set을 돌아가며 개수 count
 * 1-4. 개수와 값을 가지고 있는 Node를 우선순위큐에 넣어 정렬
 * 1-5. 큐에서 하나씩 꺼내며 배열에 다시 넣기
 * 1-6. 넣은 후 뒤에 남은 배열들을 다 null로 초기화(int 배열이 아니고 Integer 배열이라서)
 * 
 * 2. 행 길이 < 열 길이
 * 2-1. 열마다 우선순위 큐를 만들어 정렬
 * 2-2. 열을 뽑아 list로 다시 만듦.
 * 2-2. list -> set으로 변경
 * 2-3. Collections.frequency 함수 사용해 set을 돌아가며 개수 count
 * 2-4. 개수와 값을 가지고 있는 Node를 우선순위큐에 넣어 정렬
 * 2-5. 큐에서 하나씩 꺼내며 배열에 다시 넣기
 * 2-6. 넣은 후 뒤에 남은 배열들을 다 null로 초기화(int 배열이 아니고 Integer 배열이라서)
 * 
 * [주의]
 * 1. int[]는 Arrays.asList하면 List<Integer>가 아니라 List<int[]>가 됨
 * 		-> Integer[]로 선언
 * 2. 배열에 값을 다시 넣을 때 안 넣은 자리는 다 초기화 해야함. null이 나왔다고 뒤가 다 null인 것이 아님
 */

class Node implements Comparable<Node>{
	int value;
	int count;
	Node(int value, int count){
		this.value = value;
		this.count = count;
	}
	
	@Override
	public int compareTo(Node o) {
		if(this.count < o.count){
			return -1;
		}else if(this.count > o.count){
			return 1;	
		}else{
			//count가  같을 경우
			if(this.value < o.value){
				return -1;
			}else if(this.value > o.value){
				return 1;
			}
		}
		return 0;
	}
}

public class Main {
	
	static int r,c,k;
	static Integer[][] arr = new Integer[100][100];
	static int rowSize=3, colSize=3;
	static int count=0;

	public static void main(String[] args) throws IOException{
		input();
		check();
		System.out.println(count);
	}

	static void input() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine()," ");
		r = Integer.parseInt(tokens.nextToken())-1;
		c = Integer.parseInt(tokens.nextToken())-1;
		k = Integer.parseInt(tokens.nextToken());
		
		for(int i=0; i<3;i++) {
			tokens = new StringTokenizer(in.readLine()," ");
			for(int j=0; j<3;j++) arr[i][j] =Integer.parseInt(tokens.nextToken());
		}
	}
	
	static void check() {
		if(r<rowSize && c<colSize && arr[r][c]!=null && arr[r][c]==k) {
			return;
		}
		else if(count>100) {
			count=-1;
			return;
		}

		
		count++;
		
		if(rowSize >= colSize) {
			// R연산
			int maxLen = 0;
			for(int i=0;i<rowSize;i++) {
				PriorityQueue<Node> queue = new PriorityQueue<>();

				Set<Integer> set = new HashSet<>(Arrays.asList(arr[i]));
				set.remove(null);

				for(int s: set) {
					queue.add(new Node(s,Collections.frequency(Arrays.asList(arr[i]), s)));
				}
				
				maxLen = Math.max(maxLen, queue.size());

				// arr에 반영
				int idx=0;
				Node cur;
				while(!queue.isEmpty()) {
					if(idx>=100) break;
					cur = queue.poll();
					arr[i][idx++]=cur.value;
					arr[i][idx++]=cur.count;
				}
				while(idx<100) {
					arr[i][idx++]=null;
				}
			}
			colSize=maxLen*2>100?100:maxLen*2;
		}else {
			// C연산
			int maxLen = 0;
			for(int i=0;i<colSize;i++) {
				PriorityQueue<Node> queue = new PriorityQueue<>();
				List<Integer> tempList = new ArrayList<Integer>(); // 행을 뽑아 저장
				for(int j=0; j<rowSize;j++) {
					tempList.add(arr[j][i]);
				}
				
				Set<Integer> set = new HashSet<>(tempList);
				set.remove(null);
				
				for(int s: set) {
					queue.add(new Node(s,Collections.frequency(tempList, s)));
				}
				
				maxLen = Math.max(maxLen, queue.size());
				
				// arr에 반영
				int idx=0;
				Node cur;
				while(!queue.isEmpty()) {
					if(idx>=100) break;
					cur = queue.poll();
					arr[idx++][i]=cur.value;
					arr[idx++][i]=cur.count;
				}
				while(idx<100) {
					arr[idx++][i]=null;
				}
			}
			rowSize=maxLen*2>100?100:maxLen*2;
		}
		
		check();
		return;
	}
}