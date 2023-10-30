CREATE TABLE public.images
(
    id  bigserial NOT NULL,
    url varchar(255) NULL,
    CONSTRAINT images_pkey PRIMARY KEY (id)
);