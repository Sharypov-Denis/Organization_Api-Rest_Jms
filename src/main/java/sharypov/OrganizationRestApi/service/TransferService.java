package sharypov.OrganizationRestApi.service;

import sharypov.OrganizationRestApi.model.dto.PaymentDto;

public interface TransferService {
    void transferSum(PaymentDto paymentDto);
}
