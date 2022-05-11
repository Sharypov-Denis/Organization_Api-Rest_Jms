package sharypov.OrganizationRestApi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sharypov.OrganizationRestApi.dao.impl.EntityManager.AccountDaoEntityManagerImpl;
import sharypov.OrganizationRestApi.model.Account;
import sharypov.OrganizationRestApi.model.dto.AccountDto;
import sharypov.OrganizationRestApi.model.error.RestError;

import java.util.List;

/**
 * REST API для получения информации о счетамх, обновлении, добавлении и удаления(CRUD)
 */
@Slf4j
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController extends RestError {

    @Autowired
    private AccountDaoEntityManagerImpl accountDaoEntityManagerImpl;

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccount() {
        return accountDaoEntityManagerImpl.getAll();
    }

    @GetMapping(value = "/account/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable("Id") Long id) {
        return accountDaoEntityManagerImpl.getById(id);
    }


    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto createAccountDao(@RequestBody AccountDto accountDto) {
        log.info("Test controller: " + accountDto.toString());
        Account account = new Account();
        account.setAccount(accountDto.getAccount());
        account.setSum(accountDto.getBalance());
        return this.accountDaoEntityManagerImpl.save(account, accountDto.getOrgId());
    }

    @PutMapping("/account/{AccountId}")
    public Account updateAccount(@PathVariable("OrganizationId") Long id,
                                           @RequestBody Account account) {
        return accountDaoEntityManagerImpl.update(id, account);
    }

    @DeleteMapping("/account/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable("Id") Long id) {
        accountDaoEntityManagerImpl.delete(id);
    }

}
