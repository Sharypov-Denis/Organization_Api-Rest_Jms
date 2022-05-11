package sharypov.OrganizationRestApi.dao;

import sharypov.OrganizationRestApi.model.Payment;

/**
 * Интерфейс взаимодействия с БД(управление платежами)
 */
public interface PaymentDao {

    /**
     * Проверка и создание платежа
     * @param payment
     * @return
     */
    Payment create(Payment payment);
}
