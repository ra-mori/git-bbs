package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

@Controller
@RequestMapping("/bbs")
public class DeleteArticleController {
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
}
