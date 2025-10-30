package ru.hakaton.portvision;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<String> create(Comment comment) {
        commentService.create(comment);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/comment/{portId}")
    public List<Comment> findAllByPortId(@PathVariable Integer portId) {
        return commentService.findAttachmentsByPortId(portId);
    }
}
