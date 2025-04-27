package cardsgame;

import cardsgame.card.Card;
import cardsgame.card.ComparableCard;

public class Match4 extends Matcher {
    public Match4() {
        super("quads");
    }

    public static void main(String[] args) {
        new Match4();
    }

    @Override
    protected void match(Card c1) {
        for (Card c2 : c1.withDotMatchingMyCircle()) {
            for (Card flipOfC2 : ComparableCard.flips(c2)) {
                if (c1.circleMatchesDot(flipOfC2)) {
                    for (Card c3 : c2.withDotMatchingMyCircle()) {
                        for (Card flipOfC3 : ComparableCard.flips(c3)) {
                            if (flipOfC2.circleMatchesDot(flipOfC3)) {
                                for (Card c4 : c3.withDotMatchingMyCircle()) {
                                    for (Card flipOfC4 : ComparableCard.flips(c4)) {
                                        if (flipOfC3.circleMatchesDot(flipOfC4) && flipOfC4.circleMatchesDot(c1)) {
                                            add(c1, c2, c3, c4);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
