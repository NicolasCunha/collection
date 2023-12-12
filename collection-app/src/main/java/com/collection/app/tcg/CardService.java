package com.collection.app.tcg;

import com.collection.app.audit.AuditService;
import com.collection.app.collection.Collection;
import com.collection.app.log.LogService;
import com.collection.app.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Service responsible for card related operations.
 */
public class CardService {

  /**
   * Load all cards from determined collection.
   *
   * @param collection {@link Collection} instance.
   * @return {@link List} of {@link Card}.
   */
  public List<Card> loadCards(final Collection collection) {

    try (final Session session = HibernateUtil.getSessionFactory()
        .openSession()) {
      final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
      final CriteriaQuery<Card> cardCriteriaQuery = criteriaBuilder.createQuery(Card.class);
      final Root<Card> root = cardCriteriaQuery.from(Card.class);
      final Predicate predicate = criteriaBuilder.equal(root.get("collection"), collection);
      return session.createQuery(cardCriteriaQuery.where(predicate)).getResultList();
    }

  }

  /**
   * Persist a card.
   *
   * @param card {@link Card} instance.
   */
  public void addCard(final Card card) {

    if (card.getCollection() == null) {
      LogService.log("Collection is null. What happened?");
    }

    AuditService.audit(String.format("Creating card: %s", card.toString()));
    try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
      final Transaction transaction = session.beginTransaction();
      session.persist(card);
      transaction.commit();
      AuditService.audit(String.format("Created card: %s", card));
    }
  }

  /**
   * Deletes a card.
   *
   * @param cardId {@link Long} card id.
   */
  public void removeCard(final Long cardId) {
    try (final Session session = HibernateUtil.getSessionFactory().openSession()) {

      final Card card = session.find(Card.class, cardId);

      if (card == null) {
        return;
      }

      AuditService.audit(String.format("Removing card: %s", card));

      final Transaction transaction = session.beginTransaction();
      session.remove(card);
      transaction.commit();

    }

  }

}
