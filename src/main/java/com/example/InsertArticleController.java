package com.example;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

/**
 * 記事を投稿するコントローラ.
 *
 * @author shigeki.morishita
 *
 */
@Transactional
@Controller
@RequestMapping("/bbs")
public class InsertArticleController {
	@Autowired
	private ArticleRepository articleRepository;

	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}

	@RequestMapping("")
	public String index(Model model) {
		return "index";
	}

	/**
	 * 記事を投稿する.
	 *
	 * @return 投稿記事をフォワード
	 */
	@RequestMapping("/add-article")
	public String addArticle(ArticleForm form, Model model) {

		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);

		return "redirect:/bbs";
	}
}
