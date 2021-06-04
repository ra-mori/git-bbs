package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 掲示板の記事とコメント一覧を表示するコントローラ.
 * 
 * @author masaki.taguchi
 *
 */
@Controller
@Transactional
@RequestMapping("")
public class ShowBbsController {
	@Autowired
	public ArticleRepository articleRepository;

	@Autowired
	public CommentRepository commentRepository;

	/**
	 * 記事とコメント一覧を表示する.
	 * 
	 * @param model リクエストスコープ
	 * @return 記事とコメント一覧のビュー
	 */
	@RequestMapping("/")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		for (Article article : articleList) {
			article.setCommentList(commentRepository.findByArticleId(article.getId()));
		}

		model.addAttribute("articleList", articleList);
		return "index";
	}

}
