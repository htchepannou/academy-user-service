package io.tchepannou.academy.user.dao;

import io.tchepannou.academy.user.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends CrudRepository<Account, Integer>{
    Account findByPersonId(Integer personId);
}
