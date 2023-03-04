import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;


/*
 * [풀이]
 * n<=500,000 이므로 for문을 이용하기엔 시간이 너무 많이 걸린다.
 * 자신에게 가장 가까우면서 큰 탑을 찾아야하므로 스택을 사용한다.
 * 앞부터 자신을 스택에 넣기전에 자신보다 큰 탑이 있는지 확인한 후 자신을 넣는다. 
 * 자신보다 작으면 자신에게 막혀 뒤에 탑들의 수신을 못받으므로 stack에서 뺀다. 
 */
//Solution_2115_김민정
class Main
{
	static int n;
	static int[] top;
	
	public static void main(String args[]) throws Exception
	{
		input();
		
		StringBuilder sb=new StringBuilder();
		Stack<Integer> stack=new Stack<Integer>();
		int curIdx=0, save;
		while(curIdx<n) {
			if(stack.isEmpty()) { // stack에 없으면 신호를 받을 탑이 없다는 것 
				stack.add(curIdx++);
				sb.append(0+" ");
				continue;
			}
			
			save=stack.peek();
			if(top[save]<top[curIdx]) { // 현재 스택에서나 온 값이 들어갈 탑보다 작은 경우 
				stack.pop();
				continue;
			}
			
			// 신호를 받을 탑을 나온 경
			stack.add(curIdx++);
			sb.append((save+1)+" ");
		}
		
		System.out.println(sb);
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		top=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
}