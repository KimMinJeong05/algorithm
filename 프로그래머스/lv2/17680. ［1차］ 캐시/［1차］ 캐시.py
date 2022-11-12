def solution(cacheSize, cities):
    time=0
    cache=[]
    for c in cities:
        city=c.lower()
        if city in cache:
            time+=1
            del cache[cache.index(city)]
        else:
            time+=5
            if len(cache) >= cacheSize and not len(cache)==0:
                del cache[0]
        if not cacheSize==0:
            cache.append(city)
    return time