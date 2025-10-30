package ru.hakaton.portvision.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hakaton.portvision.dto.Comment;
import ru.hakaton.portvision.dto.Port;
import ru.hakaton.portvision.dto.PortDto;
import ru.hakaton.portvision.repo.CommentRepository;
import ru.hakaton.portvision.repo.PortRepository;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortService {

    private final PortRepository portRepository;
    private final CommentRepository commentRepository;

    public PortDto findById(Integer id) {
        Port port = portRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Порт не найден"));
        List<Comment> comments = commentRepository.findByStarsNotAndPortId(-1, id);
        List<Comment> files = commentRepository.findAllByStarsAndPortId(-1, id);
        return new PortDto()
                .setPort(port)
                .setComments(comments)
                .setFiles(files);
    }

    public List<PortDto> findAll() {
        List<Port> ports = portRepository.findAll();
        Map<Integer, List<Comment>> commentsPortId = commentRepository.findAllByStarsNot(-1).stream().collect(Collectors.groupingBy(Comment::getPortId));
        Map<Integer, List<Comment>> filesPortId = commentRepository.findAllByStars(-1).stream().collect(Collectors.groupingBy(Comment::getPortId));
        return ports.stream()
                .map(port -> new PortDto()
                        .setPort(port)
                        .setComments(commentsPortId.get(port.getId()))
                        .setFiles(filesPortId.get(port.getId())))
                .collect(Collectors.toList());
    }
}
