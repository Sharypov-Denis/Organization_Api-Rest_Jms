package sharypov.OrganizationRestApi.dao;

import org.springframework.transaction.annotation.Transactional;
import sharypov.OrganizationRestApi.model.Account;
import sharypov.OrganizationRestApi.model.dto.AccountDto;

import java.util.List;

/**
 * Интерфейс взаимодействия с БД(управление счетом)
 */
public interface AccountDao {

    /**
     * Получение списка всех счетов
     * @return список счетов
     */
    List<Account> getAll();

    /**
     * Получение счета по Id
     * @param id ID счета
     * @return счет
     */
    Account getById(Long id);

    /**
     * Получение счета по номеру счета
     * @param accountNumber номер счета
     * @return счет
     */
    Account getByAccountNumber(String accountNumber);

    /**
     * Создание нового счета
     * @param account сущность Счет
     * @param orgId Id организации, к которой будет привязан счет
     * @return новый счет
     */
    AccountDto save(Account account, Long orgId);

    Account create(Account account, Long orgId);

    /**
     * Обновление текущего счета
     * @param id ID счета
     * @param account счет
     * @return Обновленная сущность счета
     */

    Account update(Long id, Account account);

    @Transactional
    void updateSum(Long idFrom, Long idTo, Long sum);

    /**
     * Удаление счета
     * @param id - ID счета, который необходимо удалить
     */
    void delete(Long id);
}
