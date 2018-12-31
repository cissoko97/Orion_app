package org.orion.app.repository.contract;

import org.orion.app.entity.Account;

public interface IAccountRepository {
    Account save(Account account);

    Account findById(Integer id);
    Account findByEmail(String email);
}
