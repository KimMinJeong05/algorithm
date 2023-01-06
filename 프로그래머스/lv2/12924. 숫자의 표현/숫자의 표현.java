class Solution {
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
}