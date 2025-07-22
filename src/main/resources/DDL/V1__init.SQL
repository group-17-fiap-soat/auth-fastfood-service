-- DDL script to create the FastFood database schema
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


-- Tabela Customer
CREATE TABLE tb_customer
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    cpf        VARCHAR(11) UNIQUE NOT NULL,
    name       TEXT               NOT NULL,
    email      TEXT UNIQUE        NOT NULL,
    created_at TIMESTAMPTZ      DEFAULT now(),
    updated_at TIMESTAMPTZ      DEFAULT now()
);
