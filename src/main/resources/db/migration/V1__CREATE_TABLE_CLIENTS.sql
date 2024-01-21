-- public.clients definition

-- Drop table

-- DROP TABLE public.clients;

CREATE TABLE public.clients
(
    id    bigserial NOT NULL,
    cpf   varchar(255) NULL,
    email varchar(255) NULL,
    nome  varchar(255) NULL,
    CONSTRAINT clients_pkey PRIMARY KEY (id),
    CONSTRAINT uk_7it9dgecuhaofss241235vdcn UNIQUE (cpf),
    CONSTRAINT uk_srv16ica2c1csub334bxjjb59 UNIQUE (email)
);