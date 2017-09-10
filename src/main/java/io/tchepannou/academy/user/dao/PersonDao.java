package io.tchepannou.academy.user.dao;

import io.tchepannou.academy.user.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends CrudRepository<Person, Integer>{
    Person findByEmailIgnoreCase(String email);
}
