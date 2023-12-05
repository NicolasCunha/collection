package collection.tcg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "collection_card")
public final class Card {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String game;

    @Column
    private String name;

    @Column
    private String rarity;

    @Column
    private String cardSet;

    @Column
    private String rating;

    @Column
    private String comment;

    public Card() {
    }

    public Card(Long id, String game, String name, String rarity, String cardSet, String rating, String comment) {
        this.id = id;
        this.game = game;
        this.name = name;
        this.rarity = rarity;
        this.cardSet = cardSet;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public String getGame() {
        return game;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public String getCardSet() {
        return cardSet;
    }

    public String getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Card) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.game, that.game) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.rarity, that.rarity) &&
                Objects.equals(this.cardSet, that.cardSet) &&
                Objects.equals(this.rating, that.rating) &&
                Objects.equals(this.comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, game, name, rarity, cardSet, rating, comment);
    }

    @Override
    public String toString() {
        return "Card[" +
                "id=" + id + ", " +
                "game=" + game + ", " +
                "name=" + name + ", " +
                "rarity=" + rarity + ", " +
                "set=" + cardSet + ", " +
                "rating=" + rating + ", " +
                "comment=" + comment + ']';
    }

}
