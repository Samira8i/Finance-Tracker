INSERT INTO category (id, name, category_type, color) VALUES
( gen_random_uuid(),'Вклад', 'INCOME', '#FFF8A5'),
(gen_random_uuid(),'Бизнес', 'INCOME', '#A7BDEE'),
(gen_random_uuid(),'Зарплата', 'INCOME', '#D6A7EE'),
(gen_random_uuid(),'Аренда', 'INCOME', '#81E472'),
(gen_random_uuid(),'Крипта', 'INCOME', '#EEA7A8'),
(gen_random_uuid(),'Другое', 'INCOME', '#4C5BCF'),
( gen_random_uuid(),'Дом', 'EXPENSE', '#E2A7EE'),
(gen_random_uuid(),'Хобби', 'EXPENSE', '#B3EEA7'),
(gen_random_uuid(),'Продукты', 'EXPENSE', '#EEA7A7'),
(gen_random_uuid(),'Кафе', 'EXPENSE', '#EEC6A7'),
(gen_random_uuid(),'Здоровье', 'EXPENSE', '#A7ECEE'),
('00000000-0000-0000-0000-000000000002','Другое ', 'EXPENSE', '#EEEDA7')
on conflict (name) do nothing;

INSERT INTO users (id, username, password) VALUES
('00000000-0000-0000-0000-000000000001', 'common-user', 'defaultpass')
on conflict (id) do nothing;
