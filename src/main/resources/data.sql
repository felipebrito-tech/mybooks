INSERT INTO assunto(descricao) VALUES ('Administração');
INSERT INTO assunto(descricao) VALUES ('Negócios');
INSERT INTO assunto(descricao) VALUES ('Economia');
INSERT INTO assunto(descricao) VALUES ('Teologia Cristã');
INSERT INTO assunto(descricao) VALUES ('Vida Cristã');

INSERT INTO autor (nome) VALUES ('Greg Mckeown');
INSERT INTO autor (nome) VALUES ('Charles Duhigg');
INSERT INTO autor (nome) VALUES ('C. S. Lewis');
INSERT INTO autor (nome) VALUES ('Timothy Keller');

INSERT INTO livro (titulo, editora, edicao, ano_publicacao) VALUES ('Essencialismo', 'Editora Sextante', 1, '2015');
INSERT INTO livro (titulo, editora, edicao, ano_publicacao) VALUES ('Sem esforço', 'Editora Sextante', 1, '2021');
INSERT INTO livro (titulo, editora, edicao, ano_publicacao) VALUES ('O poder do hábito', 'Objetiva', 1, '2012');
INSERT INTO livro (titulo, editora, edicao, ano_publicacao) VALUES ('Cristianismo puro e simples', 'Thomas Nelson Brasil', 1, '2017');
INSERT INTO livro (titulo, editora, edicao, ano_publicacao) VALUES ('Ego transformado', 'Vida Nova', 1, '2014');

INSERT INTO livro_autor (livro_codl, autores_cod_au) VALUES (1, 1);
INSERT INTO livro_autor (livro_codl, autores_cod_au) VALUES (2, 1);
INSERT INTO livro_autor (livro_codl, autores_cod_au) VALUES (3, 2);
INSERT INTO livro_autor (livro_codl, autores_cod_au) VALUES (4, 3);
INSERT INTO livro_autor (livro_codl, autores_cod_au) VALUES (5, 4);

INSERT INTO livro_assunto (livro_codl, assuntos_cod_as) VALUES (1, 1);
INSERT INTO livro_assunto (livro_codl, assuntos_cod_as) VALUES (1, 2);
INSERT INTO livro_assunto (livro_codl, assuntos_cod_as) VALUES (1, 3);

INSERT INTO livro_assunto (livro_codl, assuntos_cod_as) VALUES (2, 1);
INSERT INTO livro_assunto (livro_codl, assuntos_cod_as) VALUES (2, 2);
INSERT INTO livro_assunto (livro_codl, assuntos_cod_as) VALUES (2, 3);

INSERT INTO livro_assunto (livro_codl, assuntos_cod_as) VALUES (3, 1);
INSERT INTO livro_assunto (livro_codl, assuntos_cod_as) VALUES (3, 2);
INSERT INTO livro_assunto (livro_codl, assuntos_cod_as) VALUES (3, 3);

INSERT INTO livro_assunto (livro_codl, assuntos_cod_as) VALUES (4, 4);

INSERT INTO livro_assunto (livro_codl, assuntos_cod_as) VALUES (5, 5);