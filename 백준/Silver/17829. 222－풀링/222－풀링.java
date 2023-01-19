import java.util.Scanner;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int size = sc.nextInt();
		int[][] arr = new int[size][size];
		
		for(int i=0;i<size;i++) {
			for(int j=0; j<size;j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		while(size>1) {
			arr = polling(size,arr);
			size/=2;
		}
		
		System.out.println(arr[0][0]);
		
	}
	
	public static int[][] polling(int size, int[][] arr) {
		int[][] temp = new int[size/2][size/2];
		for(int i=0;i<size;i+=2) {
			for(int j=0; j<size;j+=2) {
				
				int[] sortArr = {arr[i][j],arr[i+1][j],arr[i][j+1],arr[i+1][j+1]};
				Arrays.sort(sortArr);
				
				temp[i/2][j/2] = sortArr[2];
			}
		}
		return temp;
	}

}