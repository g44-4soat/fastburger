-- public.orders_product foreign keys

ALTER TABLE public.orders_product
    ADD CONSTRAINT fk5aidupgvh4ag9v5kand6ipgrm FOREIGN KEY (product_id) REFERENCES public.products (sku);
ALTER TABLE public.orders_product
    ADD CONSTRAINT fkobnf8ouugogkggs3xt88l35h4 FOREIGN KEY (order_id) REFERENCES public.orders (id);