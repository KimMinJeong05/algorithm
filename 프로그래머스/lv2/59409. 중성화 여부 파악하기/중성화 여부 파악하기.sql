-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME, if(SEX_UPON_INTAKE like '%Neutered%' or SEX_UPON_INTAKE like '%Spayed%', 'O', 'X') AS '중성화'
FROM ANIMAL_INS
ORDER BY ANIMAL_ID