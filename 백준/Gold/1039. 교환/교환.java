import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * [조건]
 * [풀이]
 * 1. 그리디는 너무 많은 반례가 있음 -> X
 * 2. dfs는 메모리 초과가 남 -> X
 * 
 * 이문제는 한 숫자에서 나올수 있는 모든 경우의 수로 갈 수 있는 그래프 문제
 * 같은 회에 같은 숫자가 나오면 이후의 결과가 같으므로 이후 연산은 넘어가야함 -> 시간 절약 
 */
class Main
{
	static int[] checked; // 방문 시 회전 수 
	static int n,k;
	static int[] nSize;
	
	public static void main(String args[]) throws Exception
	{
		input();
		
		System.out.println(bfs());
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		n=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		
		checked=new int[1000001];
		nSize=new int[] {0,1, 10, 100, 1000, 10000, 100000, 1000000, 10000000};
	}
	
	private static int bfs() {
		int max=-1;
		Queue<int[]> queue=new ArrayDeque<int[]>();
		
		queue.add(new int[] {n,0});
		checked[n]=0;
		
		int[] cur;
		int temp;
		int a, b;
		while(!queue.isEmpty()) {
			cur=queue.poll();
			if(cur[1]>k) break;
			
			for(int i=1; i<=7; i++) {
				if(cur[0]/nSize[i]==0) break;
				b=(cur[0]/nSize[i])%10;
				
				for(int j=i+1; j<=7; j++) {
					if(cur[0]/nSize[j]==0) break;
					if(b==0 && cur[0]/nSize[j+1]==0) continue;
					a=(cur[0]/nSize[j])%10;

					temp=cur[0]/(nSize[j+1]);
					temp=temp*10+b;
					if(j-i-1>0) temp= (temp*(int) Math.pow(10, (j-i-1))) + (cur[0]/(nSize[i+1]))%((int)Math.pow(10, (j-i-1)));
					temp=temp*10+a;
					temp=temp*nSize[i]+cur[0]%nSize[i];
					
					if(checked[temp]==cur[1]+1) continue; // 메모리제이션 
					
					checked[temp]=cur[1]+1;
					if(cur[1]+1<k) queue.add(new int[] {temp,cur[1]+1});
					if(cur[1]+1==k && max<temp) max=temp;
				}
			}
		}
		return max;
	}
}