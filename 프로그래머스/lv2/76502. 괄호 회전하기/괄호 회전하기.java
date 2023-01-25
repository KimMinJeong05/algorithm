import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;
        char[] arr = s.toCharArray();
        
        for(int i=0;i<arr.length;i++){
            Stack<Character> stack = new Stack<>();
            for(int j=0;j<arr.length;j++){
                switch(arr[j]){
                    case '{':
                    case '(':
                    case '[':
                        stack.push(arr[j]);
                        break;
                    case '}':
                        if(!stack.empty()&&stack.peek()=='{'){
                            stack.pop();
                        }else{
                            stack.push(arr[j]);
                        }
                        break;
                    case ')':
                        if(!stack.empty()&&stack.peek()=='('){
                            stack.pop();
                        }else{
                            stack.push(arr[j]);
                        }
                        break;
                    case ']':
                        if(!stack.empty()&&stack.peek()=='['){
                            stack.pop();
                        }else{
                            stack.push(arr[j]);
                        }
                        break;
                }
            }
            if(stack.empty()) answer++;
            
            for(int j=0;j<arr.length-1;j++){
                char temp = arr[j];
                arr[j]=arr[j+1];
                arr[j+1]=temp;
            }
        }
        
        return answer;
    }
}