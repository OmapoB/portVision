package ru.hakaton.portvision;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final SubscriptionService subscriptionService;

    public Comment create(Comment comment) {
        comment = commentRepository.save(comment);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(comment.getAuthor())
                    .append("\n")
                    .append(comment.getText())
                    .append("\n")
                    .append(comment.getCreatedAt());
            subscriptionService.send(new Event().setEventType("comment").setTargetId(comment.getPortId()).setBody(stringBuilder.toString()));
        } catch (Exception ignored) {}
        return comment;
    }

    public List<Comment> findAttachmentsByPortId(Integer portId) {
        return commentRepository.findAllByStarsAndPortId(-1, portId);
    }
}
