import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* [핵심 로직]
* 1. 과일을 오름차순으로 정렬
* 2. 과일을 순차적으로 돌면서 길이 l보다 작으면 l이 길어
*/

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine()," ");
		
		int n=Integer.parseInt(tokens.nextToken());
		int l=Integer.parseInt(tokens.nextToken());
		
		int[] fruit=Arrays.stream(in.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		Arrays.sort(fruit);
		
		for(int i=0;i<n;i++) {
			if(fruit[i]>l) break;
			l++;
		}
		
		System.out.println(l);
	}
}