package com.Manipulus.arctic.user.dao;

import com.Manipulus.arctic.role.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Integer> {
}
