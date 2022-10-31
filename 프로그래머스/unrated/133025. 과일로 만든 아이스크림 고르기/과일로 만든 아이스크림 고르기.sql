-- 코드를 입력하세요
SELECT info.flavor
from ICECREAM_INFO as info 
join FIRST_HALF as half on info.FLAVOR = half.FLAVOR
where half.total_order > 3000 and info.INGREDIENT_TYPE like '%fruit%'
order by half.total_order desc