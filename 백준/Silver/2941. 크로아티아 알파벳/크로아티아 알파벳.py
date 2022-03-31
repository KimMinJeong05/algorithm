# 구현
# 크로아티아 알파벳 6개는 2개 or 3개의 문자로 이루어져 있다.
# 따라서 입력된 단어를 첫번째 단어부터 2개 or 3개로 slice해서 크로아티아 알파벳에 속하는 문자인지 확인한다.
# 2개 or 3개로 자른 단어가 크로아티아 알파벳에 속하면 count+=1을 하고 다음 문자의 인덱스로 넘어간다.
# 2개 or 3개로 자른 단어가 크로아티아 알파벳에 속하지 않으면 한 단어만 건너뛰로 count+=1을 한다.

alpa_list = ['c=', 'c-', 'dz=','d-','lj','nj','s=','z=']
input_str = input()
count = 0
idx=0
while idx<len(input_str):
    if input_str[idx:idx+2] in alpa_list:
        idx+=2
    elif input_str[idx:idx+3] in alpa_list:
        idx+=3
    else:
        idx+=1
    count+=1
print(count)
