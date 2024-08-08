package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticledId() {
        {
            Long articleId = 4L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            Article article = new Article(4L, "당신의 인생영화는?", "댓글을 남겨주세요!");
            Comment a = new Comment(1L, article, "CRUD", "지구를 지켜라");
            Comment b = new Comment(2L, article, "JPA", "눈 먼 자들의 도시");
            Comment c = new Comment(3L, article, "HTML", "김씨 표류기");
            List<Comment> expected = Arrays.asList(a, b, c);

            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력");

        }
        {
            Long articleId = 1L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            Article article = new Article(1L, "가가가", "1111");
            List<Comment> expected = Arrays.asList();

            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");

        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        // "CRUD" 회원의 댓글 조회
        {
            String nickname = "CRUD";

            Comment a = new Comment(1L, new Article(4L, "당신의 인생영화는?", "댓글을 남겨주세요!"), nickname, "지구를 지켜라");
            Comment b = new Comment(4L, new Article(5L, "당신의 소울푸드는?", "댓글을 남겨주세요!!"), nickname, "치킨");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미는?", "댓글을 남겨주세요!!!"), nickname, "운동");
            List<Comment> expected = Arrays.asList(a, b, c);

            List<Comment> comments = commentRepository.findByNickname(nickname);

            assertEquals(expected.toString(), comments.toString(), "CRUD의 모든 댓글 춫력");

        }
    }

}