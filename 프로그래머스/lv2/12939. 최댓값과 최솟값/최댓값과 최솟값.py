def solution(s):
    slist = sorted(list(map(int, s.split())))
    return str(slist[0])+' '+str(slist[-1])