package collection.core;

import java.util.List;

import collection.audit.AuditService;
import collection.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CollectionService {

    public boolean existsAnyCollection() {
        return !getCollections().isEmpty();
    }

    public List<Collection> getCollections() {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final CriteriaQuery<Collection> cardCriteriaQuery = session
                    .getCriteriaBuilder()
                    .createQuery(Collection.class);
            cardCriteriaQuery.from(Collection.class);
            return session.createQuery(cardCriteriaQuery).getResultList();
        }
    }

    public void save(final Collection collection) {
        AuditService.audit(String.format("Creating collection: %s", collection.getName()));
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(collection);
            transaction.commit();
            AuditService.audit(String.format("Created collection: %s", collection));
        }
    }

}
