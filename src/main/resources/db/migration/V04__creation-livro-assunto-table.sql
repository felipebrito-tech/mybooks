CREATE TABLE livro_assunto
(
    livro_codl integer NOT NULL,
    assuntos_cod_as integer NOT NULL,
    CONSTRAINT fk1t5v0b8bhrpfc0p5tqni0jayb FOREIGN KEY (assuntos_cod_as)
        REFERENCES public.assunto (cod_as) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkorwqhfh2o2kof6yohjkpgnq1u FOREIGN KEY (livro_codl)
        REFERENCES public.livro (codl) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)