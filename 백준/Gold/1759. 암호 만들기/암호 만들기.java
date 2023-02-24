import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * [조건]
 * 최소 모음(a,e,i,o,u) 1개, 자음 2개
 * 오름차순
 * [핵심 로직]
 * 1. 무조건 오름차순이므로 조합을 구하고, 정렬하면 된다.
 * 2. 모음이 (1~L-2)개수 , 자음이(L-1~2)개수인 조합을 각각 구한다.
 * 3. 모음, 자음의 조합을 각각 구한 후, 해당 모음/자음 조합을 하나씩 뽑아 조합을 만든다
 * 4. 모음/자음을 합친 조합들을 정렬 후 문자열로 만든다.
 * 5. 해당 문자열을 정답리스트에 넣는다. 
 */

public class Main {
	
	static int L,C;
	static List<String> vowel; // 모음
	static List<String> con; // 자음
	static List<String[]> vowelPass; // 모음 조합 
	static List<String[]> conPass; // 자음 조합 
	static List<String> total; // 최종 비밀번호 조합 
	static StringBuilder sb=new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		input();
		
		String[] vpassword, cpassword;
		for(int i=1; i<=L-2; i++) {
			vowelPass.clear();
			conPass.clear();
			vpassword=new String[i]; 
			cpassword=new String[L-i];
			
			combination(0,0,i,vowel,vowelPass,vpassword); // 모음개수가 i개인 조합 
			combination(0,0,L-i,con,conPass,cpassword); // 자음개수가 L-i개인 조합 
			
			totalCombi(); // 해당 모음/자음 조합을 합치는 함수(모음 조합 1개, 자음 조합 1개 합침) 
		}
		
		print(); // 출력 
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens=new StringTokenizer(br.readLine()," ");
		L=Integer.parseInt(tokens.nextToken());
		C=Integer.parseInt(tokens.nextToken());
		
		vowel=new ArrayList<String>();
		con=new ArrayList<String>();
		vowelPass=new ArrayList<String[]>();
		conPass=new ArrayList<String[]>();
		total=new ArrayList<String>();
		tokens=new StringTokenizer(br.readLine()," ");
		
		String temp;
		while(tokens.hasMoreTokens()) { // 자음, 모음 입력을 리스트에 따로 받는다 
			temp=tokens.nextToken();
			if(temp.equals("a") || temp.equals("e") || temp.equals("i") || temp.equals("o") || temp.equals("u")) {
				vowel.add(temp);
			}else {
				con.add(temp);
			}
		}
		
	}
	// finCnt만큼의 알파벳을 뽑는 조합 
	private static void combination(int cur, int cnt, int finCnt, List<String> alphabet, List<String[]> alphaPass, String[] tempPass) {
		if(cnt==finCnt) {
			alphaPass.add(tempPass.clone());
			return;
		}
		
		for(int idx=cur; idx<alphabet.size(); idx++) {
			tempPass[cnt]=alphabet.get(idx);
			combination(idx+1, cnt+1, finCnt, alphabet, alphaPass, tempPass);
		}
	}
	// 모음 조합에서 1개, 자음 조합에서 1개 뽑아 조합을 만듦 
	// 만든 조합을 정렬한 후 문자열로 만들어 최종 리스트에 추가 
	private static void totalCombi() {
		List<String> temp= new ArrayList<String>();
		
		for(int i=0; i<vowelPass.size(); i++) {
			for(int j=0; j<conPass.size(); j++) {
				temp.clear();
				temp.addAll(Arrays.asList(vowelPass.get(i)));
				temp.addAll(Arrays.asList(conPass.get(j)));
				
				temp.sort((o1,o2)->{
					return o1.compareTo(o2);
				});
				total.add(String.join("", temp));
			}
		}
	}
	
	// 출력 함수 
	// 출력 전 문자열을 정렬해준다.
	private static void print() {
		total.sort((o1,o2)->{
			return o1.compareTo(o2);
		});
		for(String t:total) sb.append(t+"\n");
		
		System.out.println(sb);
	}
}