-- public.itens definition

-- Drop table

-- DROP TABLE public.itens;

CREATE TABLE public.itens
(
    id         bigserial      NOT NULL,
    product_id int8           NULL,
    quantity   int4           NULL,
    subtotal   numeric(38, 2) NULL,
    unit_price numeric(38, 2) NULL,
    CONSTRAINT itens_pkey PRIMARY KEY (id)
);