package io.codeagainsthumanity.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Principal;

public interface StatusRepository extends JpaRepository<Status, Long> {
    public Status findByGameAndPlayer(ApplicationUser playerId, double gameId);
}
