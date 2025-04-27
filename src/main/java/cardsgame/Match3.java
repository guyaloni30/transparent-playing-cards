package cardsgame;

import cardsgame.card.Card;
import cardsgame.card.ComparableCard;

public class Match3 extends Matcher {
    public Match3() {
        super("triplets");
    }

    public static void main(String[] args) {
        new Match3();
    }

    @Override
    protected void match(Card c1) {
        for (Card c2 : c1.withDotMatchingMyCircle()) {
            for (Card flipOfC2 : ComparableCard.flips(c2)) {
                if (c1.circleMatchesDot(flipOfC2)) {
                    for (Card c3 : c2.withDotMatchingMyCircle()) {
                        for (Card flipOfC3 : ComparableCard.flips(c3)) {
                            if (flipOfC2.circleMatchesDot(flipOfC3) && flipOfC3.circleMatchesDot(c1)) {
                                add(c1, c2, c3);
                            }
                        }
                    }
                }
            }
        }
    }
}
