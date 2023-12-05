package collection.data;

import collection.tcg.Card;

import java.util.LinkedList;
import java.util.List;

public class Collection {

    private final String name;
    private final List<Card> cards = new LinkedList<>();

    public Collection(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }
}
