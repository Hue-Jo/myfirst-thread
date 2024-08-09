package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }


    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }


    public Article create(ArticleForm articleDto) {

        Article article = articleDto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }


    public Article update(Long id, ArticleForm articleDto) {

        // 1. DTO -> 엔티티로 변환
        Article article = articleDto.toEntity();

        // 2. 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null || id != article.getId()) {
            log.info("잘못된 요청!");
            return null;
        }

        // 3. 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }


    public Article delete(Long id) {

        // 1. 삭제 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null) {
            return null;
        }

        // 2. 대상 삭제하기
        articleRepository.delete(target);
        return target;

    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> articleDtos) {

        // 1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = articleDtos.stream()
                .map(articleDto -> articleDto.toEntity())
                .collect(Collectors.toList());

        // 2. 엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 3. 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결제 실패!"));
        return articleList;
    }
}
