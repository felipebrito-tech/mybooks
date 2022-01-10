CREATE SEQUENCE livro_codl_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
	
CREATE TABLE livro
(
    codl integer NOT NULL DEFAULT nextval('livro_codl_seq'::regclass),
    ano_publicacao character varying(4),
    edicao integer,
    editora character varying(40),
    titulo character varying(40),
    CONSTRAINT livro_pkey PRIMARY KEY (codl)
);

ALTER SEQUENCE livro_codl_seq
    OWNED BY livro.codl;