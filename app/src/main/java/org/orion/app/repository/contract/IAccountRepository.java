package org.orion.app.repository.contract;

import org.orion.app.entity.Account;

import java.util.Set;

public interface IAccountRepository {
    Account save(Account account);

    Account findById(Integer id);
    Account findByEmail(String email);
    Set<Account> findAll();
}
