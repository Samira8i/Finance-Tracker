INSERT INTO category (id, name, category_type, color) VALUES
( gen_random_uuid(),'Вклад', 'INCOME', '#0FFFFF'),
(gen_random_uuid(),'Бизнес', 'INCOME', '#F0FFFF'),
(gen_random_uuid(),'Зарплата', 'INCOME', '#FF0FFF'),
(gen_random_uuid(),'Аренда', 'INCOME', '#FFF0FF'),
(gen_random_uuid(),'Криптовалюта', 'INCOME', '#FFFF0F'),
(gen_random_uuid(),'Другие доходы', 'INCOME', '#FFFFF0'),
( gen_random_uuid(),'Дом', 'EXPENSE', '#F00000'),
(gen_random_uuid(),'Хобби', 'EXPENSE', '#0F0000'),
(gen_random_uuid(),'Продукты', 'EXPENSE', '#00F000'),
(gen_random_uuid(),'Кафе', 'EXPENSE', '#000F00'),
(gen_random_uuid(),'Здоровье', 'EXPENSE', '#0000F0'),
(gen_random_uuid(),'Другие расходы', 'EXPENSE', '#00000F')
on conflict (name) do nothing;
