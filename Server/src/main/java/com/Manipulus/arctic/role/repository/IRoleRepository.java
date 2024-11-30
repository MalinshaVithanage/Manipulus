package com.Manipulus.arctic.role.repository;

import com.Manipulus.arctic.role.model.Role;
import com.Manipulus.arctic.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface IRoleRepository  extends JpaRepository<Role, Integer>{
}