package com.Manipulus.arctic.unit.repository;

import com.Manipulus.arctic.unit.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface IUnitRepository extends JpaRepository<Unit, Integer> {
}
