package ru.he.jlmq.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.he.jlmq.server.entity.Consumer;
import ru.he.jlmq.server.entity.ConsumerState;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    Optional<Consumer> findByUsername(String username);

    List<Consumer> findByState(ConsumerState state);

}
