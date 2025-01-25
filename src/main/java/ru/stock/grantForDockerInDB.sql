-- GRANT SELECT ON ALL TABLES IN SCHEMA public TO second_postgres;
-- GRANT SELECT ON TABLE public.category TO second_postgres;
-- GRANT SELECT ON TABLE public.product TO second_postgres;

GRANT CONNECT ON DATABASE stock_db TO second_postgres;
-- GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE category TO second_postgres;
GRANT USAGE ON SCHEMA public TO second_postgres;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO second_postgres;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO second_postgres;