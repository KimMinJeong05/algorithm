class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        if (a>b){
            int temp = a;
            a=b;
            b=temp;
        }

        while(!(b-a<=1 && a%2==1)){
            a = (a+1)/2;
            b = (b+1)/2;
            answer++;
        }

        return answer;
    }
}