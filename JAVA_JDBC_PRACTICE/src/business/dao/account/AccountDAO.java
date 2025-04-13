package business.dao.account;

import business.model.Account;
import java.util.List;

public interface AccountDAO {
    Account getByUsername(String username);
    List<Account> getAll();
    boolean insert(Account account);
    boolean update(Account account);
    boolean delete(String username);
}
