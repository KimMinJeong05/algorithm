import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		String[] set=new String[n];
		for(int i=0; i<n; i++) {
			set[i]=br.readLine();
		}
		Arrays.sort(set);
		
		int count=0;
		for(int i=0; i<m; i++) {
			if(Arrays.binarySearch(set,br.readLine())>=0) count++;
		}
		System.out.println(count);
	}
}