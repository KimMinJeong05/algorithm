import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 회전할 배열을 list로 만들어 list를 r만큼 밀어서 다시 원래 배열에 넣는 방식
 */
public class Main {
	
	static int n,m,r;
	static String[][] arr;
	
	public static void main(String[] args) throws IOException{
		input();
		
		for(int i=0; i<Math.min(n/2, m/2); i++) {
			rotation(i);
		}
		
		// print
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				sb.append(arr[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void input() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine()," ");
		n=Integer.parseInt(tokens.nextToken());
		m=Integer.parseInt(tokens.nextToken());
		r=Integer.parseInt(tokens.nextToken());
		
		arr=new String[n][m];
		for(int i=0; i<n; i++) {
			tokens = new StringTokenizer(in.readLine()," ");
			for(int j=0; j<m; j++) {
				arr[i][j] = tokens.nextToken();
			}
		}
	}
	
	// 테두리에서 start만큼 떨어진 배열 회전
	static void rotation(int start) {
		List<String> arrList = new ArrayList<String>();

		
		for(int i=start;i<n-start;i++) {// 왼쪽
			arrList.add(arr[i][start]);
		}
		for(int i=start+1;i<m-start;i++) {// 아래
			arrList.add(arr[n-start-1][i]);
		}
		for(int i=n-start-2;i>=start;i--) {// 오른쪽
			arrList.add(arr[i][m-start-1]);
		}
		for(int i=m-start-2;i>start;i--) {
			arrList.add(arr[start][i]);
		}

		// r이동 후 시작할 리스트의 index.
		int idx=(arrList.size()-r%arrList.size())%arrList.size();
		// 회전값 넣기
		for(int i=start;i<n-start;i++) {// 왼쪽
			arr[i][start]=arrList.get(idx);
			idx=(idx+1)%arrList.size();
		}
		for(int i=start+1;i<m-start;i++) {// 아래
			arr[n-start-1][i]=arrList.get(idx);
			idx=(idx+1)%arrList.size();
		}
		for(int i=n-start-2;i>=start;i--) {// 오른쪽
			arr[i][m-start-1]=arrList.get(idx);
			idx=(idx+1)%arrList.size();
		}
		for(int i=m-start-2;i>start;i--) {// 위
			arr[start][i]=arrList.get(idx);
			idx=(idx+1)%arrList.size();
		}
	}
}