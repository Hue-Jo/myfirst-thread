package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 2. 엔티티 -> Dto 반환
//        List<CommentDto> dtos = new ArrayList<>();
//        for(int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }

        // 3. 결과 반환
        return commentRepository.findByArticleId(articleId) // 댓글 엔티티 목록 조회
                .stream() // 엔티티 목록을 스트림으로 변환
                .map(comment -> CommentDto.createCommentDto(comment)) // 엔티티를 Dto로 매핑
                .collect(Collectors.toList()); // 스트림을 리스트로 반환
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않아 댓글 작성에 실패했습니다."));
        Comment comment = Comment.createComment(dto, article);
        Comment created = commentRepository.save(comment);
        return CommentDto.createCommentDto(comment);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제할 댓글이 존재하지 않습니다."));
        target.patch(dto);
        Comment updated = commentRepository.save(target);
        return CommentDto.createCommentDto(updated);
    }
}
