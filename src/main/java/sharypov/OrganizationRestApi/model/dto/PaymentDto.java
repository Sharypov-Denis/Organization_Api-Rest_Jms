package sharypov.OrganizationRestApi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sharypov.OrganizationRestApi.model.error.RestError;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Transfer object сущность "Платеж" для взаимодействия с REST интерфейсом
 */

@JsonIgnoreProperties
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto extends RestError {

    /**
     * Инн отправителя
     */
    @Size(min = 5, max = 10, message = "ИНН отправителя должен содержать от 5 до 10 символов")
    @NotEmpty(message = "ИНН отправителя не может быть пустым")
    private String senderInn;

    /**
     * Инн получателя
     */
    @Size(min = 5, max = 10, message = "ИНН получателя должен содержать от 5 до 10 символов")
    @NotEmpty(message = "ИНН получателя не может быть пустым")
    private String receiverInn;

    /**
     * Счет Отправителя
     */
    @Size(min = 5, max = 10, message = "Счет отправителя должен содержать от 5 до 10 символов")
    @NotEmpty(message = "Счет отправителя не может быть пустым")
    private String senderAccount;

    /**
     * Счет Получателя
     */
    @Size(min = 5, max = 10, message = "Счет получателя не может содержать не менее от 5 до 10 символов")
    @NotEmpty(message = "Счет получателя не может быть пустым")
    private String receiverAccount;

    /**
     * Баланс счета
     */
    @Min(value = 10, message = "Сумма перевод не может быть меньше 10")
    private Long balance;

    /**
     * Статус
     */
    private String status;

    /**
     * Уникальный идентификатор операции
     */
    private String transactionalId;
}
