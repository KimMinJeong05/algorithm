//import java.util.Stream;
class Solution {
    public int solution(int[] citations) {
        int h = citations.length;
        int papers = check(citations, h);
        
        while(h>papers){
            h--;
            papers = check(citations, h);
        }
        
        return h;
    }
    
    public int check(int[] citations, int h){
        int count=0;
        for(int c:citations){
            if(c>=h) count++;
        }
        return count;
    }
}