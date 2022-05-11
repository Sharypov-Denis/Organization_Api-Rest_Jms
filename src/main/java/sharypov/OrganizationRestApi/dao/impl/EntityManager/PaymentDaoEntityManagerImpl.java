package sharypov.OrganizationRestApi.dao.impl.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sharypov.OrganizationRestApi.dao.PaymentDao;
import sharypov.OrganizationRestApi.model.Payment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("EntityManagerPaymentDao")
@Transactional(readOnly = true)
public class PaymentDaoEntityManagerImpl implements PaymentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public PaymentDaoEntityManagerImpl() {
    }

    @Override
    @Transactional
    public Payment create(Payment payment) {
        entityManager.persist(payment);
        return payment;
    }

}
