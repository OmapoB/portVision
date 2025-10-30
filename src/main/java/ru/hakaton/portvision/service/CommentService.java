package ru.hakaton.portvision.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hakaton.portvision.dto.Comment;
import ru.hakaton.portvision.repo.CommentRepository;
import ru.hakaton.portvision.dto.Event;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final SubscriptionService subscriptionService;

    public Comment create(Comment comment) {
        comment = commentRepository.save(comment);
        Comment finalComment = comment;
        CompletableFuture.runAsync(() -> {StringBuilder stringBuilder = new StringBuilder();
            try {
                stringBuilder.append(finalComment.getAuthor())
                        .append("<br>")
                        .append(finalComment.getText())
                        .append("<br>")
                        .append(finalComment.getCreatedAt())
                        .append("<br>");
                subscriptionService.send(new Event().setEventType("COMMENT").setTargetId(finalComment.getPortId()).setBody(stringBuilder.toString()));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }});
        return comment;
    }
}
