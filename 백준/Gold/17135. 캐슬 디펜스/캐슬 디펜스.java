import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * [핵심 로직]
 * 1. 궁수 3명의 조합을 구한다.
 * 2. 해당 조합으로 게임을 진행한다.
 * 2-1. turn이 끝나기 전엔 checked 배열을 갱신하지 않고, 한 turn이 끝나면 갱신한다.(궁수들이 동시에 공격을 하기 때문이다.)
 * 2-2. 궁수들의 x 좌표를 1씩 감소하며 조건에 맞게 공격한다.
 * 
 * [틀린 로직]
 * 1. 각각의 성 N칸에 모두 궁수가 있다고 생각
 * 2. 각각의 궁수들이 공격할 수 있는 적들을 다 구함
 * 3. 성 N칸 중 3가지의 조합을 구하고, 그 3가지 조합의 공격한 적들을 Set하여 최대 값을 구함
 * -> 먼저 각각의 궁수들의 공격을 계산하면, 같이 할 때 이전에 없어진 적을에 대한 정보를 갱신할 수 없어서 예외 발생.
*/


public class Main {
	
	static int N, M, D;
	static List<int[]> enemy; // 적의 x, y
	static Set<Integer> archersKill; // 조합 3명의 궁수들이 공격할 수 있는 적들
	static int[] archerSet; // 궁수들의 조합
	static int maxKill=0; // 최대 공격할 수 있는 적의 수
	
	public static void main(String[] args) throws IOException {
		input();
		
		archerSet(0,1); // 궁수를 3명 뽑는 조합. 이 조합의 공격한 적의 수 구함
		
		System.out.println(maxKill);
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens=new StringTokenizer(br.readLine(), " ");
		
		N=Integer.parseInt(tokens.nextToken());
		M=Integer.parseInt(tokens.nextToken());
		D=Integer.parseInt(tokens.nextToken());
		enemy=new ArrayList<>();
		archerSet=new int[3];
		
		for(int i=1; i<=N; i++) {
			tokens=new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=M; j++) {
				if(tokens.nextToken().equals("1")) {
					enemy.add(new int[] {i,j,0});
				}
			}
		}
	}

	// 모든 궁수들 중 3개를 뽑는 조합
	private static void archerSet(int cnt, int cur) {
		if(cnt==3) {
			archersKill = new HashSet<>();
			game(); // 해당 조합으로 게임 진행 
			
			maxKill=Math.max(maxKill, archersKill.size());
			return;
		}
		for(int i=cur; i<=M; i++) {
			archerSet[cnt]=i;
			archerSet(cnt+1, i+1);
		}
	}
	// 궁수 3명으로 게임 진행 
	private static void game() {
		boolean[] checked=new boolean[enemy.size()];
		int curLen, ar, ac;
		int curx=N+1;
		int[] temp=new int[3];
		Arrays.fill(temp, -1);
		
		// 한 턴마다 실행. 
		while(curx>1 && archersKill.size()<enemy.size()) {
			for(int i=0; i<3; i++) {
				temp[i]=makeDist(curx, archerSet[i], checked); // 현 조합의 각각 궁수들의 턴 결과를 받는다(결과: 적 id) 
			}
			// 현재 턴에서 제거한 적 id를 집합에 추가. 
			for(int i=0; i<3; i++) {
				if(temp[i]!=-1) {
					checked[temp[i]]=true;
					archersKill.add(temp[i]);
				}
			}
			curx--; // 다음 턴 
		}
	}
	// 현재 궁수가 공격할 수 있는 적 
	private static int makeDist(int ar, int ac, boolean[] checked) {
		int[] min=new int[4]; // x, y, len, id
		Arrays.fill(min,Integer.MAX_VALUE);
		
		int curLen;
		for(int idx=0; idx<enemy.size(); idx++) {
			curLen=Math.abs(ar-enemy.get(idx)[0])+Math.abs(ac-enemy.get(idx)[1]);
			
			if(ar<=enemy.get(idx)[0] || checked[idx] || curLen>D) continue;
			if((curLen<min[2]) || (curLen==min[2] && min[1]>enemy.get(idx)[1])) {
				min[0]=enemy.get(idx)[0];
				min[1]=enemy.get(idx)[1];
				min[2]=curLen;
				min[3]=idx;
			}
		}
		if(min[2]==Integer.MAX_VALUE) return -1;
		return min[3];
	}

}