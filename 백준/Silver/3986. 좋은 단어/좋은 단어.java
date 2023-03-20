import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		int count=0;
		char[] arr;
		for(int i=0; i<n; i++) {
			arr=br.readLine().toCharArray();
			if(arr.length%2!=0) continue;
			
			Stack<Character> stack=new Stack<Character>();
			stack.add(arr[0]);
			for(int j=1; j<arr.length; j++) {
				if(stack.size()==0 || stack.peek()!=arr[j]) stack.push(arr[j]);
				else stack.pop();
			}
			
			if(stack.size()==0) count++;
		}
		System.out.println(count);
	}
}