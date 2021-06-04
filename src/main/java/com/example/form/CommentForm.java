package com.example.form;

/**
 * コメントを表すフォーム.
 * 
 * @author masaki.taguchi
 *
 */
public class CommentForm {
	/** 名前 */
	private String name;
	/** コメント */
	private String content;
	/** 記事ID */
	private String articleId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@Override
	public String toString() {
		return "CommentForm [name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}

}
