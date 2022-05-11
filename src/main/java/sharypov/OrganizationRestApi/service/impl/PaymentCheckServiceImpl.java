package sharypov.OrganizationRestApi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import sharypov.OrganizationRestApi.dao.impl.EntityManager.AccountDaoEntityManagerImpl;
import sharypov.OrganizationRestApi.exception.CommonException;
import sharypov.OrganizationRestApi.model.Account;
import sharypov.OrganizationRestApi.model.dto.PaymentDto;

import javax.persistence.NoResultException;
import javax.validation.Valid;


@Slf4j
@Service
@Validated
public class PaymentCheckServiceImpl {

    @Autowired
    AccountDaoEntityManagerImpl accountDaoEntityManagerImpl;

    public Boolean checkSum(@Valid PaymentDto paymentDto) {
        log.info("Проверка суммы");
        Account senderAccount = accountDaoEntityManagerImpl.getByAccountNumber(paymentDto.getSenderAccount());
        return senderAccount.getSum() >= paymentDto.getBalance();
    }

    public void checkSenderAccount(@Valid PaymentDto paymentDto) {
        try {
            Account senderAccount = accountDaoEntityManagerImpl.getByAccountNumber(paymentDto.getSenderAccount());
        } catch (NoResultException exception) {
            throw new CommonException("Счет отправителя №" + paymentDto.getSenderAccount() + " не существует");
        }
    }

    public void checkReceiverAccount(@Valid PaymentDto paymentDto) {
        try {
            Account receiverAccount = accountDaoEntityManagerImpl.getByAccountNumber(paymentDto.getReceiverAccount());
        } catch (NoResultException exception) {
            throw new CommonException("Счет получателя №" + paymentDto.getReceiverAccount() + " не существует");
        }
    }

    public Boolean checkStatusOrganization(PaymentDto paymentDto) {
        return false;
    }
}
