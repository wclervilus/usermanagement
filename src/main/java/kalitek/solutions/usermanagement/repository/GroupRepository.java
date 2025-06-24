package kalitek.solutions.usermanagement.repository;

import kalitek.solutions.usermanagement.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {}
