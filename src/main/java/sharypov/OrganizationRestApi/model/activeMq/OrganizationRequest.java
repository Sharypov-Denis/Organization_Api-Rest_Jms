package sharypov.OrganizationRestApi.model.activeMq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

/**
 * Сущность "Организация. Запрос данных из очереди"
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OrganizationRequest")
public class OrganizationRequest {

    @XmlAttribute(name = "orgInn")
    String orgInn;
}
