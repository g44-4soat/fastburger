-- public.product_image definition

-- Drop table

-- DROP TABLE public.product_image;

ALTER TABLE public.product_image
    ADD CONSTRAINT fk1n91c4vdhw8pa4frngs4qbbvs FOREIGN KEY (product_id) REFERENCES public.products (sku);
ALTER TABLE public.product_image
    ADD CONSTRAINT fkanpsonqgb5uwbw83m3m7phnms FOREIGN KEY (image_id) REFERENCES public.images (id);