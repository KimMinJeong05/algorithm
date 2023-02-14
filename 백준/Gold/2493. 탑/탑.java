import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 1. 현재 들어온 값보다 작으면 pop()
 * 2. 현재 들어온 값보다 같거나 크면 출력
 * 3. 현재 값 push
 */
class Node{
	int value;
	int idx;
	
	Node(int value, int idx) {
		this.value = value;
		this.idx = idx;
	}
}

public class Main {
	static int n;
	static StringBuilder sb=new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		
		StringTokenizer tokens = new StringTokenizer(in.readLine(), " ");
		int idx=0; // 인덱스
		Stack<Node> stack = new Stack<Node>();
		Node cur=null; // 현재 stack의 top 값
		int curTop; // 현재 값
		while(tokens.hasMoreTokens()) {
			idx++;
			curTop=Integer.parseInt(tokens.nextToken());
			if(stack.isEmpty()) { // stack에 값이 없으면 수신 받을 수 없음
				sb.append("0 ");
			}else {
				// stack에 현재 값보다 작으면 pop. 크면 해당 값의 인덱스 출력
				while(!stack.isEmpty()) {
					cur=stack.peek();
					if(cur.value >= curTop) break;
					stack.pop();
				}
				
				if(stack.isEmpty()) {
					sb.append("0 ");
				}else {
					sb.append(cur.idx);
					sb.append(" ");
				}
				
			}
			stack.push(new Node(curTop, idx)); // 현재 값 push
		}
		
		System.out.println(sb.toString());
	}

}