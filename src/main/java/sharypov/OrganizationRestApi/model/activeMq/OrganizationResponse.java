package sharypov.OrganizationRestApi.model.activeMq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Сущность "Организация. Ответ на запрос через ActiveMQ"
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OrganizationResponse")
public class OrganizationResponse {

    String name;
    String orgInn;

}
