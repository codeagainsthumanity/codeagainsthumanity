package io.codeagainsthumanity.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    public Game findByGameCode(double gameCode);
}
