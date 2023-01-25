import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		int strNum = sc.nextInt();
		char[] strArr = sc.next().toCharArray();
		int[] countChar;
		String answer = "";
		boolean flag;
		char[][] strDict = {
				{'0','0','0','0','0','0'},
				{'0','0','1','1','1','1'},
				{'0','1','0','0','1','1'},
				{'0','1','1','1','0','0'},
				{'1','0','0','1','1','0'},
				{'1','0','1','0','0','1'},
				{'1','1','0','1','0','1'},
				{'1','1','1','0','1','0'},
		};
		char[] strDictChar = {'A','B','C','D','E','F','G','H'};
		
		for(int sidx = 0; sidx<strArr.length;sidx+=6) {
			countChar=new int[8];
			flag = false;
			int min=6;
			int minidx=-1;
			for(int s=0; s<strDict.length;s++) {
				for(int i=0; i<6;i++) {
					if(strDict[s][i]!=strArr[sidx+i]) {
						countChar[s]++;
					}
				}
				if(countChar[s]<=1) {
					answer+=strDictChar[s];
					flag=true;
					break;
				}
			}
			if(!flag) {
				System.out.println(sidx/6+1);
				return;
			}
		}
		System.out.println(answer);
		
	}
	
}