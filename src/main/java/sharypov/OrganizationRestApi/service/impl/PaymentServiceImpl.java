package sharypov.OrganizationRestApi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import sharypov.OrganizationRestApi.dao.impl.EntityManager.PaymentDaoEntityManagerImpl;
import sharypov.OrganizationRestApi.model.Payment;
import sharypov.OrganizationRestApi.model.dto.PaymentDto;
import sharypov.OrganizationRestApi.model.status.PaymentStatus;

import java.util.UUID;

@Slf4j
@Service
@Validated
public class PaymentServiceImpl {

    @Autowired
    PaymentCheckServiceImpl checkService;

    @Autowired
    TransferServiceImpl transferService;

    @Autowired
    PaymentDaoEntityManagerImpl paymentDaoEntityManager;

    @Transactional
    public PaymentDto transfer(PaymentDto paymentDto) {
        checkService.checkSenderAccount(paymentDto);
        checkService.checkReceiverAccount(paymentDto);
        paymentDto.setTransactionalId(UUID.randomUUID().toString());

        if (checkService.checkSum(paymentDto)) {
            paymentDto.setStatus(PaymentStatus.PROCESSED.getDescription());
            log.info("Сумма доступна");
            transferService.transferSum(paymentDto);
        } else {
            paymentDto.setStatus(PaymentStatus.INSUFFICIENT_FUNDS.getDescription());
        }

        paymentDaoEntityManager.create(getModelPayment(paymentDto));

        return paymentDto;
    }

    //todo: в маппер уберу
    public Payment getModelPayment(PaymentDto paymentDto) {
        Payment result = new Payment();
        result.setReceiverAccount(paymentDto.getReceiverAccount());
        result.setReceiverInn(paymentDto.getReceiverInn());
        result.setSenderAccount(paymentDto.getSenderAccount());
        result.setSenderInn(paymentDto.getSenderInn());
        result.setSum(paymentDto.getBalance());
        result.setStatus(paymentDto.getStatus());
        return result;
    }
}
