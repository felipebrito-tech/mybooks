CREATE SEQUENCE assunto_cod_as_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
	
CREATE TABLE assunto
(
    cod_as integer NOT NULL DEFAULT nextval('assunto_cod_as_seq'::regclass),
    descricao character varying(20),
    CONSTRAINT assunto_pkey PRIMARY KEY (cod_as)
);

ALTER SEQUENCE assunto_cod_as_seq
    OWNED BY assunto.cod_as;