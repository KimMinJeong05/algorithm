import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	static int num;
	static BigInteger count= new BigInteger("0");
	static BigInteger[] hanoiMemo;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		num = sc.nextInt();
		hanoiMemo = new BigInteger[num+1];
		
		hanoi(num, 1, 3, 2);
		System.out.println(count);
		System.out.println(sb.toString());
	}
	
	static void hanoi(int move, int from, int to, int temp) {
		if(move==0) {
			return;
		}
		
		if(num<=20) {
			hanoi(move-1, from, temp, to);
			
			count = count.add(BigInteger.valueOf(1));
			sb.append(from);
			sb.append(" ");
			sb.append(to);
			sb.append("\n");
			
			hanoi(move-1,temp, to, from);
		}else {
			if (hanoiMemo[move-1]==null) hanoi(move-1, from, temp, to);
			else count=count.add(hanoiMemo[move-1]);
			
			count=count.add(BigInteger.valueOf(1));
			
			if (hanoiMemo[move-1]==null) hanoi(move-1,temp, to, from);
			else count=count.add(hanoiMemo[move-1]);
			
			hanoiMemo[move] = count;
		}
	}
}
