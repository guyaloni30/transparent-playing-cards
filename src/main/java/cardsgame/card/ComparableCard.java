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
                .forEach(originalCard -> {
                    Dot dot = originalCard.dot();
                    Circle circle = originalCard.circle();
                    flips.put(
                            originalCard,
                            new Card[]{
                                    originalCard,
                                    new Card(new Dot(dot.color(), 2 - dot.x(), dot.y()), new Circle(circle.color(), 2 - circle.x(), circle.y())),
                                    new Card(new Dot(dot.color(), 2 - dot.x(), 3 - dot.y()), new Circle(circle.color(), 2 - circle.x(), 3 - circle.y())),
                                    new Card(new Dot(dot.color(), dot.x(), 3 - dot.y()), new Circle(circle.color(), circle.x(), 3 - circle.y())),
                            });
                });
        return flips;
    }
}
