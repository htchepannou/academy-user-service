package io.tchepannou.academy.user.dao;

import io.tchepannou.academy.user.domain.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionDao extends CrudRepository<Session, Integer>{
}
