CREATE VIEW livros_por_autor_report_data AS
SELECT 	ROW_NUMBER() OVER(ORDER BY au.cod_au) as id,
		au.cod_au,
		au.nome,
		li.codl,
		li.titulo,
		li.ano_publicacao,
		li.edicao,
		li.editora,
		(
			SELECT string_agg(ass.descricao, ' | ') AS assuntos
			FROM assunto ass
			INNER JOIN livro_assunto las ON las.assuntos_cod_as = ass.cod_as
			WHERE las.livro_codl = li.codl
		)
FROM autor au
		LEFT JOIN livro_autor lau ON lau.autores_cod_au = au.cod_au
		LEFT JOIN livro li ON li.codl = lau.livro_codl;
		
SELECT * FROM livros_por_autor_report_data;