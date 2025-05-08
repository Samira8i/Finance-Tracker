-- Вставка категорий доходов (INCOME)
INSERT INTO category (id, name, category_type, color)
SELECT gen_random_uuid(), 'Вклад', 'INCOME', '#0FFFFF'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Вклад' AND category_type = 'INCOME');

INSERT INTO category (id, name, category_type, color)
SELECT gen_random_uuid(), 'Бизнес', 'INCOME', '#F0FFFF'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Бизнес' AND category_type = 'INCOME');

INSERT INTO category (id, name, category_type, color)
SELECT gen_random_uuid(), 'Зарплата', 'INCOME', '#FF0FFF'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Зарплата' AND category_type = 'INCOME');

INSERT INTO category (id, name, category_type, color)
SELECT gen_random_uuid(), 'Аренда', 'INCOME', '#FFF0FF'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Аренда' AND category_type = 'INCOME');

INSERT INTO category (id, name, category_type, color)
SELECT gen_random_uuid(), 'Криптовалюта', 'INCOME', '#FFFF0F'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Криптовалюта' AND category_type = 'INCOME');

INSERT INTO category (id, name, category_type, color)
SELECT gen_random_uuid(), 'Другое', 'INCOME', '#FFFFF0'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Другое' AND category_type = 'INCOME');

-- Вставка категорий расходов (EXPENSE)
INSERT INTO category (id, name, category_type, color)
SELECT gen_random_uuid(), 'Дом', 'EXPENSE', '#F00000'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Дом' AND category_type = 'EXPENSE');

INSERT INTO category (id, name, category_type, color)
SELECT gen_random_uuid(), 'Хобби', 'EXPENSE', '#0F0000'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Хобби' AND category_type = 'EXPENSE');

INSERT INTO category (id, name, category_type, color)
SELECT gen_random_uuid(), 'Продукты', 'EXPENSE', '#00F000'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Продукты' AND category_type = 'EXPENSE');

INSERT INTO category (id, name, category_type, color)
SELECT gen_random_uuid(), 'Кафе', 'EXPENSE', '#000F00'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Кафе' AND category_type = 'EXPENSE');

INSERT INTO category (id, name, category_type, color)
SELECT gen_random_uuid(), 'Здоровье', 'EXPENSE', '#0000F0'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Здоровье' AND category_type = 'EXPENSE');

INSERT INTO category (id, name, category_type, color)
SELECT gen_random_uuid(), 'Другое', 'EXPENSE', '#00000F'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = 'Другое' AND category_type = 'EXPENSE');