import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 첫 부분문자열의 각각의 DNA 수를 계산한 후
 * start point, end point를 1씩 증가하면서 DNA 수 갱신
 */

public class Main {
	
	static int S, P;
	static String str;
	static Map<Character, Integer> conditions = new HashMap<Character, Integer>(); // 'A', 'C', 'G', 'T'의 조건
	static char[] dnaKind = {'A', 'C', 'G', 'T'};

	public static void main(String[] args) throws IOException {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine()," ");
		S = Integer.parseInt(tokens.nextToken());
		P = Integer.parseInt(tokens.nextToken());
		str = in.readLine();
		tokens = new StringTokenizer(in.readLine()," ");
		for(char d:dnaKind) {
			conditions.put(d, Integer.parseInt(tokens.nextToken()));
		}
		
		System.out.println(findPassword());
	}
	
	// 부분집합
	static int findPassword() {
		int start=0,end=P-1, count=0;
		Map<Character, Integer> countDna = new HashMap<Character, Integer>(){{
			put('A', 0);
			put('C', 0);
			put('G', 0);
			put('T', 0);
		}};
		// 첫 부분문자열의 DNA별 개수
		for(int i=0;i<P;i++) {
			countDna.put(str.charAt(i),countDna.get(str.charAt(i))+1);
		}
		
		
		while(end<S) {
			// 조건 확인
			boolean flag = true;
			for(char d:dnaKind) {
				if(countDna.get(d)<conditions.get(d)) {
					flag=false;
					break;
				}
			}
			if(flag) count++;
			
			// 다음 부분문자열의 DNA 개수
			// 이전의 start를 빼로 현재의 end 추가
			start++; end++;
			if(end>=S) break;
			countDna.put(str.charAt(start-1),countDna.get(str.charAt(start-1))-1);
			countDna.put(str.charAt(end),countDna.get(str.charAt(end))+1);
		}
		return count;
	}
}