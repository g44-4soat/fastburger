-- public.orders definition

-- Drop table

-- DROP TABLE public.orders;

CREATE TABLE public.orders
(
    id                 bigserial    NOT NULL,
    date_time_creation timestamp(6) NULL,
    order_number       varchar(255) NULL,
    status             varchar(255) NULL,
    total_value        float8       NULL,
    was_paid           bool         NULL,
    client_id          int8         NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT orders_status_check CHECK (((status)::text = ANY
                                           (ARRAY [('RECEIVED'::character varying)::text, ('INPREPARATION'::character varying)::text, ('READY'::character varying)::text, ('FINISHED'::character varying)::text])))
);
