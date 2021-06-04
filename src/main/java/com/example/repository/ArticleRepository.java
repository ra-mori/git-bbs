package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * Articlesテーブルを操作するリポジトリ.
 *
 * @author shigeki.morishita
 *
 */
@Repository
public class ArticleRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	/** TABLE_NAME */
	private static final String TABLE_NAME = "articles";

	/** ARTICLEオブジェクトを生成するローマッパー */
	private final static RowMapper<Article> ARTICLE_ROW_MAPPER = new BeanPropertyRowMapper<>(Article.class);

	/**
	 * 記事の一覧検索を行う.
	 *
	 * @return List<article> 記事一覧
	 */
	public List<Article> findAll() {
		String sql = "SELECT id,name,content FROM " + TABLE_NAME + " ORDER BY id DESC;";

		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);

		return articleList;
	}

	/**
	 * 記事投稿を行う.
	 *
	 * @param article 記事の入力内容
	 */
	public void insert(Article article) {
		String sql = "INSERT INTO " + TABLE_NAME + " (name,content) VALUES (:name,:content);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		template.update(sql, param);
	}

	/**
	 * 投稿記事を消去する.
	 *
	 * @param id 投稿記事のid
	 */
	public void deleteById(int id) {
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
}
