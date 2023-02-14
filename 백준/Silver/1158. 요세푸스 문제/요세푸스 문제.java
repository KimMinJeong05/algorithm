import java.util.LinkedList;
import java.util.Scanner;

/*
 * linkedList를 사용해
 * 다음 k번째는 현재에서 K-1 더한 것이므로
 * (현재+k-1) 인덱스를 삭제하는 것을 반복한다.
 */

public class Main {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		LinkedList<Integer> arr = new LinkedList<Integer>();

		// linkedlist에 입력
		int n = sc.nextInt();
		int k = sc.nextInt();
		for(int i=1;i<=n;i++) {
			arr.add(i);
		}
		
		// linkedlist의 index는 0부터 시작하므로 k-1부터 시작
		int cur = 0;
		sb.append("<");
		while(!arr.isEmpty()) {
			cur = ((cur+k-1)%arr.size()); // 이전에 지워진 곳에서 k-1을 더하면 다음 k번째 사람
			sb.append(arr.get(cur)); // 문자열에 추가
			arr.remove(cur); // linkedList에서 삭제
			sb.append(", ");
		}
		sb.delete(sb.length()-2, sb.length()); // 뒤의 컴마 삭제
		sb.append(">");
		
		System.out.println(sb.toString());
	}

}