package ru.hakaton.portvision.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hakaton.portvision.dto.Port;

import java.util.Collection;
import java.util.List;

@Repository
public interface PortRepository extends JpaRepository<Port, Integer> {
    List<Port> findByIdIn(Collection<Integer> ids);
}