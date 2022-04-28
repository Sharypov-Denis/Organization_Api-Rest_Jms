package sharypov.OrganizationRestApi.service;

/**
 * Сервис работы с XML.
 */
public interface XmlUtil {
    /**
     * Проверка наличия определенного корневого элемента в XML
     * @param xml XML в формате String, для поиска корневого элемента
     * @param rootElement название корневого элемента
     * @return true, если искомый коневой элемент присутствует в XML, иначе false
     */
    boolean checkXmlHasRoot(String xml, String rootElement);
}
