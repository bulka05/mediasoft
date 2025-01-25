CREATE TABLE IF NOT EXISTS public.product
(
    id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    vendor_code character varying(500) NOT NULL,
    category_id smallint NOT NULL,
	price DECIMAL(8,2),
	description  character varying(500),
    last_quantity_time TIMESTAMP,
    date_of_creation TIMESTAMP,
    recording_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT product_pkey PRIMARY KEY (id)
)
