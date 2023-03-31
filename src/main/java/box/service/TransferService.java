package box.service;

import box.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface TransferService {

    void transferMoney(long idSender, long idReceiver, BigDecimal amount);
    Iterable<Account> getAllAccounts();
    List<Account> findAccountsByName(String name);
}
