package sharypov.OrganizationRestApi.listener;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import sharypov.OrganizationRestApi.service.impl.ProcessorImpl;

import javax.jms.TextMessage;

/**
 * Чтение сообщения из очереди ActiveMq
 */
@Component
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ListenerActiveMq {

    private ProcessorImpl processorImpl;
    @Autowired
    public ListenerActiveMq(ProcessorImpl processorImpl) {
        this.processorImpl = processorImpl;
    }

    @JmsListener(destination = "${activemq.fromQueue}", containerFactory = "sampleListenerFactory")
    public void received(TextMessage msg) {
        log.info("Слушатель получил сообщение");
        try {
            processorImpl.process(msg.getText());
            log.info("Слушатель начал обрабатывать сообщения");
        } catch (Exception ex) {
            log.error("Слушатель получил ошибку. Детали: " + ex.getMessage());
        }
    }
}
