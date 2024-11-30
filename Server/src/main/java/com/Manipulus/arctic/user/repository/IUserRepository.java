package com.Manipulus.arctic.user.repository;

import com.Manipulus.arctic.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface IUserRepository extends JpaRepository<User, Integer> {
    abstract User findUserByEmail(String email);
    abstract User findUserByUserName(String username);

}
