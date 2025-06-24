package kalitek.solutions.usermanagement.repository;

import kalitek.solutions.usermanagement.model.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointRepository extends JpaRepository<Endpoint, Long> {}
