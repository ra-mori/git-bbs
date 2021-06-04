package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

/**
 * 記事を削除するコントローラ.
 *
 * @author shigeki.morishita
 *
 */
@Controller
@RequestMapping("/bbs")
public class DeleteArticleController {
	@Autowired
	private ArticleRepository articleRepository;

	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}

	/**
	 * トップページへフォワード.
	 *
	 * @param model リクエストスコープ
	 * @return フォワード
	 */
	@RequestMapping("")
	public String index(Model model) {
		return "index";
	}

	/**
	 * 記事を消去するメソッド.
	 *
	 * @return リダイレクト
	 */
	public String deleteArticle(String id, Model model) {
		int articleId = Integer.parseInt(id);
		articleRepository.deleteById(articleId);

		return "redirect:/bbs";

	}
}
