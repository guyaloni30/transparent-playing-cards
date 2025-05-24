package cardsgame.spot;

import java.util.Comparator;

public interface Spot extends Comparable<Spot> {
    Color color();

    int x();

    int y();

    Comparator<Spot> COMPARATOR = Comparator
            .comparing(Spot::color)
            .thenComparing(Spot::x)
            .thenComparing(Spot::y);

    default int compareTo(Spot o) {
        return COMPARATOR.compare(this, o);
    }

    default boolean is(Color color, int x, int y) {
        return color == color() &&
                x == x() &&
                y == y();
    }

    default String toHtml() {
        String htmlClass = String.join(" ",
                getClass().getSimpleName().toLowerCase(),
                color().name(),
                "left_" + x(),
                "top_" + y());
        return "<div class=\"" + htmlClass + "\">&nbsp;</div>";
    }
}
