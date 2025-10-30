package ru.hakaton.portvision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {
    List<Subscriber> findByEventTypeAndTargetId(String eventType, Integer targetId);

    List<Subscriber> findByEventType(String eventType);
}
