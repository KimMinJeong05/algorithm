import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int[] record;
	static int size;
	static int[] checknum=new int[3];
	static char[] person= {'A','B','C'};
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
		input();
		
		if(dfs()!=0) System.out.println(-1);
		else System.out.println(sb);
	}

	private static void input() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		char[] temp=br.readLine().toCharArray();
		size=temp.length;
		
		record=new int[3];
		for(int i=0; i<size; i++) {
			record[(int)(temp[i]-'A')]++;
		}
		
		Arrays.fill(checknum, 3);
		sb=new StringBuilder();
	}
	
	private static int dfs() {
//		System.out.println(sb);
//		System.out.println(Arrays.toString(record));
//		System.out.println(Arrays.toString(checknum));
		int result=-2;
		if(record[0]==0 && record[1]==0 && record[2]==0) return 0;
		
		if((record[1]==0 || checknum[1]<1)&&(record[2]==0 || checknum[2]<2)) {
			if(record[0]<=0) return -1; //-1
			record[0]--;
			checknum[1]++;
			checknum[2]++;
			sb.append("A");
			
			result=Math.max(dfs(),result);
			if(result==0) return 0;
			
			record[0]++;
			checknum[1]--;
			checknum[2]--;
			sb.deleteCharAt(sb.length()-1);
			return result;
		}
		
		int temp;
		for(int i=1; i<=2; i++) {
			if(record[i]<=0 || checknum[i]<i) continue;
			record[i]--;
			temp=checknum[i];
			checknum[i]=0;
			checknum[i^3]++;
			sb.append(person[i]);
			
			result=Math.max(dfs(),result);
			if(result==0) return 0;
			
			record[i]++;
			checknum[i]=temp;
			checknum[i^3]--;
			sb.deleteCharAt(sb.length()-1);
		}
		return result;
	}
}