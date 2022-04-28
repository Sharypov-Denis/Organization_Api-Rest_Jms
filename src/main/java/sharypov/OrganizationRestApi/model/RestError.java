package sharypov.OrganizationRestApi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestError {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;
}
