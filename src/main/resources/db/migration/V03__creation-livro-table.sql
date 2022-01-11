CREATE TABLE livro
(
    codl serial NOT NULL,
    ano_publicacao character varying(4),
    edicao integer,
    editora character varying(40),
    titulo character varying(40),
    CONSTRAINT livro_pkey PRIMARY KEY (codl)
);
