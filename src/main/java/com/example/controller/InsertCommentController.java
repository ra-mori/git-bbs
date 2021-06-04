package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Comment;
import com.example.form.CommentForm;
import com.example.repository.CommentRepository;

/**
 * コメントを挿入するコントローラ.
 * 
 * @author masaki.taguchi
 *
 */
@Controller
@Transactional
public class InsertCommentController {
	@Autowired
	CommentRepository repository;

	/**
	 * コメントを挿入する.
	 * 
	 * @param form  コメントフォーム
	 * @param model リクエストスコープ(一覧表示のメソッドを呼び出す用)
	 * @return 記事とコメント一覧のビュー
	 */
	@RequestMapping("/insert-comment")
	public String insertComment(CommentForm form, Model model) {
		ShowBbsController showBbsController = new ShowBbsController();
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(Integer.parseInt(form.getArticleId()));

		repository.insert(comment);

		return showBbsController.index(model);
	}
}
