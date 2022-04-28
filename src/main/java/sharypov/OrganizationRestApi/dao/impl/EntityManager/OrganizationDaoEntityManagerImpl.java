package sharypov.OrganizationRestApi.dao.impl.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sharypov.OrganizationRestApi.dao.OrganizationDao;
import sharypov.OrganizationRestApi.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Реализация интерфейса через EntityManager
 */
@Repository("EntityManager")
@Transactional
public class OrganizationDaoEntityManagerImpl implements OrganizationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public OrganizationDaoEntityManagerImpl() {
    }

    @Override
    public List<Organization> getAll() {
        return entityManager.createQuery("from Organization c order by c.id desc", Organization.class).getResultList();
    }

    @Override
    public Organization getByInn(String orgInn) {
        Organization organization = entityManager.createQuery(
                "SELECT o from Organization o WHERE o.inn = :inn", Organization.class).
                setParameter("inn", orgInn).getSingleResult();
        return organization;
    }

    @Override
    public Organization getById(Long id) {
        return entityManager.find(Organization.class, id);
    }

    @Override
    public Organization create(Organization organization) {
        entityManager.persist(organization);
        return organization;
    }

    @Override
    public Organization update(Long id, Organization organization) {
        Organization original = entityManager.find(Organization.class, id);
        if (original != null) {
            original.setName(organization.getName());
            original.setInn(organization.getInn());
            /*for (City city : original.getCities()) {
                entityManager.remove(city);
            }
            original.getCities().clear();
            for (City city : organization.getCities()) {
                city.setCountry(original);
                original.getCities().add(city);
                entityManager.persist(city);
            }*/
            entityManager.merge(original);
        }
        return original;
    }

    @Override
    public void delete(Long id) {
        Organization organization = entityManager.find(Organization.class, id);
        if (organization != null) {
            entityManager.remove(organization);
        }
    }
}
