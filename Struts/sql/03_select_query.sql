-- Get main langauges of Laos
select LANGUAGE from 
KADAI.LANGUAGE a
join
MAEW.COUNTRY_LANGUAGE b
on
a.LANGUAGE_ID = b.LANGUAGE_ID
join
MAEW.COUNTRY c
on
b.COUNTRY_ID = c.COUNTRY_ID
where
c.COUNTRY = 'Laos';