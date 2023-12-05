package collection.tcg;

import collection.audit.AuditService;
import collection.core.Collection;
import collection.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CardService {

    public List<Card> loadCards() {
        try (final Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            final CriteriaQuery<Card> cardCriteriaQuery = session
                    .getCriteriaBuilder()
                    .createQuery(Card.class);
            cardCriteriaQuery.from(Card.class);
            return session.createQuery(cardCriteriaQuery).getResultList();
        }

    }

    public void addCard(final Card card) {

        if (card.getCollection() == null) {
            System.out.println("Collection is null. What happened?");
        }

        AuditService.audit(String.format("Creating card: %s", card.toString()));
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(card);
            transaction.commit();
            AuditService.audit(String.format("Created card: %s", card));
        }
    }

    public void removeCard(final Long cardId) {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()){

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
