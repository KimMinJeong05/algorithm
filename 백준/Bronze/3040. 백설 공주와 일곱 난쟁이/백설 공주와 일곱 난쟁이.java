import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* [핵심 로직]
* 1. 9개 중 2개를 뽑은 조합의 합 = (전체합-100) 
*/


public class Main {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int total=0;
		int[] hats=new int[9];
		
		for(int i=0;i<9;i++) {
			hats[i]=Integer.parseInt(in.readLine());
			total+=hats[i];
		}
		total-=100;
		
		// 조합 
		combi: for(int i=0;i<9;i++) {
			for(int j=i+1;j<9;j++) {
				if(hats[i]+hats[j]==total) {
					hats[i]=0;
					hats[j]=0;
					break combi;
				}
			}
		}
		
		for(int i=0;i<9;i++) {
			if(hats[i]==0) continue;
			System.out.println(hats[i]);
		}
	}
}