package com.collection.app.tcg;

import com.collection.app.collection.Collection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 * Represents a TCG card.
 */
@Entity
@Table(name = "collection_card")
public final class Card {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq_gen")
  @SequenceGenerator(name = "card_seq_gen", sequenceName = "card_seq", allocationSize = 1)
  private Long id;

  @JoinColumn(name = "collection_id")
  @ManyToOne
  private Collection collection;

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

  /**
   * Empty constructor.
   */
  public Card() {
    // noop
  }

  /**
   * Create a new {@link Card}.
   *
   * @param collection {@link Collection} where this card belongs.
   * @param id         {@link Long} card id. Can be null if it's going to be persisted afterward.
   * @param game       {@link String} game of which this card is part of.
   * @param name       {@link String} card name.
   * @param rarity     {@link String} rarity.
   * @param cardSet    {@link String} game set.
   * @param rating     {@link String} rarity or condition.
   * @param comment    {@link String} user comment.
   * @return {@link Card} instance.
   */
  public static Card create(final Collection collection,
                            final Long id,
                            final String game,
                            final String name,
                            final String rarity,
                            final String cardSet,
                            final String rating,
                            final String comment) {
    final Card card = new Card();
    card.setCollection(collection);
    card.setId(id);
    card.setGame(game);
    card.setName(name);
    card.setRarity(rarity);
    card.setCardSet(cardSet);
    card.setRating(rating);
    card.setComment(comment);
    return card;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Collection getCollection() {
    return collection;
  }

  public void setCollection(Collection collection) {
    this.collection = collection;
  }

  public String getGame() {
    return game;
  }

  public void setGame(String game) {
    this.game = game;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRarity() {
    return rarity;
  }

  public void setRarity(String rarity) {
    this.rarity = rarity;
  }

  public String getCardSet() {
    return cardSet;
  }

  public void setCardSet(String cardSet) {
    this.cardSet = cardSet;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (Card) obj;
    return Objects.equals(this.id, that.id)
        && Objects.equals(this.game, that.game)
        && Objects.equals(this.name, that.name)
        && Objects.equals(this.rarity, that.rarity)
        && Objects.equals(this.cardSet, that.cardSet)
        && Objects.equals(this.rating, that.rating)
        && Objects.equals(this.comment, that.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, game, name, rarity, cardSet, rating, comment);
  }

  @Override
  public String toString() {
    return "Card{"
        + "id=" + id
        + ", collection=" + collection
        + ", game='" + game + '\''
        + ", name='" + name + '\''
        + ", rarity='" + rarity + '\''
        + ", cardSet='" + cardSet + '\''
        + ", rating='" + rating + '\''
        + ", comment='" + comment + '\''
        + '}';
  }
}
