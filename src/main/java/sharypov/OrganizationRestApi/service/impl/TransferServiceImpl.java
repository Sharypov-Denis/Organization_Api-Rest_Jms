package sharypov.OrganizationRestApi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sharypov.OrganizationRestApi.dao.impl.EntityManager.AccountDaoEntityManagerImpl;
import sharypov.OrganizationRestApi.exception.CommonException;
import sharypov.OrganizationRestApi.model.Account;
import sharypov.OrganizationRestApi.model.dto.PaymentDto;
import sharypov.OrganizationRestApi.service.TransferService;

@Slf4j
@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    AccountDaoEntityManagerImpl accountDaoEntityManagerImpl;

    @Override
    public void transferSum(PaymentDto paymentDto) {
        Long sum = paymentDto.getBalance();
        try {
            Account senderAccount = accountDaoEntityManagerImpl.getByAccountNumber(paymentDto.getSenderAccount());
            Account receiverAccount = accountDaoEntityManagerImpl.getByAccountNumber(paymentDto.getReceiverAccount());
            accountDaoEntityManagerImpl.updateSum(senderAccount.getId(), receiverAccount.getId(), sum);
            log.info("Списание и зачисления завершены успешно");
        } catch (Exception e) {
            log.error("Ошибка Списание и зачисления");
            throw new CommonException(e.getMessage());
        }
    }
}
