create database m2i_ecf_mai;
use m2i_ecf mai;

create table customer
(
    id        int auto_increment
        primary key,
    email     varchar(255) null,
    firstname varchar(255) null,
    lastname  varchar(255) null,
    constraint UK_3qgg01qojcmbdp47dkaom9x45
        unique (email)
);

create table invoice
(
    id          int auto_increment
        primary key,
    created_at  timestamp null,
    status      int       null,
    customer_id int       null,
    constraint FKky1nwqx5xt33g54s1in3el5m1
        foreign key (customer_id) references customer (id)
);

create table product
(
    id          int auto_increment
        primary key,
    category    int          null,
    description varchar(255) null,
    price       double       not null,
    quantity    int          not null,
    size        int          null
);

create table invoice_product
(
    id         int auto_increment
        primary key,
    quantity   int not null,
    invoice_id int null,
    product_id int null,
    constraint FK3cg6pjuwmm3neaknduri4cph9
        foreign key (product_id) references product (id),
    constraint FKc629fy5x39y55sguo4mgjp7vb
        foreign key (invoice_id) references invoice (id)
);
INSERT INTO m2i_ecf_mai.customer (id, email, firstname, lastname) VALUES (1, 'jdupont@mail.fr', 'Jean', 'Dupont');
INSERT INTO m2i_ecf_mai.customer (id, email, firstname, lastname) VALUES (2, 'jabitbol@mail.com', 'Georges', 'Abitbol');
INSERT INTO m2i_ecf_mai.customer (id, email, firstname, lastname) VALUES (3, 'jbe.zorg@mail.zog', 'Jean-Baptiste Emmanuel', 'Zorg');

INSERT INTO m2i_ecf_mai.product (id, category, description, price, quantity, size) VALUES (1, 0, 'Jean bleu', 20, 47, 2);
INSERT INTO m2i_ecf_mai.product (id, category, description, price, quantity, size) VALUES (2, 1, 'Doudoune verte', 25, 20, 3);
INSERT INTO m2i_ecf_mai.product (id, category, description, price, quantity, size) VALUES (3, 2, 'T-shirt jaune', 10, 22, 1);
INSERT INTO m2i_ecf_mai.product (id, category, description, price, quantity, size) VALUES (4, 1, 'Slip kangourou', 6.99, 20, 4);
INSERT INTO m2i_ecf_mai.product (id, category, description, price, quantity, size) VALUES (5, 2, 'Manteau blanc', 50, 19, 3);
INSERT INTO m2i_ecf_mai.product (id, category, description, price, quantity, size) VALUES (6, 0, 'Jean jaune', 12, 6, 3);

INSERT INTO m2i_ecf_mai.invoice_product (id, quantity, invoice_id, product_id) VALUES (1, 2, 1, 4);
INSERT INTO m2i_ecf_mai.invoice_product (id, quantity, invoice_id, product_id) VALUES (2, 1, 1, 2);
INSERT INTO m2i_ecf_mai.invoice_product (id, quantity, invoice_id, product_id) VALUES (3, 2, 2, 6);
INSERT INTO m2i_ecf_mai.invoice_product (id, quantity, invoice_id, product_id) VALUES (4, 1, 2, 1);
INSERT INTO m2i_ecf_mai.invoice_product (id, quantity, invoice_id, product_id) VALUES (5, 2, 2, 3);
INSERT INTO m2i_ecf_mai.invoice_product (id, quantity, invoice_id, product_id) VALUES (6, 2, 3, 2);
INSERT INTO m2i_ecf_mai.invoice_product (id, quantity, invoice_id, product_id) VALUES (7, 5, 3, 4);
INSERT INTO m2i_ecf_mai.invoice_product (id, quantity, invoice_id, product_id) VALUES (8, 1, 3, 1);
INSERT INTO m2i_ecf_mai.invoice_product (id, quantity, invoice_id, product_id) VALUES (9, 1, 4, 3);
INSERT INTO m2i_ecf_mai.invoice_product (id, quantity, invoice_id, product_id) VALUES (10, 1, 4, 5);
INSERT INTO m2i_ecf_mai.invoice_product (id, quantity, invoice_id, product_id) VALUES (11, 2, 4, 1);
INSERT INTO m2i_ecf_mai.invoice_product (id, quantity, invoice_id, product_id) VALUES (12, 2, 4, 6);

INSERT INTO m2i_ecf_mai.invoice (id, created_at, status, customer_id) VALUES (1, '2024-05-31 09:01:08', 2, 3);
INSERT INTO m2i_ecf_mai.invoice (id, created_at, status, customer_id) VALUES (2, '2024-05-31 09:26:06', 1, 2);
INSERT INTO m2i_ecf_mai.invoice (id, created_at, status, customer_id) VALUES (3, '2024-05-31 10:31:35', 2, 1);
INSERT INTO m2i_ecf_mai.invoice (id, created_at, status, customer_id) VALUES (4, '2024-05-31 10:52:07', 0, 2);

