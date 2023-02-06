import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * 핵심 로직
 * n,m이 8 이하이고 CCTV수도 최대 8개이므로
 * 최대 4^8개이므로 1초안에 모두 계산할 수 있음 
 * 
 * 1. 브루트포스로 모든 경우의 수를 다 해본다
 * 2. 모든 경우는 dfs순으로 한다
 * 2-1. dfs 중 중간에 다른 경우로 갈 때 이전 값으로 초기화한 후에 다시 dfs로 계산
 */


// 좌표 
class Point{
    int x;
    int y;
    public Point(int x, int y) {
        this.x=x;
        this.y=y;
    }
}

public class Main {
    static int n,m;
    static int[][] arr; // 사무실 
    static boolean[][] check; // 방문 여부 
    static List<Point> arrCCTVlist = new ArrayList<Point>(); // cctv list
    static int initCCTV; // 0인 부분 
    static int changeMax=0; // cctv 감시되는 부분 

	public static void main(String[] args) {
	    input();
	    allCCTV(0,0,check);
	    System.out.println(initCCTV-changeMax);
	}
	
	// input
	static void input() {
	    Scanner sc = new Scanner(System.in);
	    n = sc.nextInt();
	    m = sc.nextInt();
	    arr=new int[n][m];
	    check = new boolean[n][m];
	    
	    for(int i=0;i<n;i++) {
	        for(int j=0;j<m;j++) {
	            arr[i][j] = sc.nextInt();
	            if(arr[i][j]!=0 && arr[i][j]!=6) {
	                arrCCTVlist.add(new Point(i,j));
	            }else if(arr[i][j]==0) {
	            	initCCTV++;
	            }
	        }
	    }
	}
	
	// CCTV 종류별로 모든경우의 수를 재귀함수를 통해 실행 
	static void allCCTV(int pointIdx, int changeCount, boolean[][] check) {
		// 초기값 저
		int tempChangeCount = changeCount; 
		boolean[][] tempCheck = copy(check);
		
		if(arrCCTVlist.size()<=pointIdx) {
			changeMax = Math.max(changeMax, changeCount);
			return;
		}
		
		Point p = arrCCTVlist.get(pointIdx);
		int type = arr[p.x][p.y];
		Point[][] dict=null; // 이번 CCTV 방향 
	    if(type==1) {
	        // cctv 1
	        dict = new Point[][]{{new Point(1,0)},{new Point(-1,0)},{new Point(0,1)},{new Point(0,-1)}};
	    }else if(type==2) {
	    	// cctv 2
	        dict = new Point[][]{{new Point(1,0),new Point(-1,0)},{new Point(0,1),new Point(0,-1)}};
	    }else if(type==3) {
	    	// cctv 3
	        dict = new Point[][]{{new Point(0,-1),new Point(1,0)},{new Point(1,0),new Point(0,1)},{new Point(0,1),new Point(-1,0)},{new Point(-1,0),new Point(0,-1)}};
	    }else if(type==4) {
	    	// cctv 4
	        dict = new Point[][]{{new Point(1,0), new Point(-1,0),new Point(0,1)},{new Point(0,-1),new Point(-1,0),new Point(0,1)},{new Point(0,-1),new Point(1,0), new Point(0,1)},{new Point(0,-1),new Point(1,0), new Point(-1,0)}};
	    }
	    else if(type==5) {
	    	// cctv 5
	        dict = new Point[][]{{new Point(1,0), new Point(-1,0),new Point(0,1), new Point(0,-1)}};
	    }
	    
	    // dfs
        for(int i=0; i<dict.length;i++) {
	    	for(int j=0; j<dict[i].length;j++) {
	        	int curX= p.x;
	        	int curY= p.y;
	            while(curX>=0 && curX<n && curY>=0 && curY<m && arr[curX][curY] != 6) {
	            	if(!check[curX][curY]&&arr[curX][curY]==0) {
	            		changeCount++;
	            	}
	            	check[curX][curY]=true;
	            	curX+=dict[i][j].x;
	            	curY+=dict[i][j].y;
	            }
	    	}
	    	
	    	// 이전 값으로 초기화
	    	allCCTV(pointIdx+1,changeCount, check);
	    	changeCount=tempChangeCount;
	    	check = copy(tempCheck);
	    }
	}
	
	// 2차원 배열 copy 
	static boolean[][] copy(boolean[][] arr){
		boolean[][] newarr = new boolean[arr.length][];
		for(int i=0;i<arr.length;i++) {
			newarr[i] = Arrays.copyOf(arr[i], arr[i].length);
		}
		return newarr;
	}
}