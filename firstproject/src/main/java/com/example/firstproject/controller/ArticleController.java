package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class ArticleController {
    @Autowired  // 리파지토리 구현객체 주입
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticle() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { // 폼 데이터를 DTO로 받기
        //System.out.println(form.toString()); // DTO에 폼 데이터가 잘 들어갔는지 확인
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환

        Article article = form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());

        // 2. 리파지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId(); // 리다이렉트를 작성할 위치
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        /**
         * 1. id를 조회해 데이터 가져오기
         * 2. 모델에 데이터 등록하기
         * 3. 뷰 페이지 반환하기
         */

        // id를 조회해 데이터 가져오기
        Article articleEntity =
                articleRepository.findById(id).orElse((null));

        // 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);  // article 이라는 이름으로 데이터 등록

        // 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        /**
         * 1. 모든 데이터 가져오기
         * 2. 모델에 데이터 등록하기
         * 3. 뷰 페이지 설정하기
         */

        // 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleList", articleEntityList);
        return "articles/index";
    }
}
