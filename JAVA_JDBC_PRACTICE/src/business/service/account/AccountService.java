package business.service.account;

import business.model.Account;

import java.util.List;

public interface AccountService {
    boolean login(String username, String password);
    List<Account> getAllAccounts();
    boolean createAccount(Account account);
    boolean updateAccount(Account account);
    boolean deleteAccount(String username);
    Account findAccountByUsername(String username);
}
