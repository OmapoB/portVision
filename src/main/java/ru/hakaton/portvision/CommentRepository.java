package ru.hakaton.portvision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> finAlldByPortId(Integer portId);

    List<Comment> findAllByStarsAndPortId(Integer stars, Integer portId);

    List<Comment> findAllByStars(Integer stars);

    List<Comment> findByStarsNotAndPortId(Integer stars, Integer portId);

    List<Comment> findAllByStarsNot(Integer stars);
}