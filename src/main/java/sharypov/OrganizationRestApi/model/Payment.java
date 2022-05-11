package sharypov.OrganizationRestApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sharypov.OrganizationRestApi.model.error.RestError;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Сущность "Платеж"
 */
@Data
@Entity
@Table(name = "check_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private Long id;

    /**
     * Инн отправителя
     */
    private String senderInn;

    /**
     * Инн получателя
     */
    private String receiverInn;

    /**
     * Счет Отправителя
     */
    private String senderAccount;

    /**
     * Счет Получателя
     */
    private String receiverAccount;

    /**
     * Сумма
     */
    private Long sum;

    /**
     * Статус
     */
    private String status;

}
