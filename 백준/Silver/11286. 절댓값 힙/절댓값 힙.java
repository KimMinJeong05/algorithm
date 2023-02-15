import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

/*
 * [핵심 로직]
 * 항상 작은 숫자를 추출하므로 우선순위 큐 사용
 * 우선순위 큐 생성 시, 람다식으로 정렬 기준 설정
 * 
 * [주의]
 * pop할 때 비어있으면 null이 나옴
 * int엔 null이 들어갈 수 없으므로 Integer레퍼클래스 사용
 */
public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int n;
    // 1차 오름차순 기준 절대값, 2차 오름차순 기준 부호있는 원래 값
	static PriorityQueue<Integer> queue = new PriorityQueue<Integer>((o1, o2)-> {
		if(Math.abs(o1)-Math.abs(o2)<0) return -1;
		else if(Math.abs(o1)-Math.abs(o2)>0) return 1;
		return o1-o2;
	});
	
	public static void main(String[] args) throws IOException{
		input();
		
		// 전체 print
		bw.flush();
	}
	
	static void input() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		n=Integer.parseInt(in.readLine());
		
		int cur;
		for(int i=0;i<n;i++) {
			action(Integer.parseInt(in.readLine()));
		}
	}
    // pop, push 실행
	static void action(int cur) throws IOException {
		if(cur==0) {
			// pop
			Integer pop = queue.poll();
			bw.write(((pop==null)?0:pop)+"\n");
		}else {
			// push
			queue.add(cur);
		}
	}
}