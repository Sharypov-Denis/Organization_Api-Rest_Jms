package sharypov.OrganizationRestApi.properties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Параметры подключения к очередям ActiveMq
 */
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "activemq")
public class ActiveMqProperties {
    private String brokerUrl;
    private String user;
    private String password;
    private String toQueue;
    private String fromQueue;
    private String listenerConcurrency;
}
