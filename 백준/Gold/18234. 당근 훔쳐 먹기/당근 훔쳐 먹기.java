import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static long K;
	static int[][] carrots;
	static int N,size;
	
	public static void main(String[] args) throws IOException{
		input();
		
		System.out.println(calculate());
	}

	private static void input() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		carrots=new int[N][2];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			carrots[i][0]=Integer.parseInt(st.nextToken());
			carrots[i][1]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(carrots,(o1, o2)->{
			return o2[1]-o1[1];
		});
	}
	private static long calculate() {
		long result=0;
		
		for(int i=0; i<N; i++) {
			if(K==0) break;
			result+=(long)carrots[i][0];
			result+=((long)carrots[i][1]*--K);
		}
		
		return result;
	}
}