class Solution {
    /* 1차 풀이
    public int solution(int n) {
        int answer = 0;
        int sum = 0;
        int next = 0;
        
        for(int start = 1; start<=n; start++){
            sum=start;
            next=start+1;
            while(sum<=n){
                if(sum==n){
                    answer++;
                    break;
                }
                sum+=next;
                next++;
            }
        }
        
        return answer;
    }
    */
    // 좀 더 개선한 풀이
    public int solution(int n) {
        int answer = 0; 
        int start = 1;
        int sum = 0; 
        
        for(int end = 1; end<=n; end++){
            sum+=end;

            while(sum>n){
                sum-=start++;
            }
            
            if(sum==n){
                answer++;
            }
           
        }
        
        return answer;
    }
}