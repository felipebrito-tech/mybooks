CREATE TABLE autor
(
    cod_au serial NOT NULL,
    nome character varying(40),
    CONSTRAINT autor_pkey PRIMARY KEY (cod_au)
);
