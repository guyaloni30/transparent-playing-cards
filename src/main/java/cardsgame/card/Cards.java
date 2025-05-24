package cardsgame.card;

import cardsgame.spot.Circle;
import cardsgame.spot.Dot;

import java.util.List;
import java.util.stream.Stream;

public record Cards(Dot dot, List<Circle> circles) {
    public Stream<Card> cards() {
        return circles.stream()
                .map(circle -> new Card(dot, circle));
    }
}
