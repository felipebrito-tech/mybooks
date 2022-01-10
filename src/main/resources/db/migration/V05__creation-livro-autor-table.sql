CREATE TABLE livro_autor
(
    livro_codl integer NOT NULL,
    autores_cod_au integer NOT NULL,
    CONSTRAINT fk89geljs5v6shh884tjw8tbqk3 FOREIGN KEY (livro_codl)
        REFERENCES public.livro (codl) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkchhjamf26ns1es77cbn95purv FOREIGN KEY (autores_cod_au)
        REFERENCES public.autor (cod_au) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)