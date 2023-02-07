import java.util.Scanner;
import java.util.Stack;

/*
 * 핵심 로직
 * DFS로 전체 케이스를 다 체크해본다.
 * 하지만 28!(?) 경우의 수가 나오므로 2억번이 넘으므로 2초가 넘음
 * -> 백트레킹을 사용해 필요없는 경우는 빨리 끝낸다.
 * 
 * 1. dfs로 모든 경우를 다 해본다.
 * 2. 재귀로 백트레킹함
 * 
 * 
 * 주의할 점 
 * 1. 처음에 stack으로 dfs하려다 실패
 * 2. 다음 재귀함수 돌릴 파라미터 잘 생각
 */
class Domino{
	int x;
	int y;
	public Domino(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public boolean find(int x,int y) {
		if((this.x==x && this.y==y)||(this.x==y && this.y==x)) {
			return true;
		}
		return false;
	}
}


public class Main {
	static Domino[] dominos = new Domino[28];
	static boolean[] checkDominos = new boolean[28];
	static int[][] arr = new int[8][7];
	static boolean[][] checkArr = new boolean[8][7];
	static Stack<int[]> st;
	static int count=0;
	static int[][] dir= {{0,1},{1,0}};
    
	public static void main(String[] args) {
	    input();
	    
	    dfs(0,0);
	    System.out.println(count);
	}
	
	// input & 초기화
	static void input() {
	    Scanner sc = new Scanner(System.in);
	    char[] temp;
	    
	    // 격자 세팅
	    for(int i=0; i<8;i++) {
	    	temp = sc.next().toCharArray();
	    	for(int j=0;j<7;j++) {
	    		arr[i][j] = temp[j]-'0';
	    	}
	    }
	    
	    // 도미노 세팅
	    int c=0;
	    for(int i=0; i<=6;i++) {
	    	for(int j=i;j<=6;j++) {
	    		dominos[c++] = new Domino(i,j);
	    	}
	    }
	    
	}
	
	static int dfs(int x, int y) {
		if(x>=8) {
			return 1;
		}
		count=0;
		int nx = x, ny = y+1; // 현재 위치
		if(ny>=7) { // 다음줄로 개행 
			nx = x+1; ny = 0;
		}

		if(checkArr[x][y]) {
			//이미 체크됨. 다음 x,y로 이동
			return dfs(nx, ny);
		}else {
			int v1, v2; // 격자 값 
			int mx, my;
			checkArr[x][y]=true;
			v1 = arr[x][y];
			for(int[] d:dir) {
				mx=x+d[0];
				my=y+d[1];
				if(mx<8&&my<7) {
					v2 = arr[mx][my];
					int dominoIdx= isFindDomino(v1,v2);
					if(dominoIdx!=-1 && !checkArr[mx][my] && !checkDominos[dominoIdx]) {
						// 백트레킹 
						checkDominos[dominoIdx] = true;
						checkArr[mx][my]=true;
						
						count+=dfs(nx, ny); // mx, my가 아닌 다음 값인 nx, ny로 넘어가야함.
						
						checkDominos[dominoIdx] = false;
						checkArr[mx][my]=false;
					}
				}
			}
			checkArr[x][y]=false;
			return count;
		}
	}
	
	static int isFindDomino(int v1, int v2) { //도미노 인덱스 찾기 
		for(int i=0; i<dominos.length;i++) {
			if(dominos[i].find(v1, v2) || dominos[i].find(v2, v1)) return i;
		}
		return -1;
	}

}