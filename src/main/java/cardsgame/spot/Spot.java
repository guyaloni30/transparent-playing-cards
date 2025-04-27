package cardsgame.spot;

public interface Spot extends Comparable<Spot> {
    Color color();

    int x();

    int y();

    default int compareTo(Spot o) {
        int diff = color().compareTo(o.color());
        if (diff != 0) {
            return diff;
        }
        if (x() != o.x()) {
            return x() - o.x();
        }
        if (y() != o.y()) {
            return y() - o.y();
        }
        return 0;
    }

    default boolean is(Color color, int x, int y) {
        return color == color() &&
                x == x() &&
                y == y();
    }

    default String toHtml() {
        String htmlClass = String.join(" ",
                color().name(),
                "x_" + x(),
                "y_" + y());
        return "<div class=\"" + htmlClass + "\">&nbsp;</div>";
    }
}
