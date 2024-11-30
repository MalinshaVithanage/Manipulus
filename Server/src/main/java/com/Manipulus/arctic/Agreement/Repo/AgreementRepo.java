package com.Manipulus.arctic.Agreement.Repo;



import com.Manipulus.arctic.Agreement.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface AgreementRepo extends JpaRepository<Agreement,Integer> {
}

