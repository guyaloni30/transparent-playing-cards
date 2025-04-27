package cardsgame.card;

import cardsgame.spot.Circle;
import cardsgame.spot.Color;
import cardsgame.spot.Dot;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public record Card(Dot dot, Circle circle) implements Comparable<Card> {
    public static final List<Card> cards = Map.of(
                    dot(Color.blue, 0, 0), List.of(
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
                    dot(Color.orange, 0, 1), List.of(
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
                    dot(Color.green, 1, 0), List.of(
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
                    dot(Color.purple, 1, 1), List.of(
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
            .entrySet()
            .stream()
            .flatMap(e -> e.getValue().stream().map(s2 -> new Card(e.getKey(), s2)))
            .sorted()
            .toList();
    public static final List<Card> distinct = cards.stream()
            .distinct()
            .sorted()
            .toList();
    private static final Map<Color, List<Card>> withDotColor = cards.stream()
            .distinct()
            .collect(groupingBy(c -> c.dot().color()));

    private static Dot dot(Color color, int x, int y) {
        return new Dot(color, x, y);
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
        return "    <div class=\"card\">" + dot.toHtml() + circle.toHtml() + "</div>";
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
                .filter(c -> c.dot().is(dotColor, dotX, dotY))
                .filter(c -> c.circle().is(circleColor, circleX, circleY))
                .findFirst()
                .get();
    }

    public List<Card> withDotMatchingMyCircle() {
        return withDotColor.get(circle.color());
    }
}
