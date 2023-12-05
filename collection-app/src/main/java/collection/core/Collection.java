package collection.core;

import collection.tcg.Card;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

@Entity
@Table(name = "collection")
public class Collection {

    @Id
    @Column
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", type = UUIDGenerator.class)
    private UUID uuid;

    @Column
    private String name;

    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY)
    private List<Card> cards = new LinkedList<>();

    public static Collection create(final String name) {
        final Collection collection = new Collection();
        collection.setName(name);
        return collection;
    }

    public Collection() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", cards=" + cards +
                '}';
    }
}
