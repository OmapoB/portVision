package ru.hakaton.portvision.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hakaton.portvision.dto.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByStarsAndPortId(Integer stars, Integer portId);

    List<Comment> findAllByStars(Integer stars);

    List<Comment> findByStarsNotAndPortId(Integer stars, Integer portId);

    List<Comment> findAllByStarsNot(Integer stars);
}