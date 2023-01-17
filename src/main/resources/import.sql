create table ACCOUNTS
(
    USER_NAME          varchar(20)  not null,
    ACTIVE             bit          not null,
    ENCRYPTED_PASSWORD varchar(128) not null,
    EMAIL              varchar(128) not null,
    PHONE              varchar(128) not null,
    FIRST_NAME         varchar(128) not null,
    LAST_NAME          varchar(128) not null,
    USER_ADDRESS       varchar(128) not null,
    USER_CITY          varchar(128) not null,
    USER_ROLE          varchar(20)  not null
);
alter table ACCOUNTS
    add primary key (USER_NAME);
create table PRODUCTS
(
    CODE        varchar(20)  not null,
    COLOUR        varchar(20)  not null,
    CATEGORY        varchar(20)  not null,
    IMAGE       blob,
    NAME        varchar(255) not null,
    PRICE       double       not null,
    CREATE_DATE timestamp    not null
);
alter table PRODUCTS
    add primary key (CODE);
create table ORDERS
(
    ID               varchar(50)  not null,
    AMOUNT           double       not null,
    CUSTOMER_ADDRESS varchar(255) not null,
    CUSTOMER_EMAIL   varchar(128) not null,
    CUSTOMER_NAME    varchar(255) not null,
    CUSTOMER_PHONE   varchar(128) not null,
    ORDER_DATE       timestamp    not null,
    ORDER_NUM        int          not null
);
alter table ORDERS
    add primary key (ID);
alter table ORDERS
    add constraint ORDER_UK
        unique (ORDER_NUM);
create table ORDER_DETAILS
(
    ID         varchar(50) not null,
    AMOUNT     double      not null,
    PRICE      double      not null,
    QUANITY    int         not null,
    ORDER_ID   varchar(50) not null,
    PRODUCT_ID varchar(20) not null
);
alter table ORDER_DETAILS
    add primary key (ID);
alter table ORDER_DETAILS
    add constraint ORDER_DETAIL_ORD_FK
        foreign key (ORDER_ID)
            references ORDERS (ID);
alter table ORDER_DETAILS
    add constraint ORDER_DETAIL_PROD_FK
        foreign key (PRODUCT_ID)
            references PRODUCTS (CODE);
insert into Accounts (USER_NAME, ACTIVE, ENCRYPTED_PASSWORD, EMAIL, PHONE, FIRST_NAME, LAST_NAME,
                      USER_ADDRESS, USER_CITY, USER_ROLE)
values ('user',
        1,
        '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',
        'user@wp.pl',
        '+48789987789',
        'Maciej',
        'Konieczny',
        'Pruszkowa 7/14',
        '58-160 Koszyce',
        'ROLE_CLIENT');
insert into Accounts (USER_NAME, ACTIVE, ENCRYPTED_PASSWORD, EMAIL, PHONE, FIRST_NAME, LAST_NAME,
                      USER_ADDRESS, USER_CITY, USER_ROLE)
values ('admin',
        1,
        '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',
        'admin@wp.pl',
        '+48456455545',
        'Konrad',
        'Wallenrod',
        'Wiejska 1/2',
        '58-100 Warszawa',
        'ROLE_ADMIN');
insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('S001',
        'niebieski',
        'kwiat',
        'Niezapominajka',
        100,
        sysdate);
insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('S002',
        'pomarańczowy',
        'kwiat',
        'Krokus',
        50,
        sysdate);
insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('S003',
        'zielony',
        'dodatek',
        'Wstążka',
        120,
        sysdate);
insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('S004',
        'czerwony',
        'kwiat',
        'Róża',
        120,
        sysdate);
insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('S005',
        'biały',
        'dodatek',
        'Papier',
        110,
        sysdate);