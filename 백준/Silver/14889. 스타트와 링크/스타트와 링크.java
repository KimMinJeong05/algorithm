import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static int personSize;
	public static int[][] statsArr;
	public static int min=0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		personSize = sc.nextInt();
		statsArr = new int[personSize][personSize]; 
		boolean[] checkArr = new boolean[personSize];
	
		for(int i=0;i<personSize;i++) {
			for(int j=0; j<personSize;j++) {
				statsArr[i][j] = sc.nextInt();
				min+=statsArr[i][j];
			}
		}
		
		checkStats(checkArr,0,0);
		System.out.println(min);
	}
	
	public static void checkStats(boolean[] checkArr, int cur, int tCount) {
		if(tCount==personSize/2) {
			int group1=0, group2=0;
			
			for(int i=0;i<personSize;i++) {
				for(int j=0; j<personSize;j++) {
					if(i!=j && checkArr[i] && checkArr[j]) {
						group1+=statsArr[i][j];
					}else if(i!=j && !checkArr[i] && !checkArr[j]) {
						group2+=statsArr[i][j];
					}
				}
			}
			
			if(min>Math.abs(group1-group2)) {
				min=Math.abs(group1-group2);
			}
			return;
		}else if(cur>=personSize) {
			return;
		}else if(cur+1-tCount>personSize/2) {
			return;
		}
		
		checkStats(checkArr, cur+1, tCount);
		boolean[] temp = Arrays.copyOf(checkArr, personSize);
		temp[cur]=true;
		checkStats(temp,cur+1,tCount+1);
	}
}
