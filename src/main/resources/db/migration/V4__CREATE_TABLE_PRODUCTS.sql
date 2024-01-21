-- public.products definition

-- Drop table

-- DROP TABLE public.products;

CREATE TABLE public.products
(
    sku           bigserial    NOT NULL,
    category_enum varchar(255) NULL,
    description   varchar(255) NULL,
    "name"        varchar(255) NULL,
    price         float8       NULL,
    CONSTRAINT products_category_enum_check CHECK (((category_enum)::text = ANY
                                                    (ARRAY [('BURGERS'::character varying)::text, ('SIDES'::character varying)::text, ('DRINKS'::character varying)::text, ('DESSERTS'::character varying)::text]))),
    CONSTRAINT products_pkey PRIMARY KEY (sku)
);