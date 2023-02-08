import java.util.Scanner;

public class Main {
	static int switchLen;
	static int[] switches;

	public static void main(String[] args) {
		// input
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		switchLen = sc.nextInt();
		switches = new int[switchLen+1];
		for(int i=1; i<=switchLen;i++) {
			switches[i] = sc.nextInt();
		}
		
		int studentLen = sc.nextInt();
		int studentSex, studentNum;
		for(int i=0; i<studentLen;i++) {
			studentSex = sc.nextInt();
			studentNum = sc.nextInt();
			
			// solve
			if(studentSex==1) { // 남자
				for(int s=studentNum; s<=switchLen;s+=studentNum) {
					switches[s] = switches[s]^1;
				}
			}else{ //여자
				wmChange(studentNum, studentNum);
			}
		}
		
		// answer sysout
		for(int i=1; i<=switchLen;i++) {
			sb.append(switches[i]);
			sb.append(" ");
			if(i%20==0) sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void wmChange(int studentNum1, int studentNum2) {
		if(switches[studentNum1] == switches[studentNum2]) {
			switches[studentNum1] = switches[studentNum1]^1;
			if(studentNum1!= studentNum2) switches[studentNum2] = switches[studentNum2]^1;
			if(studentNum1>1 && studentNum2<switchLen) {
				wmChange(studentNum1-1, studentNum2+1);
			}
		}
	}
}