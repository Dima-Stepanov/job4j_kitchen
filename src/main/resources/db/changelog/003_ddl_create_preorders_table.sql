--Таблица содержит заказ для кухни PREORDERS
CREATE TABLE preorders
(
    id        serial primary key,
    order_id  INT,
    status_id int references statuses (id),
    dish_id   INT
);