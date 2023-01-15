create table ACCOUNTS (
                          USER_NAME varchar(20) not null,
                          ACTIVE bit not null,
                          ENCRYTED_PASSWORD varchar(128) not null,
                          USER_ROLE varchar(20) not null
);
alter table ACCOUNTS
    add primary key (USER_NAME);
create table PRODUCTS (
                          CODE varchar(20) not null,
                          IMAGE blob,
                          NAME varchar(255) not null,
                          PRICE double not null,
                          CREATE_DATE timestamp not null
);
alter table PRODUCTS
    add primary key (CODE);
create table ORDERS (
                        ID varchar(50) not null,
                        AMOUNT double not null,
                        CUSTOMER_ADDRESS varchar(255) not null,
                        CUSTOMER_EMAIL varchar(128) not null,
                        CUSTOMER_NAME varchar(255) not null,
                        CUSTOMER_PHONE varchar(128) not null,
                        ORDER_DATE timestamp not null,
                        ORDER_NUM int not null
);
alter table ORDERS
    add primary key (ID);
alter table ORDERS
    add constraint ORDER_UK
        unique (ORDER_NUM);
create table ORDER_DETAILS (
                               ID varchar(50) not null,
                               AMOUNT double not null,
                               PRICE double not null,
                               QUANITY int not null,
                               ORDER_ID varchar(50) not null,
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
insert into Accounts (USER_NAME, ACTIVE, ENCRYTED_PASSWORD, USER_ROLE)
values (
           'user',
           1,
           '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',
           'ROLE_CLIENT'
       );
insert into Accounts (USER_NAME, ACTIVE, ENCRYTED_PASSWORD, USER_ROLE)
values (
           'admin',
           1,
           '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',
           'ROLE_ADMIN'
       );
insert into products (CODE, NAME, PRICE, CREATE_DATE)
values (
           'S001',
           'Core Java',
           100,
           sysdate
       );
insert into products (CODE, NAME, PRICE, CREATE_DATE)
values (
           'S002',
           'Spring for Beginners',
           50,
           sysdate
       );
insert into products (CODE, NAME, PRICE, CREATE_DATE)
values (
           'S003',
           'Swift for Beginners',
           120,
           sysdate
       );
insert into products (CODE, NAME, PRICE, CREATE_DATE)
values (
           'S004',
           'Oracle XML Parser',
           120,
           sysdate
       );
insert into products (CODE, NAME, PRICE, CREATE_DATE)
values (
           'S005',
           'CSharp Tutorial for Beginers',
           110,
           sysdate
       );