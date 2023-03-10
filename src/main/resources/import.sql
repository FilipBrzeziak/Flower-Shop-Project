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
        'pomara??czowy',
        'kwiat',
        'Krokus',
        50,
        sysdate);
insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('S003',
        'zielony',
        'dodatek',
        'Wst????ka',
        120,
        sysdate);
insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('S004',
        'czerwony',
        'kwiat',
        'R????a',
        120,
        sysdate);
insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('S005',
        'bia??y',
        'dodatek',
        'Papier',
        110,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('rose',
        'czerwona',
        'kwiat',
        'R????a',
        11,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('rose',
        'czerwony',
        'kwiat',
        'R????a',
        11,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('tulip',
        'czerwony',
        'kwiat',
        'Tulipan',
        13,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('lily',
        'zielony',
        'kwiat',
        'Lilia',
        18,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('daisy',
        'bia??y',
        'kwiat',
        'Margaretka',
        21,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('sunflower',
        '??????ty',
        'kwiat',
        'S??onecznik',
        8,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('bunch',
        'brak',
        'kompozycja',
        'Wi??zanka',
        8,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('tied',
        'brak',
        'kompozycja',
        'Bukiet wi??zany',
        8,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('box',
        'brak',
        'kompozycja',
        'Box',
        8,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('rim',
        'brak',
        'kompozycja',
        'Wianek',
        8,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('basket',
        'brak',
        'kompozycja',
        'Kosz',
        5,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('bow',
        'r????owy',
        'dodatek',
        'Wst????ka',
        5,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('ribbon',
        'czerwony',
        'dodatek',
        'Kokarda',
        5,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('card',
        'brak',
        'dodatek',
        'Karteczka',
        5,
        sysdate);

insert into products (CODE,COLOUR,CATEGORY, NAME, PRICE, CREATE_DATE)
values ('heart',
        'czerwony',
        'dodatek',
        'Serce',
        5,
        sysdate);
