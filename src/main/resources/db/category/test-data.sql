merge into categories(id, name) values(1, 'Transport');
merge into categories(id, name) values(2, 'Food');
merge into categories(id, name) values(3, 'Medicine');
merge into categories(id, name) values(4, 'Goods');
merge into categories(id, name) values(5, 'Living');

merge into users_categories(id, user_id, category_id) values(1, 1, 1);
merge into users_categories(id, user_id, category_id) values(2, 1, 2);
merge into users_categories(id, user_id, category_id) values(3, 1, 3);
merge into users_categories(id, user_id, category_id) values(4, 1, 4);
merge into users_categories(id, user_id, category_id) values(5, 1, 5);