n = int(input())

word = list()
for i in range(n):
    word.append(input())

word_set = list(set(word)) # 중복 제거
word_set.sort() # 오름차순으로 정렬
word_set.sort(key=lambda x : len(x)) # 글자 길이로 정렬

for i in range(len(word_set)):
    print(word_set[i])