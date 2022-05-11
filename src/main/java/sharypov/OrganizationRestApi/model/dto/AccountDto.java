package sharypov.OrganizationRestApi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

/**
 * Transfer object сущность "Счет" для взаимодействия с REST интерфейсом
 */
@JsonIgnoreProperties
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    /**
     * ID организации, к которой привязан счет
     */
    private Long orgId;

    /**
     * Баланс счета
     */
    private Long balance;

    /**
     * Счет
     */
    private String account;
}
