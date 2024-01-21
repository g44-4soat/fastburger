-- public.orders foreign keys

ALTER TABLE public.orders
    ADD CONSTRAINT fkm2dep9derpoaehshbkkatam3v FOREIGN KEY (client_id) REFERENCES public.clients (id);