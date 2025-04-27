package cardsgame.spot;

public record Circle(Color color, int x, int y) implements Spot {
    @Override
    public String toString() {
        return color + " circle at " + x + "," + y;
    }

    public String ansi() {
        return color.print("circle at " + x + "," + y);
    }
}
