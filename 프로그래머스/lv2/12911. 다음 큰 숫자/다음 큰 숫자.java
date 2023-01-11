import java.util.Arrays;
class Solution {
    public int solution(int n) {
        int answer = n;

        int countOne = Integer.bitCount(n);
        while(true){
            answer+=1;
            int count = Integer.bitCount(answer);
            if(countOne == count){
                break;
            }
        }
        
        return answer;
    }
}