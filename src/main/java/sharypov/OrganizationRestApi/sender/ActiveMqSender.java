package sharypov.OrganizationRestApi.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

/**
 * Сервис отправки сообщения в очередь ActiveMQ
 */
@Slf4j
@Service
public class ActiveMqSender {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Queue queue;

    /**
     * Отправка сообщения
     * @param xmlMessage сообщение в строковом представлении
     */
    public void send(String xmlMessage) {
        try {
            log.info("");
            this.jmsTemplate.convertAndSend(this.queue, xmlMessage);
            log.info("");
        }catch (Exception ex) {
            log.error("");
        }
    }
}
