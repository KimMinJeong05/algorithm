import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 구현 
 */
public class Main {
	
	static int n,m,r;
	static int[][] arr;
	static int[] rList;
	
	public static void main(String[] args) throws IOException{
		input();
		
		for(int curR:rList) {
			calculation(curR);
		}
		
		print();
	}
	
	static void input() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine()," ");
		
		n=Integer.parseInt(tokens.nextToken());
		m=Integer.parseInt(tokens.nextToken());
		r=Integer.parseInt(tokens.nextToken());
		
		arr=new int[n][];
		for(int i=0; i<n;i++) {
			arr[i]=Arrays.stream(in.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
		}
		
		rList = Arrays.stream(in.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();	
	}
	static void calculation(int kind) {
		switch(kind) {
		case 1:
			cal1();
			break;
		case 2:
			cal2();
			break;
		case 3:
			cal3();
			break;
		case 4:
			cal4();
			break;
		case 5:
			cal5();
			break;
		case 6:
			cal6();
			break;
		}
	}
	static void print() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0; i<n; i++) {
			for(int j=0;j<m; j++) {
				bw.write(arr[i][j]+" ");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
	
	
	static void cal1() {
		int temp;
		for(int j=0; j<m; j++) {
			for(int i=0; i<n/2; i++) {
				temp=arr[i][j];
				arr[i][j]=arr[n-i-1][j];
				arr[n-i-1][j]=temp;
			}
		}
	}
	static void cal2() {
		int temp;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m/2; j++) {
				temp=arr[i][j];
				arr[i][j]=arr[i][m-j-1];
				arr[i][m-j-1]=temp;
			}
		}
	}
	static void cal3() {
		// 배열의 크기가 달라지므로 배열을 새로 만들어야함
		int start=-1;
		int[][] tempArr=new int[m][n]; 		

		while(++start<Math.min(n, m)/2) {
			// 상->좌
			for(int i=0; i<m-1;i++) {
				tempArr[i][n-start-1]=arr[start][i];
			}
			// 좌->하
			for(int i=0; i<n-1;i++) {
				tempArr[m-start-1][n-i-1]=arr[i][m-start-1];
			}
			// 하->우
			for(int i=1; i<m;i++) {
				tempArr[i][start]=arr[n-start-1][i];
			}
			// 우->상
			for(int i=1; i<n;i++) {
				tempArr[start][n-i-1]=arr[i][start];
			}
		}
		
		arr=tempArr;
		int temp=m; m=n; n=temp;
	}
	static void cal4() {
		// 배열의 크기가 달라지므로 배열을 새로 만들어야함
		int start=-1;
		int[][] tempArr=new int[m][n];
		
		while(++start<Math.min(n, m)/2) {
			// 상->우
			for(int i=0; i<m-1;i++) {
				tempArr[m-i-1][start]=arr[start][i];
			}
			// 우->하
			for(int i=1; i<n;i++) {
				tempArr[m-start-1][i]=arr[i][start];
			}
			// 하->좌
			for(int i=1; i<m;i++) {
				tempArr[m-i-1][n-start-1]=arr[n-start-1][i];
			}
			// 좌->상
			for(int i=0; i<n-1;i++) {
				tempArr[start][i]=arr[i][m-start-1];
			}
		}
		
		arr=tempArr;
		int temp=m; m=n; n=temp;
	}
	static void cal5() {
		int sn=n/2, sm=m/2;
		int temp;
		
		for(int i=0;i<sn;i++) {
			for(int j=0;j<sm;j++) {
				temp = arr[i][j];
				arr[i][j] = arr[i+sn][j]; // 4->1
				arr[i+sn][j] = arr[i+sn][j+sm]; // 3->4
				arr[i+sn][j+sm] = arr[i][j+sm]; // 2->3
				arr[i][j+sm] = temp; // 1->2
			}
		}
	}
	static void cal6() {
		int sn=n/2, sm=m/2;
		int temp;
		
		for(int i=0;i<sn;i++) {
			for(int j=0;j<sm;j++) {
				temp = arr[i][j];
				arr[i][j] = arr[i][j+sm]; // 2->1
				arr[i][j+sm] = arr[i+sn][j+sm]; // 3->2
				arr[i+sn][j+sm] = arr[i+sn][j]; // 4->3
				arr[i+sn][j] = temp; // 1->4
			}
		}
	}
}