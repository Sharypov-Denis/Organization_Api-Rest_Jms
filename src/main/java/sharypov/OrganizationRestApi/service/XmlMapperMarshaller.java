package sharypov.OrganizationRestApi.service;

import sharypov.OrganizationRestApi.model.OrganizationResponse;

/**
 * Сервис для получения XML из экземпляра класса
 */
public interface XmlMapperMarshaller {

    /**
     * Получение строки XML из сущности OrganizationResponse
     * @param response Сущность OrganizationResponse
     * @return XML в строковом представлении
     */
    String getXmlStringOrganizationResponse(OrganizationResponse response);
}
