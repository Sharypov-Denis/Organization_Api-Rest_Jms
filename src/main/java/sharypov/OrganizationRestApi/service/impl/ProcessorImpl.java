package sharypov.OrganizationRestApi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис обработки входящего сообщения из Listener
 */
@Service
@Slf4j
public class ProcessorImpl {

    private static final String ORGANIZATION_REQUEST = "OrganizationRequest";
    @Autowired
    XmlUtilImpl xmlUtilImpl;
    @Autowired
    MainRequestActiveMqImpl mainRequestActiveMq;

    public void process(String xmlMessage) {
        try {
            if (xmlUtilImpl.checkXmlHasRoot(xmlMessage, ORGANIZATION_REQUEST)) {
                log.info("Получил запрос на предоставление информации об организации по ИНН");
                mainRequestActiveMq.start(xmlMessage);
            }
        } catch (Exception ex) {
            log.error("Ошибка в работе сервиса: " + ex.getMessage(), ex);
        }
    }
}
