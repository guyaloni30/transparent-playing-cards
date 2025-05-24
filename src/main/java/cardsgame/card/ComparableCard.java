package cardsgame.card;

import cardsgame.spot.Circle;
import cardsgame.spot.Dot;

import java.util.IdentityHashMap;
import java.util.Map;

public abstract class ComparableCard {
    private static final Map<Card, Card[]> flips = getFlips();

    public static Card[] flips(Card card) {
        return flips.get(card);
    }

    private static Map<Card, Card[]> getFlips() {
        Map<Card, Card[]> flips = new IdentityHashMap<>();
        Card.cards.stream()
                .distinct()
                .forEach(originalCard -> flips.put(originalCard, originalCard.flip()));
        return flips;
    }
}
