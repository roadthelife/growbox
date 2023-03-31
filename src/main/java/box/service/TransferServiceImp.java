package box.service;

import box.error.AccountNotFoundException;
import box.model.Account;
import box.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
@Service
public class TransferServiceImp implements TransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        Account sender = accountRepository.findById(idSender)
                .orElseThrow(() -> new AccountNotFoundException());

        Account receiver = accountRepository.findById(idReceiver)
                .orElseThrow(() -> new AccountNotFoundException());

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

        accountRepository.changeAmount(idSender, senderNewAmount);
        accountRepository.changeAmount(idReceiver, receiverNewAmount);
    }

    @Override
    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAccountsByName(String name) {
        return accountRepository.findAccountsByName(name);
    }
}
