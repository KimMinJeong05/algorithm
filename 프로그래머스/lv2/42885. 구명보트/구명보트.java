import java.util.Arrays;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        int leftIdx = 0;
        int rightIdx = people.length-1;
        
        while(leftIdx<=rightIdx){
            if(leftIdx==rightIdx){
                answer++;
                break;
            }
            if(people[leftIdx]+people[rightIdx]>limit){
                answer++;
                rightIdx--;
            }else{
                answer++;
                rightIdx--;
                leftIdx++;
            }
        }
        
        return answer;
    }
}