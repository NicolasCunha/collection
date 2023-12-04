package collection.tcg;

import collection.data.HibernateUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CardPersistence {

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

        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(card);
            transaction.commit();
        }

    }

    public void removeCard(final Long cardId) {

        try (final Session session = HibernateUtil.getSessionFactory().openSession()){

            final Card card = session.find(Card.class, cardId);

            if (card == null) {
                return;
            }

            final Transaction transaction = session.beginTransaction();
            session.remove(card);
            transaction.commit();

        }

    }

}
