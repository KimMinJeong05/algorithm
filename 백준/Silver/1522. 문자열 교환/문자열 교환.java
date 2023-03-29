import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 원형 모양이므로 문자열을 복사에 뒤에 붙여준다.
 * a,b의 각각의 개수만큼 붙어있어야 한다.
 * -> a나 b의 총 수만큼 윈도우를 만들어 그 안에 다른 문자(b나 a)의 개수가 최소인 값을 찾는다(다른 문자만큼 변경 필요)
 */

public class Main {

	private static String str;
	private static char sv; // 기준 문자
	private static int sc; // 기준 문자 총 개수 
	private static int min;
	
	public static void main(String[] args) throws IOException {
		input();
		
		window();
		
		System.out.println(min);
	}
	
	public static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		str=br.readLine();
		
		int temp=0;
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(0)==str.charAt(i)) temp++;
		}
		sc=Math.min(temp, str.length()-temp);
		sv=sc==temp?str.charAt(0):str.charAt(0)=='a'?'b':'a';
		
		str+=str;
		min=Integer.MAX_VALUE;
	}
	
	public static void window() {
		int count;
		for(int i=0; i<str.length()/2; i++) {
			count=0;
			for(int s=i; s<sc+i; s++) {
				if(sv!=str.charAt(s)) count++;
				if(min<=count) break;
			}
			min=Math.min(count, min);
		}
	}
	
	
}