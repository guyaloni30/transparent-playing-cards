package cardsgame.spot;

public record Dot(Color color, int x, int y) implements Spot {
    @Override
    public String toString() {
        return color + " dot at " + x + "," + y;
    }

    public String ansi() {
        return color.print("dot at " + x + "," + y);
    }
}
