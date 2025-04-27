package cardsgame;

import cardsgame.card.Card;
import cardsgame.card.ComparableCard;

public class Match2 extends Matcher {
    public Match2() {
        super("pairs");
    }

    public static void main(String[] args) {
        new Match2();
    }

    @Override
    protected void match(Card c1) {
        for (Card c2 : c1.withDotMatchingMyCircle()) {
            for (Card flipOfC2 : ComparableCard.flips(c2)) {
                if (c1.circleMatchesDot(flipOfC2) && flipOfC2.circleMatchesDot(c1)) {
                    add(c1, c2);
                }
            }
        }
    }
}
