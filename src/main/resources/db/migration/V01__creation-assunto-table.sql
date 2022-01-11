CREATE TABLE assunto
(
    cod_as serial NOT NULL,
    descricao character varying(20),
    CONSTRAINT assunto_pkey PRIMARY KEY (cod_as)
);