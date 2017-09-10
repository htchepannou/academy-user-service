package io.tchepannou.academy.user.dao;

import io.tchepannou.academy.user.domain.PersistentEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersistentEnumRepository<T extends PersistentEnum> extends CrudRepository<T, Integer> {
    T findByNameIgnoreCase(String name);
    T findByRank(Integer rank);
}
