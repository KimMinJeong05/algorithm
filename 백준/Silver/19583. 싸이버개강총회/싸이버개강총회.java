import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * [풀이]
 * EOF를 처리할 수 있는지가 가장 중요한 것 같다.
 * 시간은 분으로 환산하여 계산했다. 
 */

public class Main {
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		int[] stand=new int[3]; // 분단위, 시작, 끝, 종료 
		int[] temp=new int[2]; // 시간, 분  
		for(int i=0; i<3; i++) {
			temp=Arrays.stream(st.nextToken().split(":")).mapToInt(Integer::parseInt).toArray();
			stand[i]=temp[0]*60+temp[1];
		}
		
		int count=0;
		Set<String> studentIn=new HashSet<String>();
		int ct; String cn, input;
		
		while(true) {
			input=br.readLine();
			if(input==null) break;
			
			st=new StringTokenizer(input," ");
			temp=Arrays.stream(st.nextToken().split(":")).mapToInt(Integer::parseInt).toArray();
			ct=temp[0]*60+temp[1];
			cn=st.nextToken();
			
			if(ct<=stand[0]) studentIn.add(cn);
			else if(ct>=stand[1]&&ct<=stand[2] && studentIn.contains(cn)==true) {
				studentIn.remove(cn);
				count++;
			}
			
			if(studentIn.size()==0 || ct>stand[2]) break;
		}
		
		System.out.println(count);
	}

}