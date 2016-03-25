-- create a couple of Oracle test tables with a join table to hande many-many country-language relationship

create table MAEW.COUNTRY (

COUNTRY_ID NUMBER(8,0),
COUNTRY VARCHAR2(100),
CAPITAL_CITY VARCHAR2(100),
POPULATION  NUMBER(9,0)

);

create table KADAI.LANGUAGE (

LANGUAGE_ID NUMBER(8,0),
LANGUAGE VARCHAR2(100),

);

create table MAEW.COUNTRY_LANGUAGE (

COUNTRY_ID NUMBER(8,0),
LANGUAGE_ID NUMBER(8,0)


);

create sequence COUNTRY_SEQ  
minvalue 1
start with 1
increment by 1
cache 20;

create sequence LANGUAGE_SEQ  
minvalue 1
start with 1
increment by 1
cache 20;



grant all on COUNTRY to KADAI;
grant all on LANGUAGE to MAEW;
grant all on COUNTRY_LANGUAGE to KADAI;

alter table COUNTRY 
add constraint COUNTRY_PK primary key (COUNTRY_ID);

alter table LANGUAGE 
add constraint LANGUAGE_PK primary key (LANGUAGE_ID);


-- run as owner
COMMENT ON TABLE COUNTRY IS 'Country table.'; 
COMMENT ON COLUMN COUNTRY.COUNTRY_ID IS 'Primary key';
COMMENT ON COLUMN COUNTRY.COUNTRY IS 'Name of country';
COMMENT ON COLUMN COUNTRY.CAPITAL_CITY IS 'Name of country''s capital';
COMMENT ON COLUMN COUNTRY.POPULATION IS 'Country population, in millions, per wikipedia';

COMMENT ON TABLE LANGUAGE IS 'Language table.'; 
COMMENT ON COLUMN LANGUAGE.LANGUAGE_ID IS 'Primary key';
COMMENT ON COLUMN LANGUAGE.LANGUAGE IS 'Name of language';



