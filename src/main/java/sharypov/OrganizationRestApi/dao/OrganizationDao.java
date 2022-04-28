package sharypov.OrganizationRestApi.dao;

import sharypov.OrganizationRestApi.model.Organization;

import java.util.List;

/**
 * Интерфейс взаимодействия с БД
 */
public interface OrganizationDao {
    /**
     * Получение списка всех организаций
     * @return список
     */
    List<Organization> getAll();

    /**
     * Получение организации по ИНН
     * @param orgInn инн организации
     * @return организация
     */
    Organization getByInn(String orgInn);

    /**
     * Получение организации по ID
     * @param id организации
     * @return организация
     */
    Organization getById(Long id);

    /**
     * СОздание новой организации
     * @param organization экземпляр Организации с новыми данными по организации
     * @return новая организация
     */
    Organization create(Organization organization);

    /**
     * Обновление организации
     * @param id ID организации
     * @param organization экземпляр Организации с новыми данными по организации
     * @return обновленная организация
     */
    Organization update(Long id, Organization organization);

    /**
     * Удаление организации по ID
     * @param id организации
     */
    void delete(Long id);
}
