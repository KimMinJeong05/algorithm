class Solution {
    public long solution(int n) {
        long[] arr = new long[n];
        arr[0]=1;
        if(n == 1) return arr[n-1];
        arr[1]=2;
        
        for(int i=2; i<n;i++){
            arr[i] = (arr[i-1]+arr[i-2])%1234567;
        }
        
        return arr[n-1];
    }
}