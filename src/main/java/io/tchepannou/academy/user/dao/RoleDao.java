package io.tchepannou.academy.user.dao;

import io.tchepannou.academy.user.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Integer>{
    Role findByPersonIdAndTypeId(Integer personId, Integer typeId);
}
