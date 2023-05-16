--Таблица содержит данные по количеству блюд которые может приготовить кухня.
CREATE TABLE WAREHOUSES
(
    id       SERIAL PRIMARY KEY,
    dish_id  int,
    dish_num int
);