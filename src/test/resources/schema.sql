DROP TABLE IF EXISTS food;


create table food (food_id bigint not null,
 food_group varchar(255),
 food_name varchar(255),
 food_sub_group varchar(255),
 scientific_name varchar(255),
 primary key (food_id))
