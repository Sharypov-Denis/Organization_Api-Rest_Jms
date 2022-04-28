package sharypov.OrganizationRestApi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ErrorHandler;

/**
 * Обработчик ошибок для listener'ов
 */
@Slf4j
public class BasicErrorHandler implements ErrorHandler {

    private final String listenerName;

    public BasicErrorHandler(String listenerName) {
        this.listenerName = listenerName;
    }

    @Override
    public void handleError(Throwable t) {
        log.error(listenerName + " JMS Listener received error: " + t.getMessage());
    }
}
