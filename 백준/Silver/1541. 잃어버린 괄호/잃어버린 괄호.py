# -기호로 split하면 0번째 말고는 다 -뒤로 괄호를 넣어주면 됨
# 따라서 [0]만 +이고 뒤의 요소부터는 -로 빼준다.
exp = list(map(str, input().split('-')))
total = 0

# 첫번째 요소
e_temp = exp[0].split('+')
for i in e_temp:
    total += int(i)
# 두번째 요소 ~
for e in exp[1:]:
    e_temp = e.split('+')
    for i in e_temp:
        total -= int(i)
        
print(total)