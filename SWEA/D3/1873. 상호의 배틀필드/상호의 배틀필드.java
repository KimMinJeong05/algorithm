import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * [핵심 로직]
 * 1. 방향 정보를 방향 배열의 인덱스 숫자와 맞게 바꾼다.
 * 
 * 2. 유저의 입력에 따라 게임 진행
 * 2-1. 포탄: 현재 바라보고 있는 방향에 벽이 있을 때까지 직진.
 * 2-1-1. 벽이 벽돌이면 부수고 멈춤. 철문이면 그냥 멈춤 
 * 3. 방향: 
 * 3-1. 각 방향으로 전차를 회전 
 * 3-2. 회전한 방향으로 벽이나 물이 없으면 전진. 전진하면 전차가 있던 자리는 평지로. 
 * 3-3. 벽이나 물이 있으면 회전만 하고 다음 입력으로 넘어감. 
*/


public class Solution {
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	static int h,w,n;
	static char map[][]; // 맵 
	static int[] car; // 전차 위치, 전차 방향 
	static char[] users; // 사용자 입력들 0(U),1(R),2(D),3(L)
	static int[] dr= {-1,0,1,0}; // 위(U), 오른쪽(R), 아래(D), 왼쪽(L) 
	static int[] dc= {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			input();
			
			// 게임 
			for(int i=0; i<n; i++) {
				play(users[i]);
			}
			
			// 전차 방향 나타내주기 
			char temp=' ';
			switch(car[2]) {
			case 0:
				temp='^';
				break;
			case 1:
				temp='>';
				break;
			case 2:
				temp='v';
				break;
			case 3:
				temp='<';
				break;
			}
			map[car[0]][car[1]]=temp;
			
			print(t);
		}
		bw.flush();
		bw.close();
	}

	private static void input() throws IOException {
		StringTokenizer tokens=new StringTokenizer(br.readLine()," ");
		h=Integer.parseInt(tokens.nextToken());
		w=Integer.parseInt(tokens.nextToken());
		
		map=new char[h][w];
		car=new int[3];
		for(int i=0; i<h; i++) {
			String temp=br.readLine();
			for(int j=0; j<w; j++) {
				map[i][j]=temp.charAt(j);
				if(map[i][j]=='^' || map[i][j]=='v' ||  map[i][j]=='<' || map[i][j]=='>') {
					if(map[i][j]=='^') map[i][j]='0';
					else if(map[i][j]=='>') map[i][j]='1';
					else if(map[i][j]=='v') map[i][j]='2';
					else if(map[i][j]=='<') map[i][j]='3';
					car[0]=i;
					car[1]=j;
					car[2]=(int)(map[i][j]-'0');
				}
			}
		}
		
		n=Integer.parseInt(br.readLine());
		users=br.readLine().toCharArray();
		for(int i=0; i<n; i++) {
			if(users[i]=='U') users[i]='0';
			else if(users[i]=='R') users[i]='1';
			else if(users[i]=='D') users[i]='2';
			else if(users[i]=='L') users[i]='3';
		}
	}
	private static void play(char cur) {
		int nr, nc;
		if(cur=='S') { // 포탄 
			nr=car[0]+dr[car[2]]; nc=car[1]+dc[car[2]];
			while(nr>=0 && nr<h && nc>=0 && nc<w) {
				if(map[nr][nc]=='*') { // 벽돌 
					map[nr][nc]='.';
					break;
				}
				else if(map[nr][nc]=='#') break; // 강철벽 
				
				nr+=dr[car[2]]; nc+=dc[car[2]];
			}
		}
		else { // 이동 
			int curDir=(int) (cur-'0');
			car[2]=curDir;
			nr=car[0]+dr[curDir]; nc=car[1]+dc[curDir];
			
			if(nr<0 || nr>=h || nc<0 || nc>=w) return;
			if(map[nr][nc]=='*' || map[nr][nc]=='#' || map[nr][nc]=='-') { // 벽들, 물 
				return;
			}
				
			// 평지 
			map[nr][nc]=cur;
			map[car[0]][car[1]]='.';
			car[0]=nr; car[1]=nc;
		}
	}
	
	private static void print(int t) throws IOException {
		bw.write("#"+t+" ");
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				bw.write(map[i][j]);
			}
			bw.write("\n");
		}
	}
}