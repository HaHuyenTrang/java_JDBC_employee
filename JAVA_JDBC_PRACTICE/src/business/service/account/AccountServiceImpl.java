package business.service.account;

import business.dao.account.AccountDAO;
import business.dao.account.AccountDAOImp;
import business.model.Account;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private final AccountDAO accountDAO = new AccountDAOImp();

    @Override
    public boolean login(String username, String password) {
        Account acc = accountDAO.getByUsername(username);
        if (acc != null) {
            return acc.login(username, password);
        }
        return false;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDAO.getAll();
    }

    @Override
    public boolean createAccount(Account account) {
        return accountDAO.insert(account);
    }

    @Override
    public boolean updateAccount(Account account) {
        return accountDAO.update(account);
    }

    @Override
    public boolean deleteAccount(String username) {
        return accountDAO.delete(username);
    }

    @Override
    public Account findAccountByUsername(String username) {
        return accountDAO.getByUsername(username);
    }
}
