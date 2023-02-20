import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 0: 흰, 1: 빨, 2: 파 
 * 1,2,3,4 : →, ←, ↑, ↓
 * 말이 4개 이상 쌓이는 순간 게임이 종료
 * 
 * [핵심 로직]
 * 말의 위치/이동방향, 각 위치에 있는 말의 수
 * 
*/


public class Main {
	
	static int n,k;
	static int[][] board;
	static int[][] pieces; // 말의 위치/이동방향/순서 
	static List<Integer>[] pieceCount; // 2차원 -> 1차원. 각 위치에 말들(순서대로)
	static int[] dx= {0, 0, -1, 1}; // 말 방향 
	static int[] dy= {1, -1, 0, 0};
	static int turn;
	
	public static void main(String[] args) throws IOException {
		input();
		play();
		System.out.println(turn==1001?-1:turn);
	}

	private static void input() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens=new StringTokenizer(br.readLine()," ");
		n=Integer.parseInt(tokens.nextToken());
		k=Integer.parseInt(tokens.nextToken());
		board=new int[n][];
		pieceCount=new List[n*n];
		pieces=new int[k][];
		
		for(int i=0; i<n; i++) {
			board[i]=Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
		}
		for(int i=0; i<k; i++) {
			pieces[i]=Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			pieces[i][0]-=1; pieces[i][1]-=1; pieces[i][2]-=1; 
			if(pieceCount[pieces[i][0]*n+pieces[i][1]]==null) pieceCount[pieces[i][0]*n+pieces[i][1]]=new ArrayList<Integer>();
			pieceCount[pieces[i][0]*n+pieces[i][1]].add(i);
		}
	}
	
	private static void play() {
		boolean success=false;
		
		while(turn<=1000) {
			turn++;
			int cx, cy, nx, ny;
			for(int p=0; p<k; p++) {
				cx=pieces[p][0]; cy=pieces[p][1];
				nx=pieces[p][0]+dx[pieces[p][2]]; ny=pieces[p][1]+dy[pieces[p][2]];
				
				if(nx<0||nx>=n||ny<0||ny>=n) { // boundary
					blue(cx, cy, nx, ny, p);
					continue;
				}
				
				switch(board[nx][ny]) {
					case 0: // 흰 
						white(cx, cy, nx, ny, p);
						break;
					case 1: // 빨 
						red(cx, cy, nx, ny, p);
						break;
					case 2: // 파 
						blue(cx, cy, nx, ny, p);
						break;
				}
				
				// 말이 4개 이상 쌓여있는지 확인 
				for(List<Integer> pc:pieceCount) {
					if(pc!=null && pc.size()>=4) {
						success=true;
						break;
					}
				}
				if(success) break;
				
			}
			if(success) break;
		}
	}
	// 흰판 
	private static void white(int cx, int cy, int nx, int ny, int p) { // 이동 x, y ,말 종류(인덱스) 
		if(pieceCount[nx*n+ny]==null) pieceCount[nx*n+ny]=new ArrayList<Integer>();
		
		boolean flag=false;
		List<Integer> removeIdx=new ArrayList<Integer>();
		for(int i=0; i<pieceCount[cx*n+cy].size();i++) {
			int mp=pieceCount[cx*n+cy].get(i);
			if(mp==p) flag=true;
			if(flag) { // 같이 옮겨야할 말들 
				pieces[mp][0]=nx; pieces[mp][1]=ny;
				pieceCount[nx*n+ny].add(mp);
				removeIdx.add(i);
			}
		}
		int minus=0;
		for(int ri:removeIdx) {
			pieceCount[cx*n+cy].remove(ri-minus++);
		}
	}
	// 빨간 판 
	private static void red(int cx, int cy, int nx, int ny, int p) { // 이동 x, y ,말 종류(인덱스) 
		if(pieceCount[nx*n+ny]==null) pieceCount[nx*n+ny]=new ArrayList<Integer>();
		
		List<Integer> removeIdx=new ArrayList<Integer>();
		for(int i=pieceCount[cx*n+cy].size()-1; i>=0;i--) {
			int mp=pieceCount[cx*n+cy].get(i);
			pieces[mp][0]=nx; pieces[mp][1]=ny;
			pieceCount[nx*n+ny].add(mp);
			pieceCount[cx*n+cy].remove(i);
			if(mp==p) break;
		}
		for(int ri:removeIdx) {
			pieceCount[cx*n+cy].remove(ri);
		}
	}
	// 파란 판 
	private static void blue(int cx, int cy, int nx, int ny, int p) { // 이동 x, y ,말 종류(인덱스) 
		if(pieces[p][2]%2==0) pieces[p][2]+=1;
		else pieces[p][2]-=1;
		
		nx=pieces[p][0]+dx[pieces[p][2]]; ny=pieces[p][1]+dy[pieces[p][2]];
		if(nx<0||nx>=n||ny<0||ny>=n) return;
		if(pieceCount[nx*n+ny]==null) pieceCount[nx*n+ny]=new ArrayList<Integer>();
		
		switch(board[nx][ny]) {
			case 0: // 흰 
				white(cx, cy, nx, ny, p);
				break;
			case 1: // 빨 
				red(cx, cy, nx, ny, p);
				break;
			case 2: // 파 
				break;
		}
	}
}