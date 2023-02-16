import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* [핵심 로직]
* a*3+b*5
* 
* 1. n을 n/5 ~ 0로 나누고, 나머지값을 3으로 나눠보기
*/


public class Main {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(in.readLine());
		
		for(int i=n/5;i>=0;i--) {
			if((n-5*i)%3==0){
				System.out.println(i+((n-5*i)/3));
				return;
			}
		}
		
		System.out.println(-1);
	}
}