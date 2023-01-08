
class Solution {
    public int solution(int n) {
        int answer = 0;
        long[] fibo = new long[n+1];
        fibo[0]=0;
        fibo[1]=1;
        
        for(int i=2;i<=n;i++){
            fibo[i]=(fibo[i-1]+fibo[i-2])%1234567;
        }
        
        answer = (int)(fibo[n] % 1234567);
        
        return answer;
    }
}