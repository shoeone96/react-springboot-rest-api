CREATE
DATABASE IF NOT EXISTS coffee;

USE coffee;

CREATE TABLE customers
(
    customer_id   bigint auto_increment,
    customer_name VARCHAR(20),
    username      varchar(50),
    password      varchar(100),
    phone_number  varchar(20),
    created_at    datetime(6),
    updated_at    datetime(6),
    CONSTRAINT customer_pk PRIMARY KEY (customer_id)
);

CREATE TABLE vouchers
(
    voucher_id     bigint auto_increment,
    discount_value int,
    voucher_name   varchar(50),
    voucher_type   VARCHAR(20),
    voucher_valid  VARCHAR(20),
    customer_id    bigint,
    created_at     datetime(6),
    updated_at     datetime(6),
    CONSTRAINT voucher_pk PRIMARY KEY (voucher_id),
    CONSTRAINT voucher_fk FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
);

create table orders
(
    order_id    bigint auto_increment,
    voucher_id  bigint,
    total_price int,
    created_at  datetime(6),
    updated_at  datetime(6),
    CONSTRAINT orders_pk PRIMARY KEY (order_id),
    CONSTRAINT orders_fk FOREIGN KEY (voucher_id) REFERENCES vouchers (voucher_id)

);

CREATE TABLE products
(
    product_id   bigint AUTO_INCREMENT,
    product_name varchar(20),
    category     varchar(20),
    price        bigint,
    description  varchar(100),
    product_img  mediumblob,
    created_at   datetime(6),
    updated_at   datetime(6),
    CONSTRAINT products_pk PRIMARY KEY (product_id)
);

create table order_items
(
    order_item_id bigint AUTO_INCREMENT,
    product_id    bigint,
    order_id      bigint,
    price         bigint,
    quantity      bigint,
    created_at    datetime(6),
    updated_at    datetime(6),
    constraint order_items_pk PRIMARY KEY (order_item_id),
    constraint order_items_product_fk foreign key (product_id) references products (product_id),
    constraint order_items_order_fk foreign key (order_id) references orders (order_id)
);


CREATE
DATABASE IF NOT EXISTS coffee_test;

USE coffee_test;

CREATE TABLE customers
(
    customer_id   bigint auto_increment,
    customer_name VARCHAR(20),
    username      varchar(50),
    password      varchar(100),
    phone_number  varchar(20),
    created_at    datetime(6),
    updated_at    datetime(6),
    CONSTRAINT customer_pk PRIMARY KEY (customer_id)
);

CREATE TABLE vouchers
(
    voucher_id     bigint auto_increment,
    discount_value int,
    voucher_name   varchar(50),
    voucher_type   VARCHAR(20),
    voucher_valid  VARCHAR(20),
    customer_id    bigint,
    created_at     datetime(6),
    updated_at     datetime(6),
    CONSTRAINT voucher_pk PRIMARY KEY (voucher_id),
    CONSTRAINT voucher_fk FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
);

create table orders
(
    order_id    bigint auto_increment,
    voucher_id  bigint,
    total_price int,
    created_at  datetime(6),
    updated_at  datetime(6),
    CONSTRAINT orders_pk PRIMARY KEY (order_id),
    CONSTRAINT orders_fk FOREIGN KEY (voucher_id) REFERENCES vouchers (voucher_id)

);

CREATE TABLE products
(
    product_id   bigint AUTO_INCREMENT,
    product_name varchar(20),
    category     varchar(20),
    price        bigint,
    description  varchar(100),
    product_img  mediumblob,
    created_at   datetime(6),
    updated_at   datetime(6),
    CONSTRAINT products_pk PRIMARY KEY (product_id)
);

create table order_items
(
    order_item_id bigint AUTO_INCREMENT,
    product_id    bigint,
    order_id      bigint,
    price         bigint,
    quantity      bigint,
    created_at    datetime(6),
    updated_at    datetime(6),
    constraint order_items_pk PRIMARY KEY (order_item_id),
    constraint order_items_product_fk foreign key (product_id) references products (product_id),
    constraint order_items_order_fk foreign key (order_id) references orders (order_id)
);
