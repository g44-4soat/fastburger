CREATE TABLE public.products
(
    sku           bigserial NOT NULL,
    category_enum varchar(255) NULL,
    description   varchar(255) NULL,
    "name"        varchar(255) NULL,
    price         float8 NULL,
    CONSTRAINT products_category_enum_check CHECK (((category_enum)::text = ANY ((ARRAY['BURGERS':: character varying, 'SIDES':: character varying, 'DRINKS':: character varying, 'DESSERTS':: character varying])::text[])
) ),
	CONSTRAINT products_pkey PRIMARY KEY (sku)
);