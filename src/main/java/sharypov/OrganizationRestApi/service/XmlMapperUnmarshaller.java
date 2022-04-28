package sharypov.OrganizationRestApi.service;

import sharypov.OrganizationRestApi.model.OrganizationRequest;

/**
 * Сервис для получения экземпляра класса из XML
 */
public interface XmlMapperUnmarshaller {

    /**
     * Получение сущности OrganizationRequest из строки XML
     * @param xmlMessage строка XML
     * @return сущность OrganizationRequest
     */
    OrganizationRequest getModelOrganizationRequest(String xmlMessage);
}
