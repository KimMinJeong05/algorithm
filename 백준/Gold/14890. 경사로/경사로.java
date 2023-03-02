import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * [핵심 로직]
 * 가로, 세로로 다 체크해 본다.
 * 1. 높이는 1차이 까지 
 * 2. 낮은 계단이 개수에 맞게 있는지 확인 
 *    오름: 이전에 L만큼 있었는지. 
 *    내림: 이후에 L만큼 있는지. 내림 전에 내림이 있었는지, 그때 경사로를 놓을 수 있었는지 체크 
 * 3. L이 1이면 바로 경사로를 놓을 수 있으므로 cnt=1이 아니라 cnt=0으로 초기화해야함 
 */

class Main
{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N,L;
	static int[][] map;
	
	public static void main(String args[]) throws Exception
	{
		input();
		
		int sum=0;
		for(int i=0; i<N; i++) {
			if(check(i, false)) sum++; // 열별 
			if(check(i, true)) sum++; // 행별 
		}
		
		System.out.println(sum);
	}

	public static void input() throws IOException {
		StringTokenizer tokens=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(tokens.nextToken());
		L=Integer.parseInt(tokens.nextToken());
		
		map=new int[N][];
		for(int i=0; i<N; i++) {
			map[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
	}
	static boolean check(int cur, boolean type) {
		int height,cnt,slap; // 0: 경사없음, 1: 오름, 2: 내림 
		// cur 행에 대하여 
		if(type) {
			height=map[cur][0]; cnt=1; slap=0;
			for(int i=1; i<N; i++) {
				if(height==map[cur][i]) { // 평평 
					cnt++;
					if(slap==2 && cnt==L) {
						slap=0;
						cnt=0;
					}
					else if(slap!=2) slap=0;
				}
				else if(map[cur][i]-height==1) { // 오름 
					if(cnt<L) return false;
					height=map[cur][i];
					slap=1;
					cnt=1;
				}else if(height-map[cur][i]==1) { // 내림 
					if(slap==2 && cnt<L) return false;
					if(L==1) {
						cnt=0;
						slap=0;
					}
					else {
						cnt=1;
						slap=2;
					}
					height=map[cur][i];
				}else return false;
			}
			if(slap==2 && cnt<L) return false;
		}else { // cur 열에 대하여 
			height=map[0][cur]; cnt=1; slap=0;
			for(int i=1; i<N; i++) {
				if(height==map[i][cur]) { // 평평 
					cnt++;
					if(slap==2 && cnt==L) {
						slap=0;
						cnt=0;
					}
					else if(slap!=2) slap=0;
				}
				else if(map[i][cur]-height==1) { // 오름 
					if(cnt<L) return false;
					height=map[i][cur];
					slap=1;
					cnt=1;
				}else if(height-map[i][cur]==1) { // 내림 
					if(slap==2 && cnt<L) return false;
					if(L==1) {
						cnt=0;
						slap=0;
					}
					else {
						cnt=1;
						slap=2;
					}
					height=map[i][cur];
				}else return false;
			}
			if(slap==2 && cnt<L) return false;
		}
		
		return true;
	}
}