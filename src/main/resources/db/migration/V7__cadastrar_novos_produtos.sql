INSERT INTO public.products (sku, category_enum, description, "name", price) VALUES(1, 'BURGERS', 'Xtudo medio', 'Xtudo', 15.0);
INSERT INTO public.products (sku, category_enum, description, "name", price) VALUES(2, 'BURGERS', 'XSalada medio', 'XSalada', 10.0);
INSERT INTO public.products (sku, category_enum, description, "name", price) VALUES(3, 'SIDES', 'Batata Frita', 'Batata Frita', 8.0);
INSERT INTO public.products (sku, category_enum, description, "name", price) VALUES(4, 'SIDES', 'Mandioca Cozida', 'Mandioca Cozida', 5.0);
INSERT INTO public.products (sku, category_enum, description, "name", price) VALUES(5, 'DRINKS', 'cocacola 600', 'Cocacola', 5.0);
INSERT INTO public.products (sku, category_enum, description, "name", price) VALUES(6, 'DRINKS', 'cocacola 2L', 'Cocacola', 5.0);
INSERT INTO public.products (sku, category_enum, description, "name", price) VALUES(7, 'DESSERTS', 'Bolo de chocolate cremoso', 'Bolo de chocolate', 5.0);
INSERT INTO public.products (sku, category_enum, description, "name", price) VALUES(8, 'DESSERTS', 'Bolo de Cenoura cremoso', 'Bolo de Cenoura', 10.0);

INSERT INTO public.images (id, url) VALUES(1, 'https://img.freepik.com/fotos-gratis/hamburguer-saboroso-isolado-no-fundo-branco-fastfood-de-hamburguer-fresco-com-carne-e-queijo_90220-1063.jpg');
INSERT INTO public.images (id, url) VALUES(2, 'https://img.freepik.com/fotos-gratis/hamburguer-saboroso-isolado-no-fundo-branco-fastfood-de-hamburguer-fresco-com-carne-e-queijo_90220-1063.jpg');
INSERT INTO public.images (id, url) VALUES(3, 'https://e7.pngegg.com/pngimages/726/602/png-clipart-full-breakfast-side-dish-fast-food-mediterranean-cuisine-cuisine-of-the-united-states-junk-food.png');
INSERT INTO public.images (id, url) VALUES(4, 'https://e7.pngegg.com/pngimages/726/602/png-clipart-full-breakfast-side-dish-fast-food-mediterranean-cuisine-cuisine-of-the-united-states-junk-food.png');
INSERT INTO public.images (id, url) VALUES(5, 'https://assets.stickpng.com/thumbs/580b57fbd9996e24bc43c0de.png');
INSERT INTO public.images (id, url) VALUES(6, 'https://assets.stickpng.com/thumbs/580b57fbd9996e24bc43c0de.png');
INSERT INTO public.images (id, url) VALUES(7, 'https://w7.pngwing.com/pngs/630/327/png-transparent-dessert-cake-sprinkles-pastry-chocolate-cake-cream-white-food.png');
INSERT INTO public.images (id, url) VALUES(8, 'https://w7.pngwing.com/pngs/630/327/png-transparent-dessert-cake-sprinkles-pastry-chocolate-cake-cream-white-food.png');

INSERT INTO public.product_image (product_id, image_id) VALUES(1, 1);
INSERT INTO public.product_image (product_id, image_id) VALUES(2, 2);
INSERT INTO public.product_image (product_id, image_id) VALUES(3, 3);
INSERT INTO public.product_image (product_id, image_id) VALUES(4, 4);
INSERT INTO public.product_image (product_id, image_id) VALUES(5, 5);
INSERT INTO public.product_image (product_id, image_id) VALUES(6, 6);
INSERT INTO public.product_image (product_id, image_id) VALUES(7, 7);
INSERT INTO public.product_image (product_id, image_id) VALUES(8, 8);
