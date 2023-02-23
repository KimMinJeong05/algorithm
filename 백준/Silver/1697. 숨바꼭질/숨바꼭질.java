import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * [핵심 로직]
 * X -> 1초후 X-1, X+1, 2*X
 * 1. 모든 경우를 BFS 방식으로 다 해본다.
 * 2. 기저조건
 * 2-1. k와 같아지면 K count 갱신
 * 2-2. k count값보다 크면 continue
 * 3. 순간이동과 앞으로 이동하는건 K가 지금N보다 작을 땐 하지 않는다.
 * 	  -> 뒤로 가는 건 -1씩만 이동하는 거니 가장 빠른 길이 아니다.
 * 4. N이 K보다 커지면 무조건 1칸씩 뒤로 가는 방법밖에 없다.
 * 5. 순간 이동해서 빨리 갈 수 있는 건 100000+50000까지이다. 그 이후로 순간이동으로 한다면 한번씩 뒤로 가야하기때문에 더 오래걸린다.
 * 
 * [틀린 로직]
 * 그리디
 * 1. 3*N/2 < K -> 순간이동
 * 2. N>K -> 뒤로
 * 3. N<K -> 앞으로
 * -> 뒤로 갔다가 순간이동하는 것이 더 빠를 수 있음
*/

public class Main {
	
	static int N,K;
	static int[] count;
	
	public static void main(String[] args) throws IOException {
		input();
		
		bfs();
		
		System.out.println(count[K]);
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens=new StringTokenizer(br.readLine()," ");
		
		N=Integer.parseInt(tokens.nextToken());
		K=Integer.parseInt(tokens.nextToken());
		
		count=new int[150001];
		Arrays.fill(count,Integer.MAX_VALUE);
	}
	
	private static void bfs() {
		Queue<Integer> queue=new ArrayDeque<>();
		queue.add(N);
		count[N]=0;
		
		int cur, curCnt;
		while(!queue.isEmpty()) {
			cur=queue.poll();
			curCnt=count[cur];
			
			if(curCnt>=count[K]) continue;
			if(cur==K) {
				count[K]=Math.min(curCnt, count[K]);
				continue;
			}
			else if(cur>K) {// 이미 K가 N보다 작으면 무조건 그 차이만큼 뒤로 가야한다.
				count[K]=Math.min(curCnt+(cur-K), count[K]);
				continue;
			}
			
			
			if(cur!=0 && cur*2<=150000 && count[cur*2]>=curCnt+1) {
				count[cur*2]=curCnt+1;
				queue.add(cur*2);
			}
			if(cur+1<=100000 && count[cur+1]>=curCnt+1) {
				count[cur+1]=curCnt+1;
				queue.add(cur+1);
			}
			if(cur-1>=0 && count[cur-1]>=curCnt+1) {
				count[cur-1]=curCnt+1;
				queue.add(cur-1);
			}
		}

	}
}