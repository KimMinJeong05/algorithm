class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] total=new int[board.length+1][board[0].length+1];
        
        for(int[] s: skill){
            if(s[0]==1){ // 적일 경우, 내구도 낮춰야하므로 음수로 변경
                s[5]=-s[5];
            }
            total[s[1]][s[2]]+=s[5];
            total[s[3]+1][s[4]+1]+=s[5];
            total[s[1]][s[4]+1]+=-s[5];
            total[s[3]+1][s[2]]+=-s[5];
        }
        
        // 변경된 내구도 계산(누적합)
        for(int r=0; r<total.length;r++){
            for(int c=1; c<total[0].length;c++){
                total[r][c] += total[r][c-1];
            }
        }
        for(int c=0; c<total[0].length;c++){
            for(int r=1; r<total.length;r++){
                total[r][c] += total[r-1][c];
            }
        }
        
        // 기존 board의 전체 내구도 변경 값 반영
        for(int r=0; r<board.length;r++){
            for(int c=0; c<board[0].length;c++){
                if(board[r][c]+total[r][c]>0) answer++;
            }
        }
        
        return answer;
    }
}