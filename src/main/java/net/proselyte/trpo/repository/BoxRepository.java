package net.proselyte.trpo.repository;

import net.proselyte.trpo.entity.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxRepository extends JpaRepository<Box, Integer> {
}
