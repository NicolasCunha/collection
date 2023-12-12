package com.collection.app.collection;

import com.collection.app.tcg.Card;
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

/**
 * Represents a collection. A collection is the root level of every kind of hobby
 * this app currently supports.
 * A collection is composed of an ID and a name, and several flags/configurations.
 */
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

  @Column
  private Boolean isTcg;

  @Column
  private Boolean isVinyl;

  @Column
  private Boolean isBooks;

  @Column
  private Boolean isGames;

  @OneToMany(mappedBy = "collection", fetch = FetchType.EAGER)
  private List<Card> cards = new LinkedList<>();

  /**
   * Creates a new {@link Collection}.
   *
   * @param name {@link String} Collection name.
   * @param isTcg {@link Boolean} If TCG are supported.
   * @param isVinyl {@link Boolean} If Vinyl are supported.
   * @param isBooks {@link Boolean} If Books are supported.
   * @param isGames {@link Boolean} If Games are supported.
   * @return {@link Collection} instance.
   */
  public static Collection create(final String name,
                                  final Boolean isTcg,
                                  final Boolean isVinyl,
                                  final Boolean isBooks,
                                  final Boolean isGames) {
    final Collection collection = new Collection();
    collection.setName(name);
    collection.setTcg(isTcg);
    collection.setVinyl(isVinyl);
    collection.setBooks(isBooks);
    collection.setGames(isGames);
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

  public Boolean getTcg() {
    return isTcg;
  }

  public void setTcg(Boolean tcg) {
    isTcg = tcg;
  }

  public Boolean getVinyl() {
    return isVinyl;
  }

  public void setVinyl(Boolean vinyl) {
    isVinyl = vinyl;
  }

  public Boolean getBooks() {
    return isBooks;
  }

  public void setBooks(Boolean books) {
    isBooks = books;
  }

  public Boolean getGames() {
    return isGames;
  }

  public void setGames(Boolean games) {
    isGames = games;
  }

  public List<Card> getCards() {
    return cards;
  }

  public void setCards(List<Card> cards) {
    this.cards = cards;
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
