create table if not exists user_table
(
    id            serial
        constraint user_table_pk
            primary key,
    first_name    varchar default 50,
    last_name     varchar default 50,
    username      varchar default 50,
    password      varchar default 50,
    national_code varchar default 11,
    phone         varchar default 12
);
------------------------------------
create table if not exists cart_shopping
(
    id serial not null unique,
    user_id integer not null constraint id references user_table,
    product varchar default 50 not null,
    price bigint not null
)
-------------------------------------