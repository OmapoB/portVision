package ru.hakaton.portvision.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.hakaton.portvision.dto.Comment;
import ru.hakaton.portvision.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public Comment create(@RequestBody Comment comment) {
        return commentService.create(comment);
    }
}
