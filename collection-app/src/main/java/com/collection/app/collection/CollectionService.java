package com.collection.app.collection;

import java.util.List;
import java.util.UUID;

import com.collection.app.audit.AuditService;
import com.collection.app.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CollectionService {

    public boolean existsAnyCollection() {
        return !getCollections().isEmpty();
    }

    public List<Collection> getCollectionsByIds(final List<UUID> collectionIds) {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final CriteriaBuilder criteriaBuilder = session
                    .getCriteriaBuilder();
            final CriteriaQuery<Collection> collectionQuery = criteriaBuilder.createQuery(Collection.class);
            final Root<Collection> root = collectionQuery.from(Collection.class);
            final Expression<UUID> uuidExpression = root.get("id");
            final Predicate predicate = uuidExpression.in(collectionIds);
            collectionQuery.where(predicate);
            return session.createQuery(collectionQuery).getResultList();
        }
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

    public Collection save(final Collection collection) {
        AuditService.audit(String.format("Creating collection: %s", collection.getName()));
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(collection);
            transaction.commit();
            AuditService.audit(String.format("Created collection: %s", collection));
            return collection;
        }
    }

}
