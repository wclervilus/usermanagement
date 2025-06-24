package kalitek.solutions.usermanagement.repository;

import kalitek.solutions.usermanagement.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {}
