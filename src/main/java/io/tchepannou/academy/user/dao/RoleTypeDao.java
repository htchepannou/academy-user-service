package io.tchepannou.academy.user.dao;

import io.tchepannou.academy.user.domain.RoleType;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleTypeDao extends PersistentEnumRepository<RoleType>{
}
