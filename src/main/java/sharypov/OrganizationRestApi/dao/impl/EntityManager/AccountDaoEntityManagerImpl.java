package sharypov.OrganizationRestApi.dao.impl.EntityManager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sharypov.OrganizationRestApi.dao.AccountDao;
import sharypov.OrganizationRestApi.exception.CommonException;
import sharypov.OrganizationRestApi.model.Account;
import sharypov.OrganizationRestApi.model.dto.AccountDto;
import sharypov.OrganizationRestApi.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Реализация интерфейса через EntityManager
 */
@Slf4j
@Repository("EntityManagerAccountDao")
@Transactional(readOnly = true)
public class AccountDaoEntityManagerImpl implements AccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    public AccountDaoEntityManagerImpl() {
    }

    @Override
    public List<Account> getAll() {
        return entityManager.createQuery("from Account c order by c.id desc", Account.class).getResultList();
    }

    @Override
    public Account getById(Long id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        Account account = entityManager.createQuery(
                "SELECT a from Account a WHERE a.account = :account", Account.class).
                setParameter("account", accountNumber).getSingleResult();
        return account;
    }

    @Override
    @Transactional
    public AccountDto save(Account account, Long orgId) {
        create(account, orgId);
        AccountDto accountDto = new AccountDto();
        accountDto.setAccount(account.getAccount());
        accountDto.setBalance(account.getSum());
        accountDto.setAccount("успешно");
        return accountDto;
    }

    @Override
    @Transactional
    public Account create(Account account, Long orgId) {
        account.setOrganization(entityManager.getReference(Organization.class, orgId));
        System.out.println("Test: " + account.toString());
        entityManager.persist(account);
        return account;
    }

    @Override
    @Transactional
    public Account update(Long id, Account account) {
        Account originalAccount = entityManager.find(Account.class, id);
        if (originalAccount != null) {
            originalAccount.setSum(account.getSum());
            originalAccount.setAccount(account.getAccount());
            entityManager.merge(originalAccount);
        }
        return originalAccount;
    }

    @Override
    @Transactional
    public void updateSum(Long idFrom, Long idTo, Long sum) {
        Account originalAccountFrom = entityManager.find(Account.class, idFrom);
        Account originalAccountTo = entityManager.find(Account.class, idTo);
        if ((originalAccountFrom.getSum() - sum) < 0) {
            throw new CommonException("На счете недостаточно средств");
        }
        if (originalAccountFrom != null) {
            originalAccountFrom.setSum(originalAccountFrom.getSum() - sum);
            entityManager.merge(originalAccountFrom);
        }
        if (originalAccountTo != null) {
            originalAccountTo.setSum(originalAccountTo.getSum() + sum);
            entityManager.merge(originalAccountTo);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Account account = entityManager.find(Account.class, id);
        if (account != null) {
            entityManager.remove(account);
        }
    }
}
