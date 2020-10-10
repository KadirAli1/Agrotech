------------------
-- User Role
------------------
insert into user_role values(1, "ROLE_ADMIN");
insert into user_role values(2, "ROLE_USER");

------------------
-- Product Type
------------------
insert into product_type values(1, "Tractor", '{"No of cylinder":"No of cylinder","Capacity CC":"Capacity CC","Transmission Type":"Transmission Type","Brake Type":"Brake Type","Steering Type":"Steering Type","Fuel Tank Capacity LIT":"Fuel Tank Capacity LIT"}');
insert into product_type values(2, "Motocultivator", '{"Motor":"Motor","Cylinder":"Cylinder","Working width":"Working width","Gears":"Gears","Wheels":"Wheels","Weight":"Weight"}');

------------------
-- Country
------------------
insert into country values(1, "Macedonia");
insert into country values(2, "Albania");
insert into country values(3, "Kosova");

------------------
-- User
------------------
-- username: test
-- password: admin
insert into user(id, email, name, password, surname, username, role_id) values (1, "test@hotmail.com", "test", "$2a$16$xh2ALPPKdVaTxfJs2Ex1rekvY4g3yeJczVH6CR23XW5wEEQVK3yL6", "doe", "john", 1);

------------------
-- Product
------------------

-- Tractor
insert into product(id, date, image_name, name, photo_data, price, specs, country_id, product_type_id) values (1, "2018-08-15 14:15:11", "arjun_605.jpg", "ARJUN NOVO 605 DI-PS", "", "55000", '{"No of cylinder":"4","Capacity CC":"3531","Transmission Type":"Mechanical, Synchromesh","Brake Type":"Mechanical, oil immersed multi disc brakes","Steering Type":"Power Steering","Fuel Tank Capacity LIT":"66"}', 2, 1);
insert into product(id, date, image_name, name, photo_data, price, specs, country_id, product_type_id) values (2, "2018-08-24 09:34:10", "arjun_international.jpg", "ARJUN INTERNATIONAL", "PHOTO_DATA", "75000", '{"No of cylinder":"4","Capacity CC":"3329","Transmission Type":"Full Synchromesh","Brake Type":"Oil immersed brakes","Steering Type":"Power Steering","Fuel Tank Capacity LIT":"60"}', 3, 1);
insert into product(id, date, image_name, name, photo_data, price, specs, country_id, product_type_id) values (3, "2018-10-22 10:34:15", "mahindra_555.jpg", "MAHINDRA 555 DI POWER PLUS", "PHOTO_DATA", "52500", '{"No of cylinder":"4","Capacity CC":"3532","Transmission Type":"Full constant Mesh (Optional)","Brake Type":"Oil Immersed Multi Disc Brakes","Steering Type":"Double acting Power steering","Fuel Tank Capacity LIT":"69"}', 2, 1);
insert into product(id, date, image_name, name, photo_data, price, specs, country_id, product_type_id) values (4, "2018-12-24 22:34:22", "mahindra_265.jpg", "MAHINDRA 265 DI", "PHOTO_DATA", "27500", '{"No of cylinder":"3","Capacity CC":"2048","Transmission Type":"Partial Constant Mesh (Optional)","Brake Type":"Oil Brakes","Steering Type":"Power steering (optional)","Fuel Tank Capacity LIT":"45"}', 1, 1);

-- Motocultivator
insert into product(id, date, image_name, name, photo_data, price, specs, country_id, product_type_id) values (5, "2018-12-25 08:08:15", "Bertolini_400_E.jpg", "Bertolini 400 E", "PHOTO_DATA", "2500", '{"Motor":"Emak K 700H OHV","Cylinder":"182 cm³","Working width":"50 cm","Gears":"1 forwards + 1 reverse","Wheels":"4.00-8”","Weight":"75 kg"}', 1, 2);
insert into product(id, date, image_name, name, photo_data, price, specs, country_id, product_type_id) values (6, "2018-12-26 22:22:22", "Benassi_MC2300_H.jpg", "Benassi MC2300 H", "PHOTO_DATA", "3200", '{"Motor":"Honda GX160","Cylinder":"163 cm³","Working width":"50 cm","Gears":"1 forwards + 1 reverse","Wheels":"3.50-8”","Weight":"71 kg"}', 1, 2);
insert into product(id, date, image_name, name, photo_data, price, specs, country_id, product_type_id) values (7, "2019-01-01 11:34:15", "Bertolini_401S_H.jpg", "Bertolini 401S H", "PHOTO_DATA", "4100", '{"Motor":"Honda GX160 OHV","Cylinder":"163 cm³","Working width":"50 cm","Gears":"1 forwards + 1 reverse","Wheels":"4.00-8”","Weight":"75 kg"}', 2, 2);
insert into product(id, date, image_name, name, photo_data, price, specs, country_id, product_type_id) values (8, "2019-01-01 17:34:22", "Bertolini_407S_H_BM.jpg", "Bertolini 407S H BM", "PHOTO_DATA", "2100", '{"Motor":"Honda GX200 OHV","Cylinder":"196 cm³","Working width":"60 cm","Gears":"2 forwards + 2 reverse","Wheels":"4.00-10”","Weight":"120 kg"}', 3, 2);