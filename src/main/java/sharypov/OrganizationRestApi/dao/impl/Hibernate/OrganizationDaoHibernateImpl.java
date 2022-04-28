package sharypov.OrganizationRestApi.dao.impl.Hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sharypov.OrganizationRestApi.dao.OrganizationDao;
import sharypov.OrganizationRestApi.model.Organization;

import java.util.List;

/**
 * Реализация интерфейса через Hibernate
 */
@Repository("Hibernate")
@Transactional
@Slf4j
public class OrganizationDaoHibernateImpl implements OrganizationDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public OrganizationDaoHibernateImpl() {
    }

    @Override
    public List<Organization> getAll() {
        String sql = "Select new " + Organization.class.getName() //
                + "(e.id,e.name,e.balance) " //
                + " from " + Organization.class.getName() + " e ";
        Session session = this.sessionFactory.getCurrentSession();
        Query<Organization> query = session.createQuery(sql, Organization.class);
        return query.getResultList();
    }

    @Override
    public Organization getByInn(String orgInn) {
        return null;
    }

    @Override
    public Organization getById(Long id) {
      //  Session session = this.sessionFactory.getCurrentSession();
      //  return session.get(Organization.class, id);
        log.info("Вызван метод поиска по id");
       return getCurrentSession().get(Organization.class, id);
    }

    @Override
    public Organization create(Organization organization) {
        return null;
    }

    @Override
    public Organization update(Long id, Organization organization) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
