create schema if not exists records;

create table records.feeling (
    feeling_id bigserial primary key,
    feeling TEXT not null
);

create table records.category (
    category_id bigserial primary key,
    category TEXT not null
);

create table records.record (
    record_id bigserial primary key,
    picture_url TEXT not null,
    created_at TIMESTAMP not null,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    feeling_id bigserial references records.feeling(feeling_id),
    category_id bigserial references records.category(category_id),
    description TEXT
);