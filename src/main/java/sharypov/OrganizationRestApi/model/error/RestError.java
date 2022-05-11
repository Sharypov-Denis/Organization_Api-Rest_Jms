package sharypov.OrganizationRestApi.model.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Сущность, содержащая ошибки REST API
 */
@Data
@NoArgsConstructor
public class RestError {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transactionalId;

    public RestError(String error) {
        this.error = error;
    }

    public RestError(String error, String transactionalId) {
        this.error = error;
        this.transactionalId = transactionalId;
    }
}
