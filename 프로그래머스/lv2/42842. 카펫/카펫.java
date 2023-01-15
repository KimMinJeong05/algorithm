class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {0,0};
        int sum = brown+yellow;
        double n, m;
        for(n=3; n<sum/2; n++){
            m = sum*1.0/n;
            if(m==(int)m &&(n-2)*(m-2)==yellow){
                answer[0]=(int)m;
                answer[1]=(int)n;
                break;
            }
        }
        return answer;
    }
}