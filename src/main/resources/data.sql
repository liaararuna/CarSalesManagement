INSERT INTO brand (id, name) VALUES (1, 'Mercedes');
INSERT INTO brand (id, name) VALUES (2, 'Audi');
INSERT INTO brand (id, name) VALUES (3, 'Ford');

INSERT INTO model (id, name, brand_id) VALUES (1, 'GLA180', 1);
INSERT INTO model (id, name, brand_id) VALUES (2, 'A8', 2);
INSERT INTO model (id, name, brand_id) VALUES (3, 'Fiesta', 3);


INSERT INTO seller (id, nif, name, address, phone_number) VALUES (1, '123456789', 'João', 'Rua A, 10', '999 999 999');
INSERT INTO seller (id, nif, name, address, phone_number) VALUES (2, '987654321', 'Maria', 'Rua B, 10', '999 111 111');
INSERT INTO seller (id, nif, name, address, phone_number) VALUES (3, '111111111', 'Paula', 'Rua C, 10', '999 222 222');

INSERT INTO car (car_status, id_transaction, model_id, number_of_doors, release_year, seller_id, vin, color, fuel_type, license_plate)
    VALUES (1, 1, 1, 5, 2016, 1, 123456, 'Gray', 'Diesel', 'AR-91-QA');
INSERT INTO car (car_status, id_transaction, model_id, number_of_doors, release_year, seller_id, vin, color, fuel_type, license_plate)
    VALUES (2, 2, 2, 3, 2012, 2, 654321, 'Red', 'Gas', 'PB-90-QA');
