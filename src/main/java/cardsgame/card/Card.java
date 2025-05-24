package cardsgame.card;

import cardsgame.spot.Circle;
import cardsgame.spot.Color;
import cardsgame.spot.Dot;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public record Card(Dot dot, Circle circle) implements Comparable<Card> {
    public static final List<Card> cards = Stream.of(
                    dotCards(Color.blue, 0, 0,
                            circle(Color.blue, 0, 3),
                            circle(Color.blue, 0, 3),
                            circle(Color.blue, 2, 0),
                            circle(Color.blue, 2, 0),
                            circle(Color.blue, 2, 3),
                            circle(Color.blue, 2, 3),
                            circle(Color.green, 1, 0),
                            circle(Color.green, 1, 3),
                            circle(Color.green, 1, 3),
                            circle(Color.orange, 2, 1),
                            circle(Color.orange, 2, 1),
                            circle(Color.orange, 0, 2),
                            circle(Color.orange, 0, 2),
                            circle(Color.orange, 0, 1),
                            circle(Color.orange, 2, 2),
                            circle(Color.purple, 1, 1),
                            circle(Color.purple, 1, 1),
                            circle(Color.purple, 1, 2)),
                    dotCards(Color.orange, 0, 1,
                            circle(Color.blue, 2, 0),
                            circle(Color.blue, 2, 0),
                            circle(Color.blue, 0, 3),
                            circle(Color.blue, 0, 3),
                            circle(Color.blue, 0, 0),
                            circle(Color.blue, 2, 3),
                            circle(Color.orange, 2, 2),
                            circle(Color.orange, 2, 2),
                            circle(Color.orange, 2, 1),
                            circle(Color.orange, 2, 1),
                            circle(Color.orange, 0, 2),
                            circle(Color.orange, 0, 2),
                            circle(Color.purple, 1, 1),
                            circle(Color.purple, 1, 1),
                            circle(Color.purple, 1, 2),
                            circle(Color.green, 1, 0),
                            circle(Color.green, 1, 0),
                            circle(Color.green, 1, 3)),
                    dotCards(Color.green, 1, 0,
                            circle(Color.blue, 2, 3),
                            circle(Color.blue, 2, 3),
                            circle(Color.blue, 0, 0),
                            circle(Color.green, 1, 3),
                            circle(Color.green, 1, 3),
                            circle(Color.purple, 1, 2),
                            circle(Color.purple, 1, 2),
                            circle(Color.purple, 1, 1),
                            circle(Color.purple, 1, 1),
                            circle(Color.orange, 0, 1),
                            circle(Color.orange, 0, 1),
                            circle(Color.orange, 0, 2)),
                    dotCards(Color.purple, 1, 1,
                            circle(Color.blue, 0, 0),
                            circle(Color.blue, 0, 0),
                            circle(Color.blue, 2, 3),
                            circle(Color.orange, 0, 1),
                            circle(Color.orange, 0, 1),
                            circle(Color.orange, 0, 2),
                            circle(Color.green, 1, 3),
                            circle(Color.green, 1, 3),
                            circle(Color.green, 1, 0),
                            circle(Color.green, 1, 0),
                            circle(Color.purple, 1, 2),
                            circle(Color.purple, 1, 2)))
            .flatMap(Cards::cards)
            .sorted()
            .toList();
    public static final List<Card> distinct = cards.stream()
            .distinct()
            .sorted()
            .toList();
    private static final Map<Color, List<Card>> withDotColor = cards.stream()
            .distinct()
            .collect(groupingBy(c -> c.dot().color()));

    private static Cards dotCards(Color color, int x, int y, Circle... circles) {
        return new Cards(new Dot(color, x, y), List.of(circles));
    }

    private static Circle circle(Color color, int x, int y) {
        return new Circle(color, x, y);
    }

    @Override
    public int compareTo(Card o) {
        int diff = dot.compareTo(o.dot);
        if (diff == 0) {
            diff = circle.compareTo(o.circle);
        }
        return diff;
    }

    public String toHtml() {
        return "<div class=\"card\">" + dot.toHtml() + circle.toHtml() + "</div>";
    }

    public boolean circleMatchesDot(Card other) {
        return (circle.color() == other.dot.color()) &&
                (circle.x() == other.dot.x()) &&
                (circle.y() == other.dot.y());
    }

    @Override
    public String toString() {
        return dot + " and " + circle;
    }

    public String ansi() {
        return dot.ansi() + " and " + circle.ansi();
    }

    public static Card find(Color dotColor, int dotX, int dotY, Color circleColor, int circleX, int circleY) {
        return Card.distinct.stream()
                .filter(c -> c.dot.is(dotColor, dotX, dotY))
                .filter(c -> c.circle.is(circleColor, circleX, circleY))
                .findFirst()
                .get();
    }

    public List<Card> withDotMatchingMyCircle() {
        return withDotColor.get(circle.color());
    }

    public Card[] flip() {
        return new Card[]{
                this,
                new Card(new Dot(dot.color(), 2 - dot.x(), dot.y()), new Circle(circle.color(), 2 - circle.x(), circle.y())),
                new Card(new Dot(dot.color(), 2 - dot.x(), 3 - dot.y()), new Circle(circle.color(), 2 - circle.x(), 3 - circle.y())),
                new Card(new Dot(dot.color(), dot.x(), 3 - dot.y()), new Circle(circle.color(), circle.x(), 3 - circle.y())),
        };
    }
}
