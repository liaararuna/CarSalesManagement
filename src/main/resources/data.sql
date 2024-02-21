INSERT INTO brand (id, name) VALUES (1, 'Mercedes');
INSERT INTO brand (id, name) VALUES (2, 'Audi');
INSERT INTO brand (id, name) VALUES (3, 'Ford');
INSERT INTO brand (id, name) VALUES (4, 'Fiat');
INSERT INTO brand (id, name) VALUES (5, 'Toyota');
INSERT INTO brand (id, name) VALUES (6, 'BMW');
INSERT INTO brand (id, name) VALUES (7, 'Volkswagen');
INSERT INTO brand (id, name) VALUES (8, 'Chevrolet');
INSERT INTO brand (id, name) VALUES (9, 'Honda');
INSERT INTO brand (id, name) VALUES (10, 'Nissan');

INSERT INTO model (id, name, brand_id) VALUES (1, 'GLA180', 1);
INSERT INTO model (id, name, brand_id) VALUES (2, 'A8', 2);
INSERT INTO model (id, name, brand_id) VALUES (3, 'Fiesta', 3);
INSERT INTO model (id, name, brand_id) VALUES (4, 'Palio', 4);
INSERT INTO model (id, name, brand_id) VALUES (5, 'Palio', 4);
INSERT INTO model (id, name, brand_id) VALUES (6, 'C-Class', 1);
INSERT INTO model (id, name, brand_id) VALUES (7, 'A6', 2);
INSERT INTO model (id, name, brand_id) VALUES (8, 'Focus', 3);
INSERT INTO model (id, name, brand_id) VALUES (9, '500', 4);
INSERT INTO model (id, name, brand_id) VALUES (10, 'Camry', 5);
INSERT INTO model (id, name, brand_id) VALUES (11, 'X3', 6);
INSERT INTO model (id, name, brand_id) VALUES (12, 'Golf', 7);
INSERT INTO model (id, name, brand_id) VALUES (13, 'Cruze', 8);
INSERT INTO model (id, name, brand_id) VALUES (14, 'Civic', 9);
INSERT INTO model (id, name, brand_id) VALUES (15, 'Altima', 10);



INSERT INTO seller (id, nif, name, address, phone_number) VALUES (1, '123456789', 'João', 'Rua A, 10', '999 999 999');
INSERT INTO seller (id, nif, name, address, phone_number) VALUES (2, '987654321', 'Maria', 'Rua B, 10', '999 111 111');
INSERT INTO seller (id, nif, name, address, phone_number) VALUES (3, '111111111', 'Paula', 'Rua C, 10', '999 222 222');
INSERT INTO seller (id, nif, name, address, phone_number) VALUES (4, '222333444', 'Lucas', 'Rua D, 10', '999 333 333');
INSERT INTO seller (id, nif, name, address, phone_number) VALUES (5, '555444333', 'Ana', 'Rua E, 10', '999 444 444');
INSERT INTO seller (id, nif, name, address, phone_number) VALUES (6, '999888777', 'Rafael', 'Rua F, 10', '999 555 555');
INSERT INTO seller (id, nif, name, address, phone_number) VALUES (7, '777888999', 'Marta', 'Rua G, 10', '999 666 666');
INSERT INTO seller (id, nif, name, address, phone_number) VALUES (8, '666555444', 'Pedro', 'Rua H, 10', '999 777 777');
INSERT INTO seller (id, nif, name, address, phone_number) VALUES (9, '111222333', 'Fernanda', 'Rua I, 10', '999 888 888');
INSERT INTO seller (id, nif, name, address, phone_number) VALUES (10, '444555666', 'Carlos', 'Rua J, 10', '999 999 000');
INSERT INTO seller (id, nif, name, address, phone_number) VALUES (11, '333222111', 'Larissa', 'Rua K, 10', '999 000 111');
INSERT INTO seller (id, nif, name, address, phone_number) VALUES (12, '999000111', 'Roberto', 'Rua L, 10', '999 000 222');
INSERT INTO seller (id, nif, name, address, phone_number) VALUES (13, '888999000', 'Isabel', 'Rua M, 10', '999 000 333');


INSERT INTO car (car_status, id_transaction, model_id, number_of_doors, release_year, seller_id, vin, color, fuel_type, license_plate)
    VALUES ('INSTOCK', 1, 1, 5, 2016, 1, 123456, 'Gray', 'Diesel', 'AR-91-QA');
INSERT INTO car (car_status, id_transaction, model_id, number_of_doors, release_year, seller_id, vin, color, fuel_type, license_plate)
    VALUES ('SOLD', 2, 2, 3, 2012, 2, 654321, 'Red', 'Gas', 'PB-90-QA');
INSERT INTO car (car_status, id_transaction, model_id, number_of_doors, release_year, seller_id, vin, color, fuel_type, license_plate)
    VALUES ('INSTOCK', 3, 3, 4, 2019, 3, 789012, 'Blue', 'Gasoline', 'SP-45-QB');
INSERT INTO car (car_status, id_transaction, model_id, number_of_doors, release_year, seller_id, vin, color, fuel_type, license_plate)
    VALUES ('SOLD', 4, 4, 5, 2015, 1, 345678, 'White', 'Ethanol', 'MG-67-QC');
INSERT INTO car (car_status, id_transaction, model_id, number_of_doors, release_year, seller_id, vin, color, fuel_type, license_plate)
    VALUES ('OUTOFSTOCK', 5, 5, 4, 2017, 2, 901234, 'Black', 'Gas', 'RJ-89-QD');
INSERT INTO car (car_status, id_transaction, model_id, number_of_doors, release_year, seller_id, vin, color, fuel_type, license_plate)
    VALUES ('INSPECTION', 6, 6, 3, 2014, 3, 567890, 'Silver', 'Diesel', 'BA-12-QE');
INSERT INTO car (car_status, id_transaction, model_id, number_of_doors, release_year, seller_id, vin, color, fuel_type, license_plate)
    VALUES ('SOLD', 7, 7, 5, 2018, 1, 123987, 'Green', 'Gasoline', 'CE-34-QF');
INSERT INTO car (car_status, id_transaction, model_id, number_of_doors, release_year, seller_id, vin, color, fuel_type, license_plate)
    VALUES ('RESERVED', 8, 8, 4, 2016, 2, 789345, 'Yellow', 'Ethanol', 'SC-56-QG');
INSERT INTO car (car_status, id_transaction, model_id, number_of_doors, release_year, seller_id, vin, color, fuel_type, license_plate)
    VALUES ('INSPECTION', 9, 9, 3, 2013, 3, 234567, 'Orange', 'Gas', 'AL-78-QH');
INSERT INTO car (car_status, id_transaction, model_id, number_of_doors, release_year, seller_id, vin, color, fuel_type, license_plate)
    VALUES ('SOLD', 10, 10, 5, 2015, 1, 890123, 'Purple', 'Diesel', 'PI-90-QI');

