CREATE SEQUENCE autor_cod_au_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
	
CREATE TABLE autor
(
    cod_au integer NOT NULL DEFAULT nextval('autor_cod_au_seq'::regclass),
    nome character varying(40),
    CONSTRAINT autor_pkey PRIMARY KEY (cod_au)
);

ALTER SEQUENCE autor_cod_au_seq
    OWNED BY autor.cod_au;