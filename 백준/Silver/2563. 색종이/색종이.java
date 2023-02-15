import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * [구현 로직]
 * 1차원 배열 
 * 1. n~m에 1을 더해야한다면, n에 +1을 넣고 m+1에 -1을 넣음
 * 2. 그 후 누적합으로 더함. 
 * 
 * 이차원 배열 
 * 1. x, y를 붙여야한다면, (x,y)->+1, (x,y+10)->-1, (x+10,y+10)->1, (x+10,y)->-1
 * 2. 그 후 누적합으로 더함. 
 * 2-1. 누적합을 하므로 0인 부분은 남겨두고 1부터 시작. 
 * 2-2. 누적합이 0 이상이면 색종이 부분. 
 */



public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		int n=Integer.parseInt(in.readLine());
		int[][] paper=new int[102][102];
		
		int x,y;
		for(int i=0;i<n;i++) {
			tokens=new StringTokenizer(in.readLine()," ");
			x=Integer.parseInt(tokens.nextToken())+1;
			y=Integer.parseInt(tokens.nextToken())+1;
			
			paper[x][y]++;
			paper[x][y+10]--;
			paper[x+10][y+10]++;
			paper[x+10][y]--;
		}
		
		// 누적합 
		int count=0;
		for(int i=1;i<102;i++) {
			for(int j=1;j<102;j++) {
				paper[i][j]+=paper[i-1][j]+paper[i][j-1]-paper[i-1][j-1];
				
				if(paper[i][j]>0) count++;
			}
		}
		System.out.println(count);
	}

}