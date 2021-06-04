package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * commentsテーブルを操作するリポジトリです.
 * 
 * @author masaki.taguchi
 *
 */
@Repository
public class CommentRepository {

	@Autowired
	NamedParameterJdbcTemplate template;

	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = new BeanPropertyRowMapper<>();

	/**
	 * 記事に付随するコメントを取得する.<br>
	 * コメントは複数件となる可能性あり。
	 * 
	 * @param articleId 記事ID
	 * @return コメント一覧
	 */
	public List<Comment> findByArticleId(int articleId) {
		String sql = "SELECT id, name, content, article_id FROM comments WHERE article_id = :articleId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);

		return commentList;
	}

	/**
	 * コメントを追加する.
	 * 
	 * @param comment コメント
	 */
	public void insert(Comment comment) {
		String sql = "INSERT INTO comments(name, content, article_id) VALUES (:name, :content, :articleId)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", comment.getName())
				.addValue("content", comment.getContent()).addValue("articleId", comment.getArticleId());
		template.update(sql, param);
	}

	/**
	 * 記事に付随するコメントを一括削除する.
	 * 
	 * @param articleId 記事ID
	 */
	public void deleteByArtcleId(int articleId) {
		String sql = "DELETE FROM comments WHERE article_id = :articleId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(sql, param);
	}
}
