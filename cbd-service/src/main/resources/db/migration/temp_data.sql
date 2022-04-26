INSERT INTO cbd_core_user (id, email, first_name, identification_number, last_name)
VALUES ('1', 'sonu@cbd.com', 'Sonu', '808829932V', 'Kings');
INSERT INTO cbd_core_service.cbd_core_user (id, email, first_name, identification_number, last_name)
VALUES ('2', 'anu@cbd.com', 'Anu', '901830556V', 'John');
INSERT INTO cbd_core_service.cbd_core_user (id, email, first_name, identification_number, last_name)
VALUES ('3', 'john@cbd.com', 'John', '348829932V', 'Sam');
INSERT INTO cbd_core_service.cbd_core_user (id, email, first_name, identification_number, last_name)
VALUES ('4', 'mary@cbd.com', 'Mary', '842829932V', 'Thomas');

INSERT INTO cbd_core_account
    (actual_balance, available_balance, `number`, status, `type`, user_id)
VALUES (100000.00, 100000.00, 100015003000, 'ACTIVE', 'SAVINGS_ACCOUNT', '1'),
       (100000.00, 100000.00, 100015003001, 'ACTIVE', 'SAVINGS_ACCOUNT', '1'),
       (100000.00, 100000.00, 100015003002, 'ACTIVE', 'SAVINGS_ACCOUNT', '2'),
       (12000.00, 12000.00, 100015003003, 'ACTIVE', 'SAVINGS_ACCOUNT', '2'),
       (12000.00, 12000.00, 100015003005, 'ACTIVE', 'SAVINGS_ACCOUNT', '3'),
       (290000.00, 290000.00, 100015003006, 'ACTIVE', 'SAVINGS_ACCOUNT', '3'),
       (365023.00, 365023.00, 100015003010, 'ACTIVE', 'SAVINGS_ACCOUNT', '4'),
       (365023.00, 89456.00, 100015003011, 'ACTIVE', 'SAVINGS_ACCOUNT', '4');

