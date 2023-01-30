import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		long count = 0;
		int n = sc.nextInt();
		int[] classes = new int[n];
		for(int i=0; i<n;i++) {
			classes[i]=sc.nextInt();
		}
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		// 총 감독관 필수 + 나머지는 부감독관들 
		for(int i=0; i<n;i++) {
			classes[i]-=b;
			count++;
			
			if(classes[i]>0) {
				count+=Math.ceil(classes[i]/(double)c);
			}
		}
		
		System.out.println(count);
	}
}
