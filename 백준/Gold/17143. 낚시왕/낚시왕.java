import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * [풀이]
 * 방향: 1-위, 2-아래, 3-오른쪽, 4-왼쪽
 * 인접행렬 -> 같은 위치에 상어가 있는지 쉽게 알 수 있음. 상어 개수가 적으면 비효율적
 * 리스트 -> 상어 개수가 적을때 효율적. 같은 위치에 상어가 있는지 알기 어렵
 * 
 * 인접행렬과 리스트 둘다 사용
 * 위/왼쪽 과 아래/오른쪽은 움직이는 방향만 다르지 증가하는 양은 같으므로 하나로 계산
 * 
 * [결과]
 */

public class Main {
	
	static int R,C,M;
	static int[][] water; // shark의 index 번호(0은 상어 없는 것)
	static int[][] shark; // 상어 정보(행,열,속력, 이동 방향, 크기)
	static int[] dir= {0,-1,1,-1,1}; //  1-위, 2-아래, 3-왼쪽, 4-오른쪽

	public static void main(String[] args) throws IOException {
		input();
		
		int sum=0;
		for(int k=1; k<=C; k++) {
			sum+=catchShark(k); // 상어 낚시
			moveShark(); // 상어 움직이기
		}
		
		System.out.println(sum);
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		int[] temp;
		water=new int[R+1][C+1];
		shark=new int[M+1][]; // index 1부터
		for(int i=1; i<=M; i++) {
			temp=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if(temp[3]==3) temp[3]=4; 
			else if(temp[3]==4) temp[3]=3;
			shark[i]=Arrays.copyOf(temp, 5);
			water[temp[0]][temp[1]]=i;
		}
	}
	// 상어 낚시
	static int catchShark(int king) {
		int sum=0;
		for(int r=1; r<=R; r++) {
			if(water[r][king]==0) continue;
			
			sum+=shark[water[r][king]][4];
			shark[water[r][king]]=null;
			water[r][king]=0;
			break;
		}
		return sum;
	}
	// 상어 움직이기
	static void moveShark() {
		int x=0,y=0,s=0,d=0,z=0,len=0, cur=0;
		
		int[][] temp=new int[R+1][C+1];
		for(int i=shark.length-1; i>0; i--) {
			if(shark[i]==null) continue;
			x=shark[i][0]; y=shark[i][1];
			s=shark[i][2]; d=shark[i][3]; z=shark[i][4];
			
			// 상하 / 좌우 중 어떤 걸로 움직이는지 파악후 하나로 통일
			if(d<3) {
				cur=x;
				len=R;
			}
			else {
				cur=y;
				len=C;
			}
			
			// 한 주기만 계산하도록 조절
			s=s%(2*(len-1));
			// 한 주기 안에서 상어 움직이기
			while(s-->0) {
				if(cur==len && d%2==0) d=d-1;
				else if(cur==1 && d%2!=0) d=d+1;
				cur+=dir[d];
			}

			// 상어 옮기기
			if(d<3) {
				if(temp[cur][y]==0 || shark[temp[cur][y]][4]<z){
					shark[temp[cur][y]]=null;
					temp[cur][y]=i;
					shark[i][0]=cur;
					shark[i][3]=d;
				}
				else shark[i]=null;
			}
			else {
				if(temp[x][cur]==0 || shark[temp[x][cur]][4]<z){
					shark[temp[x][cur]]=null;
					temp[x][cur]=i;
					shark[i][1]=cur;
					shark[i][3]=d;
				}
				else shark[i]=null;
			}
		}
		
		water=temp;
	}
}